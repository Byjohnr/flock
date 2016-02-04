package controller;

import config.UnitTestBase;
import cs309.controller.HomeRestController;
import cs309.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import util.MockData;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
/**
 * Created by jeffrey on 2/2/16.
 */
public class HomeRestControllerUTest extends UnitTestBase{

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private HomeRestController homeController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(homeController).build();
    }

    @Test
    public void getEvents() throws Exception {
        when(eventService.getEvents()).thenReturn(MockData.getMockEvents(4));
        this.mockMvc.perform(get("/api/events").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$",hasSize(4)));
    }

}
