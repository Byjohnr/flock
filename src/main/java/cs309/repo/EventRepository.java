package cs309.repo;

import cs309.data.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface EventRepository extends JpaRepository<Event,Integer>, JpaSpecificationExecutor<Event> {

    @Query("select e.location from Event e where e.tag is not null and e.tag.tagName = 'public'")
    List<String> findPublicEventAddresses();
}
