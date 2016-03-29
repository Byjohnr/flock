package cs309.repo;

import cs309.data.Event;
import cs309.data.Role;
import cs309.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query("select r from Role r where r.email = :email and r.targetId = :targetId")
    Role getRole(@Param("email") String email, @Param("roleName") String roleName, @Param("targetId") Integer targetId);

}
