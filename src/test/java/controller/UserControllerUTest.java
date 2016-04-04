package controller;

import config.UnitTestBase;
import cs309.controller.UserController;
import cs309.data.User;
import cs309.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import util.MockData;

import java.security.Principal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerUTest extends UnitTestBase {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(userController).build();
    }

    @Test
    public void userAccountPage() throws Exception {
        Principal principal = mock(Principal.class);
        this.mockMvc.perform(get("/account").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("viewAccount"));
    }

    @Test
    public void userPage() throws Exception {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("bloop");
        when(userService.getUser(72)).thenReturn(mock(User.class));
        when(userService.getUserByEmail("bloop")).thenReturn(MockData.getUser(2));
        this.mockMvc.perform(get("/user/72").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("userPage"));
    }

    @Test
    public void adminUserPage() throws Exception {
        Principal principal = mock(Principal.class);
        this.mockMvc.perform(get("/admin/user_list").principal(principal))
                .andExpect(status().isOk())
                .andExpect(view().name("adminUserPage"));
    }
}
