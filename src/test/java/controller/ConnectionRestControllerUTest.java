package controller;

import config.UnitTestBase;
import cs309.controller.ConnectionRestController;
import cs309.service.ConnectionService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import util.MockData;

import java.security.Principal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ConnectionRestControllerUTest extends UnitTestBase {

    private MockMvc mockMvc;

    @Mock
    private ConnectionService connectionService;

    @InjectMocks
    private ConnectionRestController connectionRestController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(connectionRestController).build();
    }

    @Test
    public void getConnections() throws Exception{
        Principal principal = mock(Principal.class);
        when(connectionService.getConnections(principal.getName())).thenReturn(MockData.getUsers(4));
        this.mockMvc.perform(get("/api/connections/get").accept(MediaType.APPLICATION_JSON).principal(principal))

                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", Matchers.hasSize(4)));
    }

}
