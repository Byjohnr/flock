package cs309.controller;

import cs309.data.Event;
import cs309.data.Role;
import cs309.data.User;
import cs309.service.EventService;
import cs309.service.RoleService;
import cs309.service.SecurityService;
import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/events")
    public List<Event> getAllEvents() {
        return eventService.getEvents();
    }

    @PreAuthorize(("hasRole('" + Role.ADMIN + "')"))
    @RequestMapping(value = "/events/delete", method = RequestMethod.POST)
    public void deleteEvent(@RequestBody Integer id) {
        eventService.deleteEvent(eventService.getEvent(id));
    }

    @RequestMapping(value = "/authentication")
    public Boolean adminAuthentication(Principal principal) {
        boolean adminAuth = false;
        if (principal != null) {
            User principalUser = userService.getUserByEmail(principal.getName());
            adminAuth = roleService.isAdmin(principalUser);
        }
        return adminAuth;
    }

    @RequestMapping("/list_all_users")
    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    public List<User> listAllUsers(Principal principal) {
        return userService.getUsers();
    }
}
