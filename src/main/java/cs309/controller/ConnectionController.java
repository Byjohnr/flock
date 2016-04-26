package cs309.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConnectionController {

    @RequestMapping("/account/connectionGroups")
    public String connectionGroupListPage() {
        return "connectionGroups";
    }

    @RequestMapping("/account/connectionGroup/{id}")
    public String connectionGroupPage() {
        return "connectionGroup";
    }
}
