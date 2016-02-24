package cs309.repo;

import cs309.data.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection,Integer> {
}
