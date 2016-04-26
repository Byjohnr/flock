package cs309.controller;

import cs309.data.ChatGroup;
import cs309.data.ChatMessage;
import cs309.data.ChatUser;
import cs309.data.User;
import cs309.dto.ChatGroupDTO;
import cs309.dto.ChatMessageInput;
import cs309.service.ChatService;
import cs309.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class ChatRestController {

    private static final Logger LOG = LoggerFactory.getLogger(ChatRestController.class);

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
    public ChatGroupDTO getChatGroup(@PathVariable int groupId) {
        ChatGroup chatGroup = chatService.getChatGroupById(groupId);
        return new ChatGroupDTO(chatGroup.getId(), chatGroup.getChatName(), chatGroup.getChatUsers(), chatGroup.getChatMessages());
    }

    @RequestMapping(value = "/api/chat/group/{groupId}/updateName")
    public String updateChatName(@RequestBody String groupName, @PathVariable int groupId) {
        ChatGroup chatGroup = chatService.getChatGroupById(groupId);
        chatGroup.setChatName(groupName);
        chatService.saveChatGroup(chatGroup);
        return groupName;
    }

    @RequestMapping(value = "/api/chat/group/{groupId}/invite", method = RequestMethod.POST)
    public ChatUser inviteUser(@RequestBody String userId, @PathVariable int groupId) {
        ChatGroup chatGroup = chatService.getChatGroupById(groupId);
        ChatUser chatUser = new ChatUser(chatGroup, ChatUser.STATUS_INVITED, userService.getUser(Integer.decode(userId)));
        return chatService.saveChatUser(chatUser);
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

    @MessageMapping("/add/{chatId}")
    @SendTo("/topic/message/{chatId}")
    public ChatMessage saveMessage(@DestinationVariable int chatId, ChatMessageInput message, Principal principal) {
        return chatService.saveChatMessage(message.getMessage(), chatId, principal.getName());
    }

}
