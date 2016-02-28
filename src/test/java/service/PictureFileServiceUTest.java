package service;

import com.sun.imageio.plugins.jpeg.JPEGImageReader;
import config.UnitTestBase;
import cs309.data.PictureFile;
import cs309.repo.PictureFileRepository;
import cs309.service.PictureFileService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class PictureFileServiceUTest extends UnitTestBase {

    private File filePicture;

    @Mock
    private PictureFileRepository pictureFileRepo;
    @InjectMocks
    private PictureFileService pictureFileService;

    @Before
    public void setup() {
        filePicture = new File("test.txt");
        boolean d = filePicture.canRead();
        boolean j = filePicture.exists();
    }

    @Test
    public void getPictureByFileName() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String[] fileNameArr = filePicture.getName().split(".");
        ImageIO.write(ImageIO.read(filePicture), fileNameArr[fileNameArr.length - 1], outputStream);
        PictureFile testPicture = new PictureFile("testPicture.jpg", new ByteArrayInputStream(outputStream.toByteArray()));
        testPicture.setId(2);
        when(pictureFileRepo.findByFileName(anyString())).thenReturn(testPicture);
        BufferedImage methodReturn = pictureFileService.getPictureByFileName("testPicture.jpg");
        assertEquals(ImageIO.read(filePicture), methodReturn);
        verify(pictureFileRepo, times(1)).findByFileName(anyString());
        verifyNoMoreInteractions(pictureFileRepo);
    }

    @Test
    public void getPictureByUserId() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String[] fileNameArr = filePicture.getName().split(".");
        ImageIO.write(ImageIO.read(filePicture), fileNameArr[fileNameArr.length - 1], outputStream);
        PictureFile testPicture = new PictureFile("testPicture.jpg", new ByteArrayInputStream(outputStream.toByteArray()));
        testPicture.setUserId(3);
        when(pictureFileRepo.findByUserId(anyInt())).thenReturn(testPicture);
        BufferedImage methodReturn = pictureFileService.getPictureByUserId(3);
        assertEquals(ImageIO.read(filePicture), methodReturn);
        verify(pictureFileRepo, times(1)).findByUserId(anyInt());
        verifyNoMoreInteractions(pictureFileRepo);
    }

    @Test
    public void getPictureByEventId() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String[] fileNameArr = filePicture.getName().split(".");
        ImageIO.write(ImageIO.read(filePicture), fileNameArr[fileNameArr.length - 1], outputStream);
        PictureFile testPicture = new PictureFile("testPicture.jpg", new ByteArrayInputStream(outputStream.toByteArray()));
        testPicture.setEventId(4);
        when(pictureFileRepo.findByEventId(anyInt())).thenReturn(testPicture);
        BufferedImage methodReturn = pictureFileService.getPictureByEventId(4);
        assertEquals(ImageIO.read(filePicture), methodReturn);
        verify(pictureFileRepo, times(1)).findByEventId(anyInt());
        verifyNoMoreInteractions(pictureFileRepo);
    }

    @After
    public void resetMocks() {
        filePicture = null;
        reset(pictureFileRepo);
    }
}
