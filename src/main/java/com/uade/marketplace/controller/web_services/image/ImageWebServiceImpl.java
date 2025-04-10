package com.uade.marketplace.controller.web_services.image;

import com.uade.marketplace.controller.dto.response.image.ImageResponse;
import com.uade.marketplace.service.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class ImageWebServiceImpl implements ImageWebService {
    private final ImageService imageService;

    @Autowired
    public ImageWebServiceImpl(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public ImageResponse save(MultipartFile file) {
        return new ImageResponse(imageService.save(file));
    }

    @Override
    public byte[] getImage(Long id) {
        return imageService.getImage(id);
    }
}
