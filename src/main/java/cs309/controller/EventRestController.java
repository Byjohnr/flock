package cs309.controller;

import cs309.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cs309.data.Event;
import org.springframework.web.bind.annotation.RestController;
import cs309.service.EventService;

/**
 * Created by chasekoehler on 2/8/16.
 */

@RestController
public class EventRestController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/api/event/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable Integer id) {
        return eventService.getEvent(id);
    }
}
