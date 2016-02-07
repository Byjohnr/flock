package cs309.controller;

import cs309.data.Event;
import cs309.dto.CreateEventDTO;
import cs309.dto.EventDTO;
import cs309.service.EventService;
import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeffrey on 1/29/16.
 */
@RestController
public class HomeRestController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @RequestMapping("/api/events")
    public List<EventDTO> getEvents() {
        List<EventDTO> eventDTOs = new ArrayList<>();
        eventService.getEvents().stream().forEach(event -> eventDTOs.add(new EventDTO(event)));
        return eventDTOs;
    }

    @RequestMapping(value = "/api/create", method = RequestMethod.POST)
    public String createEvent(@RequestBody final CreateEventDTO createEventDTO) throws IOException, ParseException {
//        TODO jeffreyh 2-6-16 add validation, return response
//        TODO jefffreyh 2-6/16 set the user by whoever is creating the event
        eventService.saveEvent(new Event(createEventDTO, userService.getUser(1)));
//        TODO jeffreyh 2/7/16 return url to event just created when the event page is created
        return "/";
    }
}
