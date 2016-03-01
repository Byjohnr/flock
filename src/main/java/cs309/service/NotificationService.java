package cs309.service;

import cs309.data.Notification;
import cs309.dto.NotificationDTO;
import cs309.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    public List<Notification> getNotificationDTOs() {return notificationRepository.findAll();}

}
