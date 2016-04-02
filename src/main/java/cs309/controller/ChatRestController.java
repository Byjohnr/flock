package cs309.controller;

import cs309.data.ChatGroup;
import cs309.data.ChatUser;
import cs309.data.User;
import cs309.service.ChatService;
import cs309.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController(value = "/api")
public class ChatRestController {
//TODO jeffreyh 4/1/16 add security to the pages


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

    @RequestMapping(value = "/chat/group/create")
    public void createChatGroup(@RequestBody String groupName, Principal principal) {
        ChatGroup chatGroup = new ChatGroup(groupName, userService.getUserByEmail(principal.getName()));
        chatService.saveChatGroup(chatGroup);
    }

    @RequestMapping(value = "/chat/group/{groupId}/invite/respond")
    public void respondToChatInvite(@RequestBody String response, @PathVariable int groupId, Principal principal) {
        ChatUser chatUser = chatService.getChatUserByEmailAndGroupId(principal.getName(), groupId);
        if(StringUtils.equals("accept", response)) {
            chatUser.setStatus(ChatUser.STATUS_ACCEPTED);
            chatService.saveChatUser(chatUser);
        } else {
            chatService.deleteChatUser(chatUser);
        }
    }

    @RequestMapping(value = "/chat/group/{groupId}/message")
    public void saveMessage(@RequestBody String message, @PathVariable int groupId, Principal principal) {
        chatService.saveChatMessage(message, groupId, principal.getName());
    }

}
