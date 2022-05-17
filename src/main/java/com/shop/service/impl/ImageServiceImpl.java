package com.shop.service.impl;

import com.shop.entity.Image;
import com.shop.repository.ImageRepository;
import com.shop.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    @Transactional
    public Image findById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public Image save(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setFileName(file.getName());
        image.setContent(file.getBytes());
        image.setContentType(file.getContentType());
        return imageRepository.save(image);
    }
}
