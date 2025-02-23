package workshop.springai.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import workshop.springai.services.ImageRecognitionService;

@Slf4j
@RestController
public class ImageRecognitionController {

    private final ImageRecognitionService imageRecognitionService;

    public ImageRecognitionController(ImageRecognitionService imageRecognitionService) {
        this.imageRecognitionService = imageRecognitionService;
    }

    @PostMapping(value = "/image/recognition", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> processImage(@RequestParam("file") MultipartFile file) {
        log.info("Image processing: process started");
        try {
            return ResponseEntity.ok(imageRecognitionService.processImage(file));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Image processing: process failed" + e.getMessage());
        }
    }
}
