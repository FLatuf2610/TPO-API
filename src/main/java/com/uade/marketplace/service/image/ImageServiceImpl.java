package com.uade.marketplace.service.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService{
    @Value("${upload.image.path}")
    private String uploadDir;

    @Value("${image.access.path}")
    private String accessPath;

    @Value("${app.base.url}")
    private String baseUrl;

    @Override
    public String save(MultipartFile file) {
        try {
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                //noinspection ResultOfMethodCallIgnored
                directory.mkdir();
            }

            String fileExt = getExtension(file.getOriginalFilename());
            if (!isExtensionAllowed(fileExt)) {
                throw new RuntimeException("");
            }

            String fileName = UUID.randomUUID() + "." + fileExt;
            File fileDestination = new File(directory, fileName);
            file.transferTo(fileDestination);

            return baseUrl + accessPath + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }

    private boolean isExtensionAllowed(String extension) {
        return extension.equals("jpg") || extension.equals("png") || extension.equals("jpeg");
    }
}
