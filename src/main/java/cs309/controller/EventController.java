package cs309.controller;

import cs309.service.EventService;
import cs309.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private SecurityService securityService;

    @PreAuthorize("@securityService.isInvited(#id, #principal)")
    @RequestMapping(value = "/event/{id}")
    public String eventPage(@PathVariable("id") Integer id, Principal principal) {
        return eventService.getEvent(id) != null ? "eventPage" : "redirect:/404";
    }

    @RequestMapping("/event/create")
    public String createEventPage() {
        return "createEvent";
    }

}
