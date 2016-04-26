package cs309.service;

import cs309.data.Event;
import cs309.data.Notification;
import cs309.data.User;
import cs309.dto.NotificationDTO;
import cs309.repo.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Notification saveNotification (Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    public List<NotificationDTO> getNotificationDTOs(String email) {
        List<NotificationDTO> notificationDtoList = new ArrayList<>();

        for (Notification notification : notificationRepository.getNotificationsByEmail(email)) {
            NotificationDTO notificationDTO;
            User creator = notification.getCreator();
            if (notification.getType().equals(Notification.EVENT_INVITE)) {
                Event event = eventService.getEvent(notification.getTypeId());
                notificationDTO= new NotificationDTO("/event/" + notification.getTypeId(),"You have been invited to " + event.getEventName(),notification.getId(),notification.getType(),notification.getTypeId(), creator);
                notificationDtoList.add(notificationDTO);
            } else if (notification.getType().equals(Notification.USER_CONNECTION)) {
                notificationDTO = new NotificationDTO("/user/" + creator.getId(),creator.getFirstName() + " " + creator.getLastName() + " wants to be your connection",notification.getId(),notification.getType(),notification.getTypeId(), creator);
                notificationDtoList.add(notificationDTO);
            } else if (notification.getType().equals(Notification.ADD_EVENT_ADMIN)){
                Event event = eventService.getEvent(notification.getTypeId());
                notificationDTO = new NotificationDTO("/event/" + notification.getTypeId(), creator.getFirstName() + " " + creator.getLastName() + " added you as an Admin on their event "+ event.getEventName(), notification.getId(),notification.getType(),notification.getTypeId(), creator);
                notificationDtoList.add(notificationDTO);
            } else if (notification.getType().equals(Notification.ACCEPT_USER_CONNECTION)){
                notificationDTO = new NotificationDTO("/user/" + creator.getId(), creator.getFirstName() + " " + creator.getLastName() + " is now your connection ", notification.getId(),notification.getType(),notification.getTypeId(), creator);
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