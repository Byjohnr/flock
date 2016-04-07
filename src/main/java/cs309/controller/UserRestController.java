package cs309.controller;

import cs309.data.Event;
import cs309.data.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs309.data.User;
import cs309.dto.SearchDTO;
import cs309.service.EventService;
import cs309.service.RoleService;
import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private EventService eventService;

    @RequestMapping("/user/create")
    public String createUser(@RequestBody User user) {
//        TODO jeffreyh 2/8/16 validation
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        roleService.createRole(user.getEmail(), Role.USER, null);
        return "success";
    }

    @RequestMapping("/user/info")
    public User getUser(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    @RequestMapping("/user/{id}")
    public User getOtherUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @RequestMapping("/home/search")
    public SearchDTO handleSearch(@RequestBody String query) {
        query = query.toLowerCase();
        List<User> users = userService.userSearch(query);
        List<Event> events = eventService.getEventSearch(query);
        return new SearchDTO(events, users);
    }
}