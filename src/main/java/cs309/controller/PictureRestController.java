package cs309.controller;

import cs309.data.PictureFile;
import cs309.data.User;
import cs309.service.PictureFileService;
import cs309.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Object profilePictureGetter(Principal principal) {
        User principalUser = userService.getUserByEmail(principal.getName());
        PictureFile pictureFile = pictureFileService.getPictureFileByUserId(principalUser.getId());
        return pictureFile.getPicture();
    }

    @ResponseBody
    @RequestMapping("/picture_upload/profile_picture")
    public Object profilePictureUpload(String picture, String fileName, Principal principal) {
        System.out.println("pictureData: " + picture);
        Integer pictureLengthChars = picture.length();

        PictureFile pictureFile = pictureFileService.savePictureFile(fileName, null, null, picture);

        return pictureFile.getPicture();
    }
}
