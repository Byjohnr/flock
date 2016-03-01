package cs309.service;

import cs309.data.Event;
import cs309.data.Notification;
import cs309.data.User;
import cs309.dto.NotificationDTO;
import cs309.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EventService eventService;
    private UserService userService;


    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    public List<Notification> getNotificationDTOs(String email) {
        String message;
        String url;
        for (Notification notification : notificationRepository.getNotificationsByEmail(email)) {
            if(notification.getType().equals(Notification.EVENTINVITE)){
                Event event = eventService.getEvent(notification.getTypeId());
                message = "You have been invited to " + event.getEventName();
                url = "/event/"+notification.getTypeId();
                //urls would be /event/id  /user/{id}
            }
            if (notification.getType().equals(Notification.USERCONNECTION)) {
                User user = userService.getUser(notification.getTypeId());
                message = user.getFirstName()+user.getLastName()+" wants to be a connection";
                url = "/user/"+user.getId();
            }
        }
        return
    }
}
