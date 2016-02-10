package cs309.controller;

import cs309.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import cs309.data.Event;
/**
 * Created by chasekoehler on 2/8/16.
 */
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping("/event/{id}")
    public String EventPage(@PathVariable Integer id) {
        return "event";
    }

}
