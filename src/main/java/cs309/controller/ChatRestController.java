package cs309.controller;

import cs309.data.ChatGroup;
import cs309.data.ChatUser;
import cs309.data.User;
import cs309.service.ChatService;
import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController(value = "/api")
public class ChatRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/chat/list")
    public List<ChatUser> getChatGroups(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        return user.getChatUsers();
    }

    @RequestMapping(value = "/chat/group/{groupId}")
    public ChatGroup getChatGroup(@PathVariable int groupId) {
        return chatService.getChatGroupById(groupId);
    }

    @RequestMapping(value = "/chat/group/{groupId}/updateName")
    public void updateChatName(@RequestBody String groupName, @PathVariable int groupId) {
        ChatGroup chatGroup = chatService.getChatGroupById(groupId);
        chatGroup.setChatName(groupName);
        chatService.saveChatGroup(chatGroup);
    }

    @RequestMapping(value = "/chat/group/{groupId}/invite")
    public void inviteUser(@RequestBody String userId, @PathVariable int groupId) {
        ChatGroup chatGroup = chatService.getChatGroupById(groupId);
        ChatUser chatUser = new ChatUser(chatGroup, ChatUser.STATUS_INVITED, userService.getUser(Integer.decode(userId)));
        chatService.saveChatUser(chatUser);
    }

}
