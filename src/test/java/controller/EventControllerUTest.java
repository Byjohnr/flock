package controller;

import config.UnitTestBase;
import cs309.controller.EventController;
import cs309.service.EventService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import util.MockData;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

public class EventControllerUTest extends UnitTestBase {
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(eventController).build();
    }

    @Test
    public void eventPage() throws Exception {
        this.mockMvc.perform(get("/event/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("eventPage"));
    }

    @Test
    public void createEventPage() throws Exception {
        this.mockMvc.perform(get("/event/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createEvent"));
    }
}
