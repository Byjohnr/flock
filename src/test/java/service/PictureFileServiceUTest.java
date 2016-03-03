package service;

import config.UnitTestBase;
import cs309.data.PictureFile;
import cs309.repo.PictureFileRepository;
import cs309.service.PictureFileService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

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
        URL testPictureUrl = this.getClass().getResource("/img/testPicture.jpg");
        filePicture = new File(testPictureUrl.getFile());
    }

    @Test
    public void getPictureByFileName() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String fileType = filePicture.getName().substring(filePicture.getName().indexOf('.') + 1);
        ImageIO.write(ImageIO.read(filePicture), fileType, outputStream);
        PictureFile testPicture = new PictureFile("testPicture.jpg", outputStream.toByteArray());
        testPicture.setId(2);
        when(pictureFileRepo.findByFileName(anyString())).thenReturn(testPicture);
        BufferedImage methodReturn = pictureFileService.getPictureByFileName("testPicture.jpg");
        ByteArrayOutputStream methodOutputStream = new ByteArrayOutputStream();
        ImageIO.write(methodReturn, fileType, methodOutputStream);
        assertEquals(ImageIO.read(filePicture).getWidth(), methodReturn.getWidth());
        assertEquals(ImageIO.read(filePicture).getHeight(), methodReturn.getHeight());
        assertEquals(ImageIO.read(filePicture).getType(), methodReturn.getType());
        assertEquals(ImageIO.read(filePicture).getNumXTiles(), methodReturn.getNumXTiles());
        assertEquals(ImageIO.read(filePicture).getNumYTiles(), methodReturn.getNumYTiles());
        assertEquals(ImageIO.read(filePicture).getTransparency(), methodReturn.getTransparency());
        verify(pictureFileRepo, times(1)).findByFileName(anyString());
        verifyNoMoreInteractions(pictureFileRepo);
    }

    @Test
    public void getPictureByUserId() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String fileType = filePicture.getName().substring(filePicture.getName().indexOf('.') + 1);
        ImageIO.write(ImageIO.read(filePicture), fileType, outputStream);
        PictureFile testPicture = new PictureFile("testPicture.jpg", outputStream.toByteArray());
        testPicture.setUserId(3);
        when(pictureFileRepo.findByUserId(anyInt())).thenReturn(testPicture);
        BufferedImage methodReturn = pictureFileService.getPictureByUserId(3);
        ByteArrayOutputStream methodOutputStream = new ByteArrayOutputStream();
        ImageIO.write(methodReturn, fileType, methodOutputStream);
        assertEquals(ImageIO.read(filePicture).getWidth(), methodReturn.getWidth());
        assertEquals(ImageIO.read(filePicture).getHeight(), methodReturn.getHeight());
        assertEquals(ImageIO.read(filePicture).getType(), methodReturn.getType());
        assertEquals(ImageIO.read(filePicture).getNumXTiles(), methodReturn.getNumXTiles());
        assertEquals(ImageIO.read(filePicture).getNumYTiles(), methodReturn.getNumYTiles());
        assertEquals(ImageIO.read(filePicture).getTransparency(), methodReturn.getTransparency());
        verify(pictureFileRepo, times(1)).findByUserId(anyInt());
        verifyNoMoreInteractions(pictureFileRepo);
    }

    @Test
    public void getPictureByEventId() throws IOException, URISyntaxException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String fileType = filePicture.getName().substring(filePicture.getName().indexOf('.') + 1);
        ImageIO.write(ImageIO.read(filePicture), fileType, outputStream);
        PictureFile testPicture = new PictureFile("testPicture.jpg", outputStream.toByteArray());
        testPicture.setEventId(4);
        when(pictureFileRepo.findByEventId(anyInt())).thenReturn(testPicture);
        BufferedImage methodReturn = pictureFileService.getPictureByEventId(4);
        ByteArrayOutputStream methodOutputStream = new ByteArrayOutputStream();
        ImageIO.write(methodReturn, fileType, methodOutputStream);
        assertEquals(ImageIO.read(filePicture).getWidth(), methodReturn.getWidth());
        assertEquals(ImageIO.read(filePicture).getHeight(), methodReturn.getHeight());
        assertEquals(ImageIO.read(filePicture).getType(), methodReturn.getType());
        assertEquals(ImageIO.read(filePicture).getNumXTiles(), methodReturn.getNumXTiles());
        assertEquals(ImageIO.read(filePicture).getNumYTiles(), methodReturn.getNumYTiles());
        assertEquals(ImageIO.read(filePicture).getTransparency(), methodReturn.getTransparency());
        verify(pictureFileRepo, times(1)).findByEventId(anyInt());
        verifyNoMoreInteractions(pictureFileRepo);
    }

    @After
    public void resetMocks() {
        filePicture = null;
        reset(pictureFileRepo);
    }

//    private boolean bufferedImagesEqual(BufferedImage base, BufferedImage toCompare) {
//        boolean returnedValue = false;
//        if (base.getWidth() == toCompare.getWidth() && base.getHeight() == toCompare.getHeight()) {
//            int width = base.getWidth();
//            int height = base.getHeight();
//            returnedValue = true;
//            for (int y = 0; y < height && returnedValue; ++y) {
//                for (int x = 0; x < width && returnedValue; ++x) {
//                    int rgbBase = base.getRGB(x, y);
//                    int rgbToCompare = toCompare.getRGB(x, y);
//                    if (base.getRGB(x, y) != toCompare.getRGB(x, y)) {
//                        returnedValue = false;
//                    }
//                }
//            }
//        }
//        return returnedValue;
//    }
}
