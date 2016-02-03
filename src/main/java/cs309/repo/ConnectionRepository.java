package cs309.repo;

import cs309.data.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jeffrey on 1/31/16.
 */
public interface ConnectionRepository extends JpaRepository<Connection,Integer> {
}
