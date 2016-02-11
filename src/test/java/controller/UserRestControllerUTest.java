package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.UnitTestBase;
import cs309.controller.UserRestController;
import cs309.data.User;
import cs309.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.ObjectError;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
/**
 * Created by jeffrey on 2/8/16.
 */
public class UserRestControllerUTest extends UnitTestBase {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserRestController userRestController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(userRestController).build();
    }

    @Test
    public void saveUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        String userString = mapper.writeValueAsString(user);
        this.mockMvc.perform(post("/api/user/create").content(userString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(userService, times(1)).saveUser(user);
    }
}
