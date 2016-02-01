package controller;

import cs309.controller.HomeController;
import cs309.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by jeffrey on 1/31/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerUTest {

//    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private HomeController homeController;

    @Test
    public void homePage() {

    }

}
