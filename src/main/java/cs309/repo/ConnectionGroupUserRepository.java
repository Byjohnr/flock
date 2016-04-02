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

    @Query("select cgu from ConnectionGroupUser cgu where cgu.connectionGroup.id =:groupId and cgu.user.id =:userId")
    ConnectionGroupUser getConnectionGroupUserByUserIdAndGroupId(@Param("userId") int userId, @Param("groupId") int groupId);
}
