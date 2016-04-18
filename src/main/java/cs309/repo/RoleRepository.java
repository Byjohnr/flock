package cs309.repo;

import cs309.data.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query("select r from Role r where r.email = :email and r.roleName = :roleName and r.targetId = :targetId")
    Role getRole(@Param("email") String email, @Param("roleName") String roleName, @Param("targetId") Integer targetId);

    @Query("select r from Role r where r.email = :email")
    List<Role> getRolesByEmail(@Param("email") String email);

    @Query("select r from Role r where r.email = :email and r.roleName = 'ROLE_ADMIN'")
    Role getAdmin(@Param("email") String email);
}
