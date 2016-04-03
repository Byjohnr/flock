package service;

import config.UnitTestBase;
import cs309.data.Notification;
import cs309.repo.NotificationRepository;
import cs309.service.NotificationService;
import org.junit.After;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import util.MockData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

public class NotificationServiceUTest extends UnitTestBase {

    @Mock
    private NotificationRepository notificationRepo;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    public void getNotifications() {
        when(notificationRepo.findAll()).thenReturn(new ArrayList<>(MockData.getMockNotifications(10)));
        List<Notification> notification = notificationService.getNotifications();
        assertEquals(notification.size(), 10);
    }

    @After
    public void resetMocks() {
        reset(notificationRepo);
    }
    @Test
    public void deleteNotification() {
        Notification notification = MockData.getNotification(1);
        notificationService.deleteNotification(notification);
        verify(notificationRepo,times(1)).delete(notification);
    }
}
