package cs309.service;

import cs309.data.Event;
import cs309.dto.LocationDTO;
import cs309.repo.CommentRepository;
import cs309.repo.EventRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    CommentRepository commentRepository;

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

    @Transactional
    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    public List<LocationDTO> getPublicEventAddresses() {
        List<LocationDTO> locations = new LinkedList<>();
        List<Event> events = eventRepository.findPublicEvents();
        for (Event event : events) {
            if (event.getLatitude() != null && event.getLongitude() != null) {
                locations.add(new LocationDTO(event.getLatitude(), event.getLongitude()));
            }
        }
        return locations;
    }
}
