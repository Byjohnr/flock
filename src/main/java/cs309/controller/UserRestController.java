package cs309.controller;

import cs309.data.Role;
import cs309.data.User;
import cs309.service.RoleService;
import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/api/user/create")
    public String createUser(@RequestBody User user) {
//        TODO jeffreyh 2/8/16 validation
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        roleService.createRole(user.getEmail(), Role.USER);
        return "success";
    }

    @RequestMapping("/api/user/info")
    public User getUser(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }
}
