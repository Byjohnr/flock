package cs309.repo;

import cs309.data.ConnectionGroupUser;
import cs309.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConnectionGroupUserRepository extends JpaRepository<ConnectionGroupUser, Integer> {

    @Query("select u from ConnectionGroupUser c, User u where c.user.id = u.id and c.connectionGroup.id = :groupId")
    List<User> getUsersInConnectionGroup(@Param("groupId") int groupId);

    @Query("select u from ConnectionGroup cg, ConnectionGroupUser cgu, Connection c, User u where (c.user1.id = u.id and c.user2.email =:email or c.user2.id = u.id and c.user1.email =:email) and (cg.id =:groupId and cgu.connectionGroup.id = :groupId) and u.id <> cgu.user.id")
    List<User> getUsersNotInConnectionGroupByGroupIdAndEmail(@Param("groupId") int groupId, @Param("email") String email);
}
