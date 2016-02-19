package service;

import config.UnitTestBase;
import cs309.data.Notification;
import cs309.repo.NotificationRepository;
import cs309.service.NotificationService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.MockData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by John on 2/8/2016.
 */
public class NotificationServiceUTest extends UnitTestBase {

    @Mock
    private NotificationRepository notificationRepo;

    @InjectMocks
    private NotificationService notificationService;


    @Test
    public void getNotifications(){
    when(notificationRepo.findAll()).thenReturn(new ArrayList<>(MockData.getMockNotifications(10)));
    List<Notification> notification = notificationService.getNotifications();
    assertEquals(notification.size(),10);
    }
}
