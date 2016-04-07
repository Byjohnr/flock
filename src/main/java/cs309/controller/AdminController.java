package cs309.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class AdminController {

    @PreAuthorize("@securityService.isAdmin(#principal)")
    @RequestMapping(value = "/admin/events")
    public String adminEventsPage(Principal principal) {
        return "adminEventsPage";
    }

}
