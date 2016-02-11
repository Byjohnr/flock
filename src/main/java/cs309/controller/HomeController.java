package cs309.controller;

import cs309.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jeffrey on 1/28/16.
 */
@Controller
public class HomeController {

    @Autowired
    private EventService eventService;

    @RequestMapping("/")
    public String homePage() {
        return "home";
    }

    @RequestMapping("/main")
    public String mainPage() {
        return "mainPage";
    }
}
