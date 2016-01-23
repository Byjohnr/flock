package cs309.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jeffrey on 1/18/16.
 */
@Controller
public class testController {

    @RequestMapping("/")
    public String test() {
        return "index";
    }

}
