package cs309.controller;

import cs309.data.Event;
import cs309.data.Role;
import cs309.data.User;
import cs309.service.EventService;
import cs309.service.RoleService;
import cs309.service.SecurityService;
import cs309.service.UserService;
import org.apache.commons.lang3.StringUtils;
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

    @PreAuthorize(("hasRole('" + Role.ADMIN + "')"))
    @RequestMapping(value = "/authentication/{userId}")
    public String authenticationLevel(@PathVariable("userId") Integer userId, Principal principal) {
        String authenticationLevel = "";
        User user = userService.getUser(userId);
        if (user != null) {
            if (roleService.isAdmin(user)) {
                authenticationLevel = "Admin";
            } else {
                authenticationLevel = "User";
            }
        }
        return authenticationLevel;
    }

    @PreAuthorize(("hasRole('" + Role.ADMIN + "')"))
    @RequestMapping(value = "/authentication/{userId}/make_admin")
    public String makeUserAdmin(@PathVariable("userId") Integer userId, Principal principal) {
        String returnedMessage = "";
        User user = userService.getUser(userId);
        if (user != null) {
            Role savedRole = roleService.updateRolePrivilages(user.getEmail(), Role.ADMIN);
            if (savedRole != null && StringUtils.equalsIgnoreCase(Role.ADMIN, savedRole.getRoleName())) {
                returnedMessage = user.getFirstName() + " " + user.getLastName() + " has been made an admin.";
            } else {
                returnedMessage = user.getFirstName() + " " + user.getLastName() + " was not made an admin.";
            }
        }
        return returnedMessage;
    }

    @PreAuthorize(("hasRole('" + Role.ADMIN + "')"))
    @RequestMapping(value = "/authentication/{userId}/make_user")
    public String makeAdminUser(@PathVariable("userId") Integer userId, Principal principal) {
        String returnedMessage = "";
        User user = userService.getUser(userId);
        if (user != null && StringUtils.equalsIgnoreCase(user.getEmail(), principal.getName())) {
            Role savedRole = roleService.updateRolePrivilages(user.getEmail(), Role.USER);
            if (savedRole != null && StringUtils.equalsIgnoreCase(Role.USER, savedRole.getRoleName())) {
                returnedMessage = user.getFirstName() + " " + user.getLastName() + " has been made a user.";
            } else {
                returnedMessage = user.getFirstName() + " " + user.getLastName() + " was not made a user.";
            }
        } else if (user != null) {
            returnedMessage = "You cannot take admin privilages away from yourself.";
        }
        return returnedMessage;
    }

    @RequestMapping("/list_all_users")
    @PreAuthorize("hasRole('" + Role.ADMIN + "')")
    public List<User> listAllUsers(Principal principal) {
        return userService.getUsers();
    }
}
