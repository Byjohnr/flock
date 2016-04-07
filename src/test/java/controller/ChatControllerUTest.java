package controller;

import config.UnitTestBase;
import cs309.controller.ChatController;
import cs309.controller.ConnectionController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ChatControllerUTest extends UnitTestBase {

    private MockMvc mockMvc;

    @InjectMocks
    private ChatController chatController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(chatController).build();
    }

    @Test
    public void chatListPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chat/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("chatListPage"));
    }

    @Test
    public void chatGroupPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/chat/group/2"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("chatGroupPage"));
    }
}
