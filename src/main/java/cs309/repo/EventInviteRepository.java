package cs309.repo;

import cs309.data.EventInvite;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jeffrey on 1/31/16.
 */
public interface EventInviteRepository extends JpaRepository<EventInvite,Integer> {
}
