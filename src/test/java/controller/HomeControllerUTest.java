package controller;

import config.UnitTestBase;
import cs309.controller.HomeController;
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
/**
 * Created by jeffrey on 1/31/16.
 */
public class HomeControllerUTest extends UnitTestBase{


    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private HomeController homeController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(homeController).build();
    }


    @Test
    public void homePage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    public void mainPage() throws Exception {
        this.mockMvc.perform(get("/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("mainPage"));
    }
}
