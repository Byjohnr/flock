package cs309.repo;

import cs309.data.PictureFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PictureFileRepository extends JpaRepository<PictureFile, Integer> {

    @Query("select p from PictureFile p where p.fileName = :fileName")
    PictureFile findByFileName(@Param("fileName") String fileName);
}