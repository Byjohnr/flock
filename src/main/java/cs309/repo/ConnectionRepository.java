package cs309.repo;

import cs309.data.Connection;
import cs309.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection,Integer> {

    @Query("select u from Connection c, User u where c.user1.id = u.id and c.user2.email =:email or c.user2.id = u.id and c.user1.email =:email")
    List<User> getConnections(@Param("email") String email);

    @Query("select count(c) > 0 from Connection c where c.user1 = :user1 and c.user2 = :user2 or c.user1 = :user2 and c.user2 = :user1")
    boolean isConnected(@Param("user1") User user1, @Param("user2") User user2);

    @Modifying
    @Query("delete from Connection c where c.user1 = :user1 and c.user2 =:user2 or c.user1 = :user2 and c.user2 =:user1")
    void deleteConnection(@Param("user1") User user1, @Param("user2") User user2);
}
