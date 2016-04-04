package cs309.controller;

import cs309.data.PictureFile;
import cs309.data.User;
import cs309.service.PictureFileService;
import cs309.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class PictureRestController {

    private static final Logger LOG = LoggerFactory.getLogger(PictureRestController.class);

    @Autowired
    private PictureFileService pictureFileService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/profile_picture")
    public String profilePictureGetter(Principal principal) {
        String picture = null;
        try {
            User principalUser = userService.getUserByEmail(principal.getName());
            PictureFile pictureFile = pictureFileService.getPictureFileByUserId(principalUser.getId());
            picture = pictureFile.getPicture();
        } catch (NullPointerException ex) {
            LOG.error("No picture for user " + principal.getName());
        }
        return picture;
    }

    @ResponseBody
    @RequestMapping("/user_profile_picture/{userId}")
    public String userProfilePictureGetter(@PathVariable("userId") Integer userId) {
        String picture = null;
        try {
            PictureFile pictureFile = pictureFileService.getPictureFileByUserId(userId);
            picture = pictureFile.getPicture();
        } catch (NullPointerException ex) {
            LOG.error("No picture for user " + userId);
        }
        return picture;
    }

    @ResponseBody
    @RequestMapping("/picture_upload/profile_picture")
    public String profilePictureUpload(String picture, String fileName, Principal principal) {
        User principalUser = userService.getUserByEmail(principal.getName());
        PictureFile pictureFile = pictureFileService.savePictureFileForUser(fileName, principalUser.getId(), picture);
        return pictureFile.getPicture();
    }

    @RequestMapping("/event_picture/{eventId}")
    public String eventPictureGetter(@PathVariable("eventId") Integer eventId) {
        String picture = null;
        try {
            picture = pictureFileService.getPictureFileByEventId(eventId).getPicture();
        } catch (NullPointerException ex) {
            LOG.error("No picture for user " + eventId);
        }
        return picture;
    }

    @RequestMapping("/picture_upload/event_picture/{eventId}")
    public String eventPictureUpload(String picture, String fileName, @PathVariable("eventId") Integer eventId) {
        PictureFile pictureFile = pictureFileService.savePictureFileForEvent(fileName, eventId, picture);
        return pictureFile.getPicture();
    }
}
