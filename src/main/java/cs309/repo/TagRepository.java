package cs309.repo;

import cs309.data.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("select t from Tag t where t.enabled = true")
    List<Tag> getTagsEnabled();
}
