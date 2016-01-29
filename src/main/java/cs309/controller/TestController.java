package cs309.controller;

import cs309.data.User;
import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by jeffrey on 1/18/16.
 */
@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/1")
    public String test() {
        return "index";
    }
//
//    @RequestMapping(value = "/", method = RequestMethod.POST)
//    public void delete(){}
//
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @ResponseBody
//    public List<User> allUsers() {
//        return userService.getUsers();
//    }
}
