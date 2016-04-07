package cs309.controller;

import cs309.data.Event;
import cs309.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/admin/events/delete", method = RequestMethod.POST)
    public void deleteEvent(@RequestBody Integer id) {
        eventService.deleteEvent(eventService.getEvent(id));
    }
}
