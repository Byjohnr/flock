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
        Integer id;
        List<NotificationDTO> notificationDtoList = new ArrayList<>();

        for (Notification notification : notificationRepository.getNotificationsByEmail(email)) {
            NotificationDTO notificationDTO = new NotificationDTO();
            if (notification.getType().equals(Notification.EVENT_INVITE)) {
                Event event = eventService.getEvent(notification.getTypeId());
                message = "You have been invited to " + event.getEventName();
                url = "/event/" + notification.getTypeId();
                id = notification.getId();
                notificationDTO.setId(id);
                notificationDTO.setUrl(url);
                notificationDTO.setMessage(message);
                notificationDtoList.add(notificationDTO);
                //urls would be /event/id  /user/{id}
            }
            if (notification.getType().equals(Notification.USER_CONNECTION)) {
                User user = userService.getUser(notification.getTypeId());
                message = user.getFirstName() + " " + user.getLastName() + " wants to be your connection";
                url = "/user/" + user.getId();
                id = notification.getId();
                notificationDTO.setId(id);
                notificationDTO.setUrl(url);
                notificationDTO.setMessage(message);
                notificationDtoList.add(notificationDTO);
            }
        }
    return notificationDtoList;
    }
    public void deleteNotification(Integer id){
        notificationRepository.delete(id);
    }

//    public Notification findNotification(Integer id){
//        return notificationRepository.findOne(id);
//    }
}
