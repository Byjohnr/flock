package cs309.repo;

import cs309.data.ConnectionRequest;
import cs309.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Integer> {

    @Query("select count(c) > 0 from ConnectionRequest c where c.userSending = :user1 and c.userReceiving = :user2")
    boolean hasRequested(@Param("user1") User user1, @Param("user2") User user2);

    @Modifying
    @Query("delete from ConnectionRequest c where c.userReceiving =:receiver and c.userSending =:sender")
    void deleteConnectionRequest( @Param("receiver") User receiver, @Param("sender") User sender);
}
