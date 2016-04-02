package cs309.service;

import cs309.data.ChatGroup;
import cs309.data.ChatMessage;
import cs309.data.ChatUser;
import cs309.repo.ChatGroupRepository;
import cs309.repo.ChatMessageRepository;
import cs309.repo.ChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ChatService {

    @Autowired
    private ChatGroupRepository chatGroupRepository;

    @Autowired
    private ChatUserRepository chatUserRepository;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatGroup getChatGroupById(int id) {
        return chatGroupRepository.findOne(id);
    }

    public void saveChatMessage(String message, int chatGroupId, String userEmail) {
        ChatGroup chatGroup = chatGroupRepository.findOne(chatGroupId);
        ChatUser chatUser = chatUserRepository.getChatUserByUserEmailAndChatGroupId(userEmail, chatGroupId);
        ChatMessage chatMessage = new ChatMessage(message, chatGroup, chatUser);
        chatMessageRepository.save(chatMessage);
    }

    public ChatGroup saveChatGroup(ChatGroup chatGroup) {
        return chatGroupRepository.save(chatGroup);
    }

    public void saveChatUser(ChatUser chatUser) {
        chatUserRepository.save(chatUser);
    }

    public ChatUser getChatUserByEmailAndGroupId(String email, int groupId) {
        return chatUserRepository.getChatUserByUserEmailAndChatGroupId(email, groupId);
    }

    public ChatUser getChatUserById(int chatUserId) {
        return chatUserRepository.findOne(chatUserId);
    }

    public void deleteChatUser(ChatUser chatUser) {
        chatUserRepository.delete(chatUser);
    }
}
