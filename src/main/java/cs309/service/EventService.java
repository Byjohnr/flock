package cs309.service;

import cs309.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jeffrey on 1/28/16.
 */
@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;
}
