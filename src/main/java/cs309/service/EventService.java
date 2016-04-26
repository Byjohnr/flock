package cs309.service;

import cs309.data.Event;
import cs309.dto.LocationDTO;
import cs309.data.EventInvite;
import cs309.data.User;
import cs309.repo.CommentRepository;
import cs309.repo.ConnectionRepository;
import cs309.repo.EventInviteRepository;
import cs309.repo.EventRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private EventInviteRepository eventInviteRepository;

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(int id) {
        return eventRepository.findOne(id);
    }

    @Transactional
    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getEventSearch(String query) {
        if(StringUtils.isBlank(query)) {
            return eventRepository.findAll();
        }
        Specification<Event> eventNameSpecification = (root, query1, cb) -> cb.like(cb.lower(root.get("eventName")), "%" + query + "%");
        Specification<Event> location = (root, query1, cb) -> cb.like(cb.lower(root.get("location")), query + "%");
        List<Event> events = eventRepository.findAll(eventNameSpecification);
        events.addAll(eventRepository.findAll(location).stream().filter(event -> !events.contains(event)).collect(Collectors.toList()));
        int eventSize = events.size();
        return eventSize > 5 ? events.subList(0,5) : events;
    }

    public List<Event> getEventsFromMapSearch(Integer type, Integer tagId, User user) {
        List<Event> events;
        if(type != null && tagId != null) {
            events = eventRepository.getEventsByTagId(tagId);
            if (type == Event.CONNECTIONS_ONLY) {
                events = events.stream().filter(event -> connectionRepository.isConnected(user, event.getCreator())).collect(Collectors.toList());
            } else if (type == Event.INVITED) {
                events = events.stream().filter(event -> eventInviteRepository.userInviteExists(event.getId(), user.getId())).collect(Collectors.toList());
            } else if (type == Event.OPEN) {
                events = events.stream().filter(event -> event.getType() == Event.OPEN).collect(Collectors.toList());
            }
        } else if (tagId != null) {
            events = getEventsByTagId(tagId, user);
        } else if (type != null) {
            events = getEventsByType(type, user);
        } else {
            List<EventInvite> eventsInvitedTo = user.getEvents();
            List<Event> openEvents = eventRepository.getOpenEvents();
            List<Event> connectionsOnlyEvents = eventRepository.getConnectionsOnlyEvents()
                    .stream().filter(event -> connectionRepository.isConnected(user, event.getCreator()))
                    .collect(Collectors.toList());
            Set<Event> eventSet = new HashSet<>();
            eventsInvitedTo.stream().forEach(eventInvite -> eventSet.add(eventInvite.getEvent()));
            openEvents.forEach(eventSet::add);
            connectionsOnlyEvents.forEach(eventSet::add);
            events = new ArrayList<>(eventSet);
        }
        return  events;
    }

    @Transactional
    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    public List<LocationDTO> getPublicEventAddresses() {
        List<LocationDTO> locations = new ArrayList<>();
        List<Event> events = eventRepository.getOpenEvents();
        for (Event event : events) {
            if (event.getLatitude() != null && event.getLongitude() != null) {
                locations.add(new LocationDTO(event.getLatitude(), event.getLongitude()));
            }
        }
        return locations;
    }

    private List<Event> getEventsByType(Integer type, User user) {
        List<Event> events;
        if (type == Event.OPEN) {
            events = eventRepository.getOpenEvents();
        } else if (type == Event.CONNECTIONS_ONLY) {
            events = eventRepository.getConnectionsOnlyEvents().stream()
                    .filter(event -> connectionRepository.isConnected(user, event.getCreator()))
                    .collect(Collectors.toList());
        } else if (type == Event.INVITED) {
            Set<Event> eventSet = new HashSet<>();
            user.getEvents().stream().forEach(eventInvite -> eventSet.add(eventInvite.getEvent()));
            events = new ArrayList<>(eventSet);
        } else {
            events = new ArrayList<>();
        }
        return events;
    }

    private List<Event> getEventsByTagId(Integer tagId, User user) {
        List<Event> events = eventRepository.getEventsByTagId(tagId);
        return events.stream().filter(event -> event.getType() != Event.CONNECTIONS_ONLY ||
                connectionRepository.isConnected(user, event.getCreator()))
                .collect(Collectors.toList());
    }
}
