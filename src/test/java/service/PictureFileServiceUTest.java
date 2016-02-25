package service;

import config.UnitTestBase;
import cs309.data.PictureFile;
import cs309.repo.PictureFileRepository;
import cs309.service.PictureFileService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;

import static org.mockito.Mockito.*;

public class PictureFileServiceUTest extends UnitTestBase {

    @Mock
    private PictureFileRepository pictureFileRepo;
    @InjectMocks
    private PictureFileService pictureFileService;

    @Test
    public void getPicturesByFileName() {
        when(pictureFileRepo.findByFileName(anyString())).thenReturn(new PictureFile("testFile", null));
        
    }
}
