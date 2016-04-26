package controller;

import config.UnitTestBase;
import cs309.controller.ChatRestController;
import cs309.data.ChatGroup;
import cs309.data.ChatUser;
import cs309.service.ChatService;
import cs309.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import util.MockData;

import java.security.Principal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ChatRestControllerUTest extends UnitTestBase{

    private MockMvc mockMvc;

    @Mock
    private ChatService chatService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ChatRestController chatRestController;

    private Principal principal = mock(Principal.class);

    @Before
    public void set() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(chatRestController).build();
    }

    @Test
    public void getChatGroups() throws Exception {
        when(userService.getUserByEmail(principal.getName())).thenReturn(MockData.getUser(2));
        mockMvc.perform(get("/api/chat/list").principal(principal))
                .andExpect(status().isOk());
    }

    @Test
    public void getChatGroup() throws Exception {
        when(chatService.getChatGroupById(32)).thenReturn(MockData.getChatGroup(3));
        mockMvc.perform(get("/api/chat/group/32"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateChatName() throws Exception {
        when(chatService.getChatGroupById(32)).thenReturn(MockData.getChatGroup(3));
        mockMvc.perform(post("/api/chat/group/32/updateName").content("newGroupName"))
                .andExpect(status().isOk());
        verify(chatService, times(1)).saveChatGroup(any(ChatGroup.class));
    }

    @Test
    public void inviteUser() throws Exception {
        when(chatService.getChatGroupById(123)).thenReturn(MockData.getChatGroup(4));
        mockMvc.perform(post("/api/chat/group/123/invite").content("5"))
                .andExpect(status().isOk());
        verify(chatService, times(1)).saveChatUser(any(ChatUser.class));
    }

    @Test
    public void createChatGroup() throws Exception {
        mockMvc.perform(post("/api/chat/group/create").content("huzzah").principal(principal))
                .andExpect(status().isOk());
        verify(chatService, times(1)).saveChatGroup(any(ChatGroup.class));
        verify(chatService, times(1)).saveChatUser(any(ChatUser.class));
    }

    @Test
    public void respondToChatInvite() throws Exception {
        when(chatService.getChatUserById(1234)).thenReturn(MockData.getChatUser(45));
        mockMvc.perform(post("/api/chat/1234/respond").content("accept"))
                .andExpect(status().isOk());
        verify(chatService, times(1)).saveChatUser(any(ChatUser.class));
    }

}
