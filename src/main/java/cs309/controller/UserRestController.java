package cs309.controller;

import cs309.data.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs309.data.User;
import cs309.service.RoleService;
import cs309.service.UserService;
import cs309.util.JsonObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/user/create")
    public String createUser(@RequestBody User user) {
//        TODO jeffreyh 2/8/16 validation
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        roleService.createRole(user.getEmail(), Role.USER);
        return "success";
    }

    @RequestMapping("/user/info")
    public User getUser(Principal principal) {
//        TODO jeffreyh 2/22/16 remove this when we decide to enable authentication
        if(principal == null) {
            return userService.getUser(1);
        }
        return userService.getUserByEmail(principal.getName());
    }

    @RequestMapping("/user/get/{userEmail:.+}")
    public String getUser(@PathVariable("userEmail") String userEmail) {
        return JsonObjectConverter.toJsonString(userService.getUserByEmail(userEmail));
    }
}