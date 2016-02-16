package controller;

import config.UnitTestBase;
import cs309.controller.EventRestController;
import cs309.data.Event;
import cs309.service.EventService;
import cs309.service.UserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import util.MockData;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by chasekoehler on 2/15/16.
 */
public class EventRestControllerUTest extends UnitTestBase {
    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @Mock
    private UserService userService;

    @InjectMocks
    private EventRestController eventController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(eventController).build();
    }

    @Test
    public void getEvent() throws Exception {
        when(eventService.getEvent(1)).thenReturn(MockData.getEvent(1));
        this.mockMvc.perform(get("/api/event/1"))
                .andExpect(status().isOk());
                Event event = eventService.getEvent(1);
                assertEquals(event.getEventName(), "event1");
    }
}
