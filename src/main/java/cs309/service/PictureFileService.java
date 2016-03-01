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

    public PictureFile savePictureFile(File file) {
        PictureFile returnedPictureFile = null;
        if (file != null && file.exists() && file.canRead()) {
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                String fileType = file.getName().substring(file.getName().indexOf('.') + 1);
                ImageIO.write(ImageIO.read(file), fileType, outputStream);
                returnedPictureFile = pictureFileRepo.save(new PictureFile(file.getName(), outputStream.toByteArray()));
            } catch (IOException ex) {
                LOG.error("Reading file with filename " + file.getName() + " threw IOException in PictureFileService method savePictureFile", ex);
            }
        }
        return returnedPictureFile;
    }

    public BufferedImage getPictureByFileName(String fileName) {
        PictureFile pictureFile = pictureFileRepo.findByFileName(fileName);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(pictureFile.getPicture()));
        } catch (IOException ex) {
            LOG.warn("BufferedImage with fileName " + fileName + "was not read from the PictureFile.", ex);
        }
        return bufferedImage;
    }

    public BufferedImage getPictureByUserId(Integer userId) {
        PictureFile pictureFile = pictureFileRepo.findByUserId(userId);
        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(pictureFile.getPicture()));
        } catch (IOException ex) {
            LOG.warn("BufferedImage with userId " + userId + "was not read from the PictureFile " + pictureFile.getFileName() + ".", ex);
        }
        return bufferedImage;
    }

    public BufferedImage getPictureByEventId(Integer eventId) {
        PictureFile pictureFile = pictureFileRepo.findByEventId(eventId);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(pictureFile.getPicture()));
        } catch (IOException ex) {
            LOG.warn("BufferedImage with eventId " + eventId + "was not read from the PictureFile " + pictureFile.getFileName() + ".", ex);
        }
        return bufferedImage;
    }
}
