package cs309.repo;

import cs309.data.Connection;
import cs309.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection,Integer> {

    @Query("select u from Connection c, User u where c.user1.id = u.id and c.user2.email =:email or c.user2.id = u.id and c.user1.email =:email")
    List<User> getConnections(@Param("email") String email);
}
