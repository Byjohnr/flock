package cs309.dto;

import cs309.data.ChatMessage;
import cs309.data.ChatUser;

import java.util.List;

public class ChatGroupDTO {

    private int chatGroupId;
    private String chatName;
    private List<ChatUser> chatUsers;
    private List<ChatMessage> chatMessages;

    public ChatGroupDTO(int chatGroupId, String chatName, List<ChatUser> chatUsers, List<ChatMessage> chatMessages) {
        this.chatGroupId = chatGroupId;
        this.chatName = chatName;
        this.chatUsers = chatUsers;
        this.chatMessages = chatMessages;
    }

    public int getChatGroupId() {
        return chatGroupId;
    }

    public String getChatName() {
        return chatName;
    }

    public List<ChatUser> getChatUsers() {
        return chatUsers;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }
}
