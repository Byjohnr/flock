package cs309.controller;

import cs309.data.PictureFile;
import cs309.service.PictureFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@Controller
@RequestMapping("/picture_upload")
public class PictureUploadController {

    private static final Logger LOG = LoggerFactory.getLogger(PictureUploadController.class);
    private static final String TEST_PICTURE_PATH = "/img/SecureAllTheThings.jpeg";

    @Autowired
    private PictureFileService pictureFileService;

    @RequestMapping("/test")
    public String pictureUploadTest() {
        Class classType = this.getClass();
        URL url = classType.getResource(TEST_PICTURE_PATH);
        String filePath = url.getFile();
        File file = new File(filePath);
        PictureFile pictureFile = pictureFileService.savePictureFile(file);
        try {
            ImageIO.write(ImageIO.read(new ByteArrayInputStream(pictureFile.getPicture())), file.getName().substring(file.getName().indexOf('.') + 1),
                    new File(this.getClass().getResource("/img/").getFile() + "test_" + pictureFile.getFileName()));
        } catch (IOException ex) {
            LOG.error("IOException thrown in PictureUploadController.pictureUploadTest() when trying to read a pictureFile's InputStream", ex);
        }
        return "redirect:/";
    }
}
