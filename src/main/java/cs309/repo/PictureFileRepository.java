package cs309.repo;

import cs309.data.PictureFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.NotNull;

public interface PictureFileRepository extends JpaRepository<PictureFile, Integer> {

    @Query("select p from PictureFile p where p.fileName = :fileName")
    PictureFile findByFileName(@Param("fileName") String fileName);

    @Query("select p from PictureFile p where p.userId = :userId")
    PictureFile findByUserId(@Param("userId") @NotNull Integer userId);

    @Query("select p from PictureFile p where p.eventId = :eventId")
    PictureFile findByEventId(@Param("eventId") @NotNull Integer eventId);
}