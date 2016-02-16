package cs309.controller;

import cs309.data.User;
import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jeffrey on 2/8/16.
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/api/user/create")
    public String createUser(@RequestBody User user) {
//        TODO jeffreyh 2/8/16 validation and encrypting password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "success";
    }
}
