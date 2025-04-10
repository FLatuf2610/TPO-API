package com.uade.marketplace.service.image;

import com.uade.marketplace.data.entities.ProductImage;
import com.uade.marketplace.data.repositories.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ProductImageRepository productImageRepository;

    @Value("${app.base.url}")
    String baseUrl;

    @Override
    public String save(MultipartFile file) {
        try {
            String ext = getExtension(file.getOriginalFilename());
            if (!isExtensionAllowed(ext)) {
                throw new RuntimeException("Formato incompatible");
            }

            ProductImage productImage = ProductImage.builder()
                    .name(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .data(file.getBytes())
                    .build();

            ProductImage persistedEntity = productImageRepository.save(productImage);
            return baseUrl + "/images/" + persistedEntity.getId();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public byte[] getImage(Long id) {
        return productImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"))
                .getData();
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    private boolean isExtensionAllowed(String extension) {
        return extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg");
    }
}
