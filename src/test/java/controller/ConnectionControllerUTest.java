package controller;

import config.UnitTestBase;
import cs309.controller.ConnectionController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ConnectionControllerUTest extends UnitTestBase {

    private MockMvc mockMvc;

    @InjectMocks
    private ConnectionController connectionController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(connectionController).build();
    }

    @Test
    public void connectionGroupListPage() throws Exception {
//        TODO
    }

    @Test
    public void connectionGroupPage() throws Exception {
//        TODO
    }
}
