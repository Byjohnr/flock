package cs309.controller;

import cs309.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cs309.data.Event;
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

    @RequestMapping(value = "/api/event/{id}", method = RequestMethod.POST)
    public String updateEvent(@RequestBody Event event) {
        eventService.saveEvent(event);
        return "/";
    }
}
