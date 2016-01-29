package cs309.repo;

import cs309.data.Event;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jeffrey on 1/28/16.
 */
public interface EventRepository extends JpaRepository<Event,Long> {
}
