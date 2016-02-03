package cs309.repo;

import cs309.data.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chasekoehler on 2/1/16.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
