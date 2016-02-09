package cs309.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jeffrey on 2/4/16.
 */
@Controller
public class CreateEventController {

    @RequestMapping("/create")
    public String createEventPage() {
        return "createEvent";
    }
}
