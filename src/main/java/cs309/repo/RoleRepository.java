package cs309.repo;

import cs309.data.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jeffrey on 2/21/16.
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {

}
