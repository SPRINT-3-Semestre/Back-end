package com.example.EditMatch.service.image;

import com.example.EditMatch.controller.cloudinary.dto.ImageResponseDto;
import com.example.EditMatch.controller.cloudinary.mapper.ImageMapper;
import com.example.EditMatch.entity.Image;
import com.example.EditMatch.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    public ImageResponseDto save(String url, MultipartFile urlImage, String userId) {
        Image image = new Image();

        image.setLinkImage(url);
        image.setName(urlImage.getOriginalFilename());
        image.setUserId(userId);

        ImageMapper.of(image);

        imageRepository.save(image);

        return ImageMapper.of(image);
    }
}
