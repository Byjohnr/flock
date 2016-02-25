package cs309.service;

import cs309.data.PictureFile;
import cs309.repo.PictureFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class PictureFileService {

    private final Logger LOG = LoggerFactory.getLogger(PictureFileService.class);

    @Autowired
    private PictureFileRepository pictureFileRepo;

    public BufferedImage getPictureByFileName(String fileName) {
        PictureFile pictureFile = pictureFileRepo.findByFileName(fileName);
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(pictureFile.getInputStream());
        } catch (IOException ex) {
            LOG.warn("BufferedImage with fileName " + fileName + "was not read from the PictureFile.", ex);
        }
        return bufferedImage;
    }
}
