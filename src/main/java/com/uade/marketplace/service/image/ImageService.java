package com.uade.marketplace.service.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String save(MultipartFile file);
}
