package cs309.service;

import cs309.data.PictureFile;
import cs309.repo.PictureFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class PictureFileService {

    private final Logger LOG = LoggerFactory.getLogger(PictureFileService.class);

    @Autowired
    private PictureFileRepository pictureFileRepo;

    public PictureFile savePictureFile(String fileName, Integer userId, Integer eventId, String pictureData) {
        return pictureFileRepo.save(new PictureFile(fileName, userId, eventId, pictureData));
    }

    public PictureFile getPictureFileByFileName(String fileName) {
        return pictureFileRepo.findByFileName(fileName);
    }

    public PictureFile getPictureFileByUserId(Integer userId) {
        return pictureFileRepo.findByUserId(userId);
    }

    public PictureFile getPictureFileByEventId(Integer eventId) {
        return pictureFileRepo.findByEventId(eventId);
    }
}
