package cs309.controller;

import cs309.data.Event;
import cs309.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    @Autowired
    private EventService eventService;

    @RequestMapping("/admin/events")
    public List<Event> getAllEvents() {
        return eventService.getEvents();
    }
}
