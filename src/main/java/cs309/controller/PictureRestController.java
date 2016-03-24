package cs309.controller;

import cs309.service.PictureFileService;
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

    @ResponseBody
    @RequestMapping("/profile_picture")
    public Object profilePictureGetter(String picture, Principal principal) {
        System.out.println("pictureData: " + picture);
        Integer pictureLengthChars = picture.length();

//        PictureFile pictureFile = pictureFileService.savePictureFile(picture);

        return picture;
    }

    @ResponseBody
    @RequestMapping("/picture_upload/profile_picture")
    public Object profilePictureUpload(String picture, Principal principal) {
        System.out.println("pictureData: " + picture);
        Integer pictureLengthChars = picture.length();

//        PictureFile pictureFile = pictureFileService.savePictureFile(picture);

        return picture;
    }
}
