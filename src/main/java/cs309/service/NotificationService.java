package cs309.service;

import cs309.data.Event;
import cs309.data.Notification;
import cs309.data.User;
import cs309.dto.NotificationDTO;
import cs309.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;


    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    public List<NotificationDTO> getNotificationDTOs(String email) {
        String message;
        String url;
        List<NotificationDTO> notificationDtoList = new ArrayList<>();

        for (Notification notification : notificationRepository.getNotificationsByEmail(email)) {
            NotificationDTO notificationDTO = new NotificationDTO();
            if (notification.getType().equals(Notification.EVENTINVITE)) {
                Event event = eventService.getEvent(notification.getTypeId());
                message = "You have been invited to " + event.getEventName();
                url = "/event/" + notification.getTypeId();
                notificationDTO.setUrl(url);
                notificationDTO.setMessage(message);
                notificationDtoList.add(notificationDTO);
                //urls would be /event/id  /user/{id}
            }
            if (notification.getType().equals(Notification.USERCONNECTION)) {
                User user = userService.getUser(notification.getTypeId());
                message = user.getFirstName() + user.getLastName() + " wants to be your connection";
                url = "/user/" + user.getId();
                notificationDTO.setUrl(url);
                notificationDTO.setMessage(message);
                notificationDtoList.add(notificationDTO);
            }
        }
    return notificationDtoList;
    }
}
