package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.UnitTestBase;
import cs309.controller.UserRestController;
import cs309.data.Role;
import cs309.data.User;
import cs309.service.RoleService;
import cs309.service.UserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import util.MockData;


import java.security.Principal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserRestControllerUTest extends UnitTestBase {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserRestController userRestController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(userRestController).build();
    }

    @Test
    public void saveUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = MockData.getUser(1);
        String userString = mapper.writeValueAsString(user);
        this.mockMvc.perform(post("/api/user/create").content(userString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(userService, times(1)).saveUser(user);
        verify(roleService, times(1)).createRole("email1", Role.USER,1);
    }

    @Test
    public void getUser() throws Exception {
        when(userService.getUserByEmail(anyString())).thenReturn(MockData.getUser(1));
        this.mockMvc.perform(post("/api/user/info").principal(mock(Principal.class)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)));
    }

    @Test
    public void getOtherUser() throws Exception {
        when(userService.getUserByEmail(anyString())).thenReturn(MockData.getUser(1));
        this.mockMvc.perform(get("/api/user/1").principal(mock(Principal.class)))
                .andExpect(status().isOk());
    }
}
