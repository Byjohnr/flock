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
        List<NotificationDTO> notificationDtoList = new ArrayList<>();

        for (Notification notification : notificationRepository.getNotificationsByEmail(email)) {
            NotificationDTO notificationDTO;
            if (notification.getType().equals(Notification.EVENT_INVITE)) {
                Event event = eventService.getEvent(notification.getTypeId());
                notificationDTO= new NotificationDTO("/event/" + notification.getTypeId(),"You have been invited to " + event.getEventName(),notification.getId());
                notificationDtoList.add(notificationDTO);
            }
            if (notification.getType().equals(Notification.USER_CONNECTION)) {
                User user = userService.getUser(notification.getTypeId());
                notificationDTO = new NotificationDTO("/user/" + user.getId(),user.getFirstName() + " " + user.getLastName() + " wants to be your connection",notification.getId());

                notificationDtoList.add(notificationDTO);
            }
        }
    return notificationDtoList;
    }
    public void deleteNotification(Notification notification){
        notificationRepository.delete(notification);
    }
    public Notification getNotification(int id){
        return notificationRepository.findOne(id);
    }

}