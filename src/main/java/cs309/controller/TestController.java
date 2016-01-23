package cs309.controller;

import cs309.data.User;
import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by jeffrey on 1/18/16.
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String test() {
        return "index";
    }

    @ModelAttribute("users")
    public List<User> allUsers() {
        return userService.getUsers();
    }
}
