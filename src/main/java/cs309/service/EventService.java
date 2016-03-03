package cs309.service;

import cs309.data.Comment;
import cs309.data.Event;
import cs309.repo.CommentRepository;
import cs309.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffrey on 1/28/16.
 */
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
}
