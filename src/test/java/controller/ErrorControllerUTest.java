package controller;

import config.UnitTestBase;
import cs309.controller.ErrorController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class ErrorControllerUTest extends UnitTestBase{
    private MockMvc mockMvc;

    @InjectMocks
    private ErrorController ErrorController;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(ErrorController).build();
    }

    @Test
    public void Error403Page() throws Exception {
        this.mockMvc.perform(get("/403"))
                .andExpect(status().isOk())
                .andExpect(view().name("Error403Page"));
    }
    @Test
    public void Error404Page() throws Exception {
        this.mockMvc.perform(get("/404"))
                .andExpect(status().isOk())
                .andExpect(view().name("Error404Page"));
    }

}
