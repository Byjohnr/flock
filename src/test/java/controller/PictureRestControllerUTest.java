package controller;

import config.UnitTestBase;
import cs309.controller.PictureRestController;
import cs309.data.PictureFile;
import cs309.data.User;
import cs309.service.PictureFileService;
import cs309.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.security.Principal;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PictureRestControllerUTest extends UnitTestBase {

    @Mock
    private PictureFileService pictureFileService;
    @Mock
    private UserService userService;
    @InjectMocks
    private PictureRestController pictureRestController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = standaloneSetup(pictureRestController).build();
    }

    @Test
    public void profilePictureGetter() throws Exception {
        User principalUser = new User();
        principalUser.setId(4);
        principalUser.setFirstName("Easter");
        principalUser.setLastName("Bunny");
        principalUser.setEmail("thebunny@easter.com");
        PictureFile returnedPictureFile = new PictureFile("returnedPicture.png", principalUser.getId(), null, "The Easter Bunny's map of where he hid eggs.");
        when(userService.getUserByEmail(anyString())).thenReturn(principalUser);
        when(pictureFileService.getPictureFileByUserId(principalUser.getId())).thenReturn(returnedPictureFile);

        ResultActions results = this.mockMvc.perform(get("/api/profile_picture").accept(MediaType.TEXT_HTML).principal(mock(Principal.class)));

        results
                .andExpect(status().isOk())
                .andExpect(content().string(returnedPictureFile.getPicture()));

        verify(userService, times(1)).getUserByEmail(anyString());
        verify(pictureFileService, times(1)).getPictureFileByUserId(principalUser.getId());
        verifyNoMoreInteractions(userService, pictureFileService);
    }

    @Test
    public void userProfilePictureGetter() throws Exception {
        User principalUser = new User();
        principalUser.setId(4);
        principalUser.setFirstName("Easter");
        principalUser.setLastName("Bunny");
        principalUser.setEmail("thebunny@easter.com");
        PictureFile returnedPictureFile = new PictureFile("returnedPicture.png", principalUser.getId(), null, "The Easter Bunny's map of where he hid eggs.");
        when(pictureFileService.getPictureFileByUserId(principalUser.getId())).thenReturn(returnedPictureFile);

        ResultActions results = this.mockMvc.perform(get("/api/user_profile_picture/{userId}", principalUser.getId()).accept(MediaType.TEXT_HTML).principal(mock(Principal.class)));

        results
                .andExpect(status().isOk())
                .andExpect(content().string(returnedPictureFile.getPicture()));

        verify(pictureFileService, times(1)).getPictureFileByUserId(principalUser.getId());
        verifyNoMoreInteractions(userService, pictureFileService);
    }

    @Test
    public void profilePictureUpload() throws Exception {
        User principalUser = new User();
        principalUser.setId(4);
        principalUser.setFirstName("Easter");
        principalUser.setLastName("Bunny");
        principalUser.setEmail("thebunny@easter.com");
        PictureFile returnedPictureFile = new PictureFile("returnedPicture.png", principalUser.getId(), null, "The Easter Bunny's map of where he hid eggs.");
        when(userService.getUserByEmail(anyString())).thenReturn(principalUser);
        when(pictureFileService.savePictureFileForUser(returnedPictureFile.getFileName(), principalUser.getId(), returnedPictureFile.getPicture())).thenReturn(returnedPictureFile);

        ResultActions results = this.mockMvc.perform(post("/api/picture_upload/profile_picture").accept(MediaType.TEXT_HTML).principal(mock(Principal.class))
                .param("picture", returnedPictureFile.getPicture()).param("fileName", returnedPictureFile.getFileName()));

        results
                .andExpect(status().isOk())
                .andExpect(content().string(returnedPictureFile.getPicture()));

        verify(userService, times(1)).getUserByEmail(anyString());
        verify(pictureFileService, times(1)).savePictureFileForUser(returnedPictureFile.getFileName(), principalUser.getId(), returnedPictureFile.getPicture());
        verifyNoMoreInteractions(userService, pictureFileService);
    }

    @Test
    public void eventPictureGetter() throws Exception {
        PictureFile returnedPictureFile = new PictureFile("returnedPicture.png", 1, null, "The Easter Bunny's map of where he hid eggs.");
        when(pictureFileService.getPictureFileByEventId(anyInt())).thenReturn(returnedPictureFile);

        ResultActions results = this.mockMvc.perform(get("/api/event_picture/{eventId}", 1).accept(MediaType.TEXT_HTML).principal(mock(Principal.class)));

        results
                .andExpect(status().isOk())
                .andExpect(content().string(returnedPictureFile.getPicture()));

        verify(pictureFileService, times(1)).getPictureFileByEventId(1);
    }

    @Test
    public void eventPictureUpload() throws Exception {
        PictureFile returnedPictureFile = new PictureFile("returnedPicture.png", 1, null, "The Easter Bunny's map of where he hid eggs.");
        when(pictureFileService.savePictureFileForEvent(returnedPictureFile.getFileName(), 1, returnedPictureFile.getPicture())).thenReturn(returnedPictureFile);

        ResultActions results = this.mockMvc.perform(post("/api/picture_upload/event_picture/1").accept(MediaType.TEXT_HTML).principal(mock(Principal.class))
                .param("picture", returnedPictureFile.getPicture()).param("fileName", returnedPictureFile.getFileName()));

        results
                .andExpect(status().isOk())
                .andExpect(content().string(returnedPictureFile.getPicture()));

        verify(pictureFileService, times(1)).savePictureFileForEvent(returnedPictureFile.getFileName(), 1, returnedPictureFile.getPicture());
        verifyNoMoreInteractions(userService, pictureFileService);
    }


    @After
    public void resetMocks() {
        reset(userService, pictureFileService);
    }
}
