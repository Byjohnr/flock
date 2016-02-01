package cs309.repo;

import cs309.data.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jeffrey on 1/31/16.
 */
public interface InviteRepository extends JpaRepository<Invite,Long> {
}
