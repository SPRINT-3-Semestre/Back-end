package com.example.EditMatch.controller.cloudinary;

import com.example.EditMatch.service.image.ImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
public class CreateImageController {
    private final UploadFileInterface uploadFileInterface;
    private final ImageService imageService;

    public CreateImageController(UploadFileInterface uploadFileInterface, ImageService imageService) {
        this.uploadFileInterface = uploadFileInterface;
        this.imageService = imageService;
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file,
                                 @RequestParam("userId") String userId) throws Exception {
        String url = uploadFileInterface.uploadFile(file);
        imageService.save(url, file, userId);

        return url;
    }
}