package cs309.service;

import cs309.data.Event;
import cs309.data.Notification;
import cs309.data.User;
import cs309.dto.NotificationDTO;
import cs309.repo.NotificationRepository;
import org.aspectj.weaver.ast.Not;
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
            if (notification.getType().equals(Notification.EVENT_INVITE)) {
                Event event = eventService.getEvent(notification.getTypeId());
                notificationDTO= new NotificationDTO("/event/" + notification.getTypeId(),"You have been invited to " + event.getEventName(),notification.getId(),1,notification.getTypeId());
                notificationDtoList.add(notificationDTO);
            }
            if (notification.getType().equals(Notification.USER_CONNECTION)) {
                User user = userService.getUser(notification.getCreator().getId());
                notificationDTO = new NotificationDTO("/user/" + user.getId(),user.getFirstName() + " " + user.getLastName() + " wants to be your connection",notification.getId(),2,notification.getTypeId());
                notificationDtoList.add(notificationDTO);
            }
            if (notification.getType().equals(Notification.ADD_EVENT_ADMIN)){
                User user = userService.getUser(notification.getCreator().getId());
                Event event = eventService.getEvent(notification.getTypeId());
                notificationDTO = new NotificationDTO("/event/" + notification.getTypeId(), user.getFirstName() + " " + user.getLastName() + " added you as an Admin on their event "+ event.getEventName(), notification.getId(),3,notification.getTypeId());
                notificationDtoList.add(notificationDTO);
            }
            if (notification.getType().equals(Notification.ACCEPT_USER_CONNECTION)){
                User user = userService.getUser(notification.getCreator().getId());
                notificationDTO = new NotificationDTO("/user/" + user.getId(), user.getFirstName() + " " + user.getLastName() + " is now your connection ", notification.getId(),4,notification.getTypeId());
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