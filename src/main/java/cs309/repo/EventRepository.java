package cs309.repo;

import cs309.data.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EventRepository extends JpaRepository<Event,Integer>, JpaSpecificationExecutor<Event> {

    @Query("select e from Event e where e.type = 1")
    List<Event> getOpenEvents();

    @Query("select e from Event e where e.type = 2")
    List<Event> getConnectionsOnlyEvents();

    @Query("select e from Event e where e.tag.id = :tagId")
    List<Event> getEventsByTagId(@Param("tagId") Integer tagId);
}
