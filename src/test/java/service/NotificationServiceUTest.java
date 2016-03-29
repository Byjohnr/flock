package service;

import config.UnitTestBase;
import cs309.data.Notification;
import cs309.dto.NotificationDTO;
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
//    @Test
//    public void deleteNotification() {
//        when(notificationRepo.findAll()).thenReturn(new ArrayList<>(MockData.getMockNotifications(10)));
//        List<Notification> notification = notificationService.getNotifications();
//        notificationService.deleteNotification(1);
//        assertEquals(notification.size(), 9);
//    }
//    @Test
//    public void getNotificationDTOs(String email) {
//        when(notificationRepo.findAll()).thenReturn(new ArrayList<>(MockData.getMockNotificationDTOs(email)));
//        List<NotificationDTO> notificationDTOs = notificationService.getNotificationDTOs(email);
//        assertEquals(notificationDTOs.size(), 10);
//    }
}
