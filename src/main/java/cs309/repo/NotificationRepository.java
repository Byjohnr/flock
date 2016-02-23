package cs309.repo;

import cs309.data.Event;
import cs309.data.Notification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<Notification,Integer> {
}
