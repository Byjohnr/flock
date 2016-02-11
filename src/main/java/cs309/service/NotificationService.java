package cs309.service;

import cs309.data.Notification;
import cs309.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by John on 2/3/2016.
 */
@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

}
