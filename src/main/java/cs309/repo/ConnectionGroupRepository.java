package cs309.repo;

import cs309.data.ConnectionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConnectionGroupRepository extends JpaRepository<ConnectionGroup,Integer> {

    @Query("select c from ConnectionGroup c where c.user.email = :email")
    List<ConnectionGroup> getConnectionGroupsByEmail(@Param("email") String email);

}
