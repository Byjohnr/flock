package cs309.service;

import cs309.data.PictureFile;
import cs309.repo.PictureFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureFileService {

    private final Logger LOG = LoggerFactory.getLogger(PictureFileService.class);

    @Autowired
    private PictureFileRepository pictureFileRepo;

    public PictureFile savePictureFileForUser(String fileName, Integer userId, String pictureData) {
        PictureFile returnedPictureFile = pictureFileRepo.findByUserId(userId);
        if (returnedPictureFile == null) {
            returnedPictureFile = new PictureFile(fileName, userId, null, pictureData);
        } else {
            returnedPictureFile.setFileName(fileName);
            returnedPictureFile.setPicture(pictureData);
        }
        return pictureFileRepo.save(returnedPictureFile);
    }

    public PictureFile savePictureFileForEvent(String fileName, Integer eventId, String pictureData) {
        PictureFile returnedPictureFile = pictureFileRepo.findByUserId(eventId);
        if (returnedPictureFile == null) {
            returnedPictureFile = new PictureFile(fileName, null, eventId, pictureData);
        } else {
            returnedPictureFile.setFileName(fileName);
            returnedPictureFile.setPicture(pictureData);
        }
        return pictureFileRepo.save(returnedPictureFile);
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
