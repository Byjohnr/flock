package cs309.controller;

import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/account")
    public String userAccountPage() {
        return "viewAccount";
    }

    @RequestMapping("/user/{id}")
    public String userPage(@PathVariable int id, Principal principal) {
        if(userService.getUserByEmail(principal.getName()).getId() == id) {
            return "redirect:/account";
        }
        return userService.getUser(id) != null ? "userPage" : "redirect:/404";
    }

    @RequestMapping("/admin/user_list")
//    @PreAuthorize("@securityService.isAdmin(#principal)")
    public String adminUserPage(Principal principal) {
        return "adminUserPage";
    }
}
