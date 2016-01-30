package cs309.controller;

import cs309.data.Event;
import cs309.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jeffrey on 1/29/16.
 */
@RestController
public class HomeRestController {

    @Autowired
    private EventService eventService;

    @RequestMapping("/events")
    List<Event> getEvents() {
        return eventService.getEvents();
    }
}
