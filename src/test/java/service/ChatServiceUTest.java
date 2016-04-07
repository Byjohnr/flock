package service;

import config.UnitTestBase;
import cs309.data.ChatGroup;
import cs309.data.ChatMessage;
import cs309.data.ChatUser;
import cs309.repo.ChatGroupRepository;
import cs309.repo.ChatMessageRepository;
import cs309.repo.ChatUserRepository;
import cs309.service.ChatService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.MockData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ChatServiceUTest extends UnitTestBase {

    @Mock
    private ChatGroupRepository chatGroupRepository;

    @Mock
    private ChatUserRepository chatUserRepository;

    @Mock
    private ChatMessageRepository chatMessageRepository;

    @InjectMocks
    private ChatService chatService;

    @Test
    public void getChatGroupById() {
        when(chatGroupRepository.findOne(1)).thenReturn(MockData.getChatGroup(1));
        ChatGroup chatGroup = chatService.getChatGroupById(1);
        assertNotNull(chatGroup);
    }

    @Test
    public void saveChatMessage() {
        int charGroupId = 1;
        String userEmail = "yippy";
        String message = "cool! that sounds fun!";

        when(chatGroupRepository.findOne(charGroupId)).thenReturn(MockData.getChatGroup(4));
        when(chatUserRepository.getChatUserByUserEmailAndChatGroupId(userEmail, charGroupId)).thenReturn(MockData.getChatUser(3));
        chatService.saveChatMessage(message, charGroupId, userEmail);
        verify(chatMessageRepository, times(1)).save(any(ChatMessage.class));
    }

    @Test
    public void saveChatGroup() {
        ChatGroup chatGroup = MockData.getChatGroup(2);
        chatService.saveChatGroup(chatGroup);
        verify(chatGroupRepository, times(1)).save(chatGroup);
    }

    @Test
    public void saveChatUser() {
        ChatUser chatUser = MockData.getChatUser(3);
        chatService.saveChatUser(chatUser);
        verify(chatUserRepository, times(1)).save(chatUser);
    }

    @Test
    public void getChatUserByEmailAndGroupId() {
        when(chatUserRepository.getChatUserByUserEmailAndChatGroupId("EMAILY", 1234)).thenReturn(MockData.getChatUser(5));
        ChatUser chatUser = chatService.getChatUserByEmailAndGroupId("EMAILY", 1234);
        assertNotNull(chatUser);
    }

    @Test
    public void getChatUserById() {
        when(chatUserRepository.findOne(666)).thenReturn(MockData.getChatUser(333));
        ChatUser chatUser = chatService.getChatUserById(666);
        assertNotNull(chatUser);
    }

    @Test
    public void deleteChatUser() {
        ChatUser chatUser = new ChatUser();
        chatService.deleteChatUser(chatUser);
        verify(chatUserRepository, times(1)).delete(any(ChatUser.class));
    }
}
