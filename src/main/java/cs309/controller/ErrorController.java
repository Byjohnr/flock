package cs309.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/403")
    public String Error403Page(){return "Error403Page";}

    @RequestMapping("/404")
    public String Error404Page() {return "Error404Page";}
}