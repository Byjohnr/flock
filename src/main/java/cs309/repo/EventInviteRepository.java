package cs309.repo;

import cs309.data.EventInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EventInviteRepository extends JpaRepository<EventInvite,Integer> {

    @Query("select count(e) > 0 from EventInvite e where e.event = :eventId and e.userInvited = :userId")
    boolean userInviteExists(@Param("eventId") int eventId, @Param("userId") int userId);
}
