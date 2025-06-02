package com.uade.marketplace.controller.controllers;

import com.uade.marketplace.controller.dto.response.image.ImageResponse;
import com.uade.marketplace.controller.web_services.image.ImageWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("images")
public class ImageController {
    private final ImageWebService imageWebService;

    @Autowired
    public ImageController(ImageWebService imageWebService) {
        this.imageWebService = imageWebService;
    }

    // POST --> {url}/images/uploadImage
    @PostMapping("uploadImage")
    public ResponseEntity<ImageResponse> uploadImage(@RequestParam("image")MultipartFile image) {
        return new ResponseEntity<>(imageWebService.save(image), HttpStatus.CREATED);
    }

    // GET --> {url}/images/id
    @GetMapping("/{id}")
    ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        return new ResponseEntity<>(imageWebService.getImage(id), HttpStatus.OK);
    }
}
