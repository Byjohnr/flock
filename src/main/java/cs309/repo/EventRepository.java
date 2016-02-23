package cs309.repo;

import cs309.data.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event,Integer> {
}
