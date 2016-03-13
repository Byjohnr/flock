package cs309.controller;

import cs309.data.PictureFile;
import cs309.service.PictureFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.Principal;
import java.sql.Blob;
import java.util.Map;

@RestController
@RequestMapping("/api/picture_upload")
public class PictureUploadRestController {

    private static final Logger LOG = LoggerFactory.getLogger(PictureUploadRestController.class);
    private static final String TEST_PICTURE_PATH = "/img/SecureAllTheThings.jpeg";

    @Autowired
    private PictureFileService pictureFileService;

    @ResponseBody
    @RequestMapping("/profile_picture")
    public Object profilePictureUpload(URI pictureData) {
        //Logic to parse the awesome picture encoded string
        System.out.println("pictureData: " + pictureData);

//        PictureFile pictureFile = pictureFileService.savePictureFile(new File(pictureData.normalize()));

        return pictureData;
    }

    /*
    @RequestMapping("//*test")
    public String pictureUploadTest(Principal principal) {
        Class classType = this.getClass();
        URL url = classType.getResource(TEST_PICTURE_PATH);
        String filePath = url.getFile();
        File file = new File(filePath);
        PictureFile pictureFile = pictureFileService.savePictureFile(file);
        try {
            ImageIO.write(ImageIO.read(new ByteArrayInputStream(pictureFile.getPicture())), file.getName().substring(file.getName().indexOf('.') + 1),
                    new File(this.getClass().getResource("/img/").getFile() + "test_" + pictureFile.getFileName()));
        } catch (IOException ex) {
            LOG.error("IOException thrown in PictureUploadController.pictureUploadTest() when trying to read a pictureFile's bytes", ex);
        }
        return "redirect:/";
    }
    */
}
