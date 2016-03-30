package cs309.repo;

import cs309.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.currentCity = :currentCity")
    List<User> findByCurrentCity(@Param("currentCity") String currentCity);

    @Query("select u from User u where u.email = :email")
    User findUserByEmail(@Param("email") String email);
}