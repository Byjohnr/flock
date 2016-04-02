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
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setDateSent(new Date());
        chatMessage.setChatGroup(chatGroup);
        chatMessage.setMessageSender(chatUser);
        chatMessageRepository.save(chatMessage);
    }

    public void saveChatGroup(ChatGroup chatGroup) {
        chatGroupRepository.save(chatGroup);
    }

    public void saveChatUser(ChatUser chatUser) {
        chatUserRepository.save(chatUser);
    }
}
