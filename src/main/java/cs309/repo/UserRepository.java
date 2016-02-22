package cs309.repo;

import cs309.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Integer>{


    @Query("select u from User u where u.email = :email")
    User getUserByEmail(@Param("email") String email);
}
