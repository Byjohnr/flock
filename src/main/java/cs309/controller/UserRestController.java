package cs309.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs309.data.User;
import cs309.service.UserService;
import cs309.util.JsonObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jeffrey on 2/8/16.
 */
@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user/create")
    public String createUser(@RequestBody User user) {
//        TODO jeffreyh 2/8/16 validation and encrypting password
        userService.saveUser(user);
        return "success";
    }

    @RequestMapping("/user/get/{userEmail}")
    public String getUser(@PathVariable("userEmail") String userEmail) {
        return JsonObjectConverter.toJsonString(userService.getUserByEmail(userEmail));
    }
}
