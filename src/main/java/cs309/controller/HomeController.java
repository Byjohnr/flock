package cs309.controller;

import cs309.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class HomeController {

    private final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping("/")
    public String homePage(Principal principal) {
        if(principal != null) {
            LOG.error(principal.getName());
            LOG.error(principal.toString());
        }
        return "home";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage() {
        return "mainPage";
    }

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String mapPage() {
        return "mapPage";
    }
}
