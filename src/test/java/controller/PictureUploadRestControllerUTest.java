package controller;

import config.UnitTestBase;
import cs309.controller.PictureUploadRestController;
import cs309.data.PictureFile;
import cs309.service.PictureFileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PictureUploadRestControllerUTest extends UnitTestBase {

    @Mock
    private PictureFileService pictureFileService;
    @InjectMocks
    private PictureUploadRestController pictureUploadRestController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(pictureUploadRestController).build();
    }

    @Test
    public void pictureUploadTest() throws Exception {
        File file = new File(this.getClass().getResource("/img/testPicture.jpg").getFile());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(ImageIO.read(file), file.getName().substring(file.getName().indexOf('.') + 1), byteArrayOutputStream);
        when(pictureFileService.savePictureFile(any())).thenReturn(new PictureFile(file.getName(), byteArrayOutputStream.toByteArray()));

        ResultActions results = this.mockMvc.perform(get("/picture_upload/test").accept(MediaType.TEXT_HTML));

        results
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
