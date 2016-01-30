package cs309.service;

import cs309.data.Event;
import cs309.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeffrey on 1/28/16.
 */
@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
}
