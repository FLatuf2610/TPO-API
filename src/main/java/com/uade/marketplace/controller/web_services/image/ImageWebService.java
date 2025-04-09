package com.uade.marketplace.controller.web_services.image;

import com.uade.marketplace.controller.dto.response.image.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageWebService {
    ImageResponse save(MultipartFile file);
}
