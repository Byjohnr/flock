package cs309.repo;

import cs309.data.Event;
import cs309.data.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by John on 2/3/2016.
 */
public interface NotificationRepository extends JpaRepository<Notification,Integer> {

}
