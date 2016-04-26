package cs309.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TagController {

    @RequestMapping("/admin/tags")
    public String tagPage() {
        return "adminTagPage";
    }
}
