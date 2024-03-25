package com.example.EditMatch.controller.cloudinary.mapper;

import com.example.EditMatch.controller.client.dto.ClientResponseDto;
import com.example.EditMatch.controller.cloudinary.dto.CreateImageDto;
import com.example.EditMatch.controller.cloudinary.dto.ImageResponseDto;
import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Image;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {
    public static Image of(CreateImageDto createImageDto){
        Image image = new Image();
        createImageDto.setLinkImage(image.getLinkImage());
        createImageDto.setName(image.getName());
        createImageDto.setUserId(image.getUserId());

        return image;
    }

    public static ImageResponseDto of(Image image){
        ImageResponseDto imageResponseDto = new ImageResponseDto();

        imageResponseDto.setLinkImage(image.getLinkImage());
        imageResponseDto.setName(image.getName());
        imageResponseDto.setUserId(image.getUserId());

        return imageResponseDto;
    }
}
