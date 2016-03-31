package cs309.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin/events")
    public String adminEventsPage() {
        return "adminEventsPage";
    }

}
