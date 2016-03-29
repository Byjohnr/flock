package cs309.service;

import cs309.data.Event;
import cs309.data.specifications.EventSpecification;
import cs309.repo.CommentRepository;
import cs309.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        Specification<Event> specification = new EventSpecification(query);
        return eventRepository.findAll(specification);
    }
}
