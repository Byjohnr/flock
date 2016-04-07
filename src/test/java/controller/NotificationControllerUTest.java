package controller;

import config.UnitTestBase;
import cs309.controller.NotificationController;
import cs309.service.NotificationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import util.MockData;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class NotificationControllerUTest extends UnitTestBase {
    private MockMvc mockMvc;

    @InjectMocks
    private NotificationController notificationController;

    @Mock
    private NotificationService notificationService;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(notificationController).build();
    }

    @Test
    public void notificationPage() throws Exception {
        this.mockMvc.perform(get("/notifications"))
                .andExpect(status().isOk())
                .andExpect(view().name("notificationsPage"));
    }

}