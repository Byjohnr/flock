package cs309.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConnectionController {


    @RequestMapping("/account/connectionGroups")
    public String connectionGroupPage() {
        return "connectionGroups";
    }

    @RequestMapping("/connectionGroup/{id}")
    public String connectionGroup() {
        return "connectionGroup";
    }
}
