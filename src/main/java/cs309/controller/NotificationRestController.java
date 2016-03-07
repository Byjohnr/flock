package cs309.controller;

import cs309.data.Notification;
import cs309.dto.NotificationDTO;
import cs309.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2/18/2016.
 */
@RestController
public class NotificationRestController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping ("/api/notifications")
    public List<NotificationDTO> getNotifications(Principal principal) {
        return notificationService.getNotificationDTOs(principal.getName());
    }
    //@RequestMapping ("/api/notifications")
    public void deleteNotification(Integer id){
        notificationService.deleteNotification(id);
    }
}
