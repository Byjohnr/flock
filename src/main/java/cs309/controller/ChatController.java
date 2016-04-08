package cs309.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class ChatController {

    @RequestMapping(value = "/chat/list")
    public String chatListPage() {
        return "chatListPage";
    }

    @RequestMapping(value = "/chat/group/{groupId}")
    public String chatGroupPage() {
        return "chatGroupPage";
    }


}
