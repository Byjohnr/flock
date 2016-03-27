package service;

import config.UnitTestBase;
import cs309.data.PictureFile;
import cs309.repo.PictureFileRepository;
import cs309.service.PictureFileService;
import org.junit.After;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PictureFileServiceUTest extends UnitTestBase {

    @Mock
    private PictureFileRepository pictureFileRepo;
    @InjectMocks
    private PictureFileService pictureFileService;

    @Test
    public void savePictureFileForUser() {
        Integer user5 = 5;
        Integer notUser5 = 3;
        PictureFile foundPictureFile = new PictureFile("foundPictureFile", user5, null, "a default picture here");
        foundPictureFile.setId(user5);
        PictureFile returnedPictureFile = new PictureFile("returnedPictureFile", user5, null, "a totally different picture");
        returnedPictureFile.setId(user5);
        PictureFile newPictureFile = new PictureFile("newPictureFile", notUser5, null, "a new default picture");
        newPictureFile.setId(notUser5);

        when(pictureFileRepo.findByUserId(user5)).thenReturn(foundPictureFile);
        when(pictureFileRepo.save(returnedPictureFile)).thenReturn(returnedPictureFile);
        when(pictureFileRepo.save(newPictureFile)).thenReturn(newPictureFile);

        PictureFile methodReturn = pictureFileService.savePictureFileForUser(returnedPictureFile.getFileName(), user5, returnedPictureFile.getPicture());
        assertEquals(returnedPictureFile, methodReturn);
        verify(pictureFileRepo, times(1)).findByUserId(user5);
        verify(pictureFileRepo, times(1)).save(returnedPictureFile);

        methodReturn = pictureFileService.savePictureFileForUser(newPictureFile.getFileName(), notUser5, newPictureFile.getPicture());
        assertEquals(newPictureFile, methodReturn);
        verify(pictureFileRepo, times(1)).findByUserId(notUser5);
        verify(pictureFileRepo, times(1)).save(newPictureFile);

        verifyNoMoreInteractions(pictureFileRepo);
    }

    @Test
    public void savePictureFileForEvent() {
        Integer event5 = 5;
        Integer notEvent5 = 3;
        PictureFile foundPictureFile = new PictureFile("foundPictureFile", null, event5, "a default picture here");
        PictureFile returnedPictureFile = new PictureFile("returnedPictureFile", null, event5, "a totally different picture");
        PictureFile newPictureFile = new PictureFile("newPictureFile", null, notEvent5, "a new default picture");

        when(pictureFileRepo.findByEventId(event5)).thenReturn(foundPictureFile);
        when(pictureFileRepo.save(returnedPictureFile)).thenReturn(returnedPictureFile);
        when(pictureFileRepo.save(newPictureFile)).thenReturn(newPictureFile);

        PictureFile methodReturn = pictureFileService.savePictureFileForEvent(returnedPictureFile.getFileName(), event5, returnedPictureFile.getPicture());
        assertEquals(returnedPictureFile, methodReturn);
        verify(pictureFileRepo, times(1)).findByEventId(event5);
        verify(pictureFileRepo, times(1)).save(returnedPictureFile);

        methodReturn = pictureFileService.savePictureFileForEvent(newPictureFile.getFileName(), notEvent5, newPictureFile.getPicture());
        assertEquals(newPictureFile, methodReturn);
        verify(pictureFileRepo, times(1)).findByEventId(notEvent5);
        verify(pictureFileRepo, times(1)).save(newPictureFile);

        verifyNoMoreInteractions(pictureFileRepo);
    }

    @Test
    public void getPictureByFileName() {
        PictureFile testPicture = new PictureFile("testPicture.jpg", 5, null, "default picture string");
        when(pictureFileRepo.findByFileName(anyString())).thenReturn(testPicture);
        PictureFile methodReturn = pictureFileService.getPictureFileByFileName("testPicture.jpg");
        assertEquals(testPicture, methodReturn);
        verify(pictureFileRepo, times(1)).findByFileName(anyString());
        verifyNoMoreInteractions(pictureFileRepo);
    }

    @Test
    public void getPictureByUserId() {
        Integer userId = 3;
        PictureFile testPicture = new PictureFile("testPicture.jpg", userId, null, "default picture string");
        when(pictureFileRepo.findByUserId(userId)).thenReturn(testPicture);
        PictureFile methodReturn = pictureFileService.getPictureFileByUserId(userId);
        assertEquals(testPicture, methodReturn);
        verify(pictureFileRepo, times(1)).findByUserId(userId);
        verifyNoMoreInteractions(pictureFileRepo);
    }

    @Test
    public void getPictureByEventId() {
        Integer eventId = 4;
        PictureFile testPicture = new PictureFile("testPicture.jpg", null, eventId, "default picture string");
        when(pictureFileRepo.findByEventId(eventId)).thenReturn(testPicture);
        PictureFile methodReturn = pictureFileService.getPictureFileByEventId(eventId);
        assertEquals(testPicture, methodReturn);
        verify(pictureFileRepo, times(1)).findByEventId(eventId);
        verifyNoMoreInteractions(pictureFileRepo);
    }

    @After
    public void resetMocks() {
        reset(pictureFileRepo);
    }
}
