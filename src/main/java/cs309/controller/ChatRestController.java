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

@RestController
public class ChatRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/api/chat/list")
    public List<ChatUser> getChatGroups(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        return user.getChatUsers();
    }

    @RequestMapping(value = "/api/chat/group/{groupId}")
    public ChatGroup getChatGroup(@PathVariable int groupId) {
        return chatService.getChatGroupById(groupId);
    }

    @RequestMapping(value = "/api/chat/group/{groupId}/updateName")
    public void updateChatName(@RequestBody String groupName, @PathVariable int groupId) {
        ChatGroup chatGroup = chatService.getChatGroupById(groupId);
        chatGroup.setChatName(groupName);
        chatService.saveChatGroup(chatGroup);
    }

    @RequestMapping(value = "/api/chat/group/{groupId}/invite", method = RequestMethod.POST)
    public void inviteUser(@RequestBody String userId, @PathVariable int groupId) {
        ChatGroup chatGroup = chatService.getChatGroupById(groupId);
        ChatUser chatUser = new ChatUser(chatGroup, ChatUser.STATUS_INVITED, userService.getUser(Integer.decode(userId)));
        chatService.saveChatUser(chatUser);
    }

    @RequestMapping(value = "/api/chat/group/create", method = RequestMethod.POST)
    public void createChatGroup(@RequestBody String groupName, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        ChatGroup chatGroup = new ChatGroup(groupName, user);
        ChatGroup savedChatGroup = chatService.saveChatGroup(chatGroup);
        chatService.saveChatUser(new ChatUser(savedChatGroup, ChatUser.STATUS_ACCEPTED, user));
    }

    @RequestMapping(value = "/api/chat/{userId}/respond", method = RequestMethod.POST)
    public void respondToChatInvite(@RequestBody String response, @PathVariable int userId) {
        ChatUser chatUser = chatService.getChatUserById(userId);
        if(StringUtils.equals("accept", response)) {
            chatUser.setStatus(ChatUser.STATUS_ACCEPTED);
            chatService.saveChatUser(chatUser);
        } else {
            chatService.deleteChatUser(chatUser);
        }
    }

    @RequestMapping(value = "/api/chat/group/{groupId}/message", method = RequestMethod.POST)
    public void saveMessage(@RequestBody String message, @PathVariable int groupId, Principal principal) {
        chatService.saveChatMessage(message, groupId, principal.getName());
    }

}
