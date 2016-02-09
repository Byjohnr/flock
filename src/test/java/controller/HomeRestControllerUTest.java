package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.UnitTestBase;
import cs309.controller.HomeRestController;
import cs309.data.Event;
import cs309.data.User;
import cs309.dto.CreateEventDTO;
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


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
/**
 * Created by jeffrey on 2/2/16.
 */
public class HomeRestControllerUTest extends UnitTestBase{

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @Mock
    private UserService userService;

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
                .andExpect(jsonPath("$", Matchers.hasSize(4)));
    }

    @Test
    public void createEvent() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CreateEventDTO eventDTO = new CreateEventDTO();
        String string = mapper.writeValueAsString(eventDTO);
        when(userService.getUser(1)).thenReturn(mock(User.class));
        this.mockMvc.perform(post("/api/create")
                    .content(string)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(eventService, times(1)).saveEvent(any(Event.class));
    }

}
