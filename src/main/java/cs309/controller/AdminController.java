package cs309.controller;

import cs309.data.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('" + Role.ADMIN + "')")
public class AdminController {
    @RequestMapping(value = "/events")
    public String adminEventsPage(Principal principal) {
        return "adminEventsPage";
    }

    @RequestMapping("/user_list")
    public String adminUserPage(Principal principal) {
        return "adminUserPage";
    }
}
