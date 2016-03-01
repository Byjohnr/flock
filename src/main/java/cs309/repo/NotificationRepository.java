package cs309.repo;

import cs309.data.Event;
import cs309.data.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface NotificationRepository extends JpaRepository<Notification,Integer> {
    @Query("select n from Notification n where n.reciever.email = : email")
    List<Notification> getNotificationsByEmail(@Param("email")String email);
}
