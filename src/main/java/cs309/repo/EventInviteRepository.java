package cs309.repo;

import cs309.data.Event;
import cs309.data.EventInvite;
import cs309.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventInviteRepository extends JpaRepository<EventInvite,Integer> {

    @Query("select i from EventInvite i where i.userInvited = :user and i.event = :event")
    EventInvite findEventInviteByUserAndEvent(@Param("user") User user, @Param("event") Event event);

}
