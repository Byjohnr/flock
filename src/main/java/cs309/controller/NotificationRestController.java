package cs309.controller;

import cs309.dto.NotificationDTO;
import cs309.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class NotificationRestController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping ("/api/notifications")
    public List<NotificationDTO> getNotifications(Principal principal) {
        return notificationService.getNotificationDTOs(principal.getName());
    }
    @RequestMapping (value = "/api/notifications/delete", method = RequestMethod.POST)
    public void deleteNotification(@RequestBody Integer id){
        notificationService.deleteNotification(notificationService.getNotification(id));
    }

}
