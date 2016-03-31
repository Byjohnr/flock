package cs309.repo;

import cs309.data.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface EventRepository extends JpaRepository<Event,Integer>, JpaSpecificationExecutor<Event> {
}
