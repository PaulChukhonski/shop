package com.shop.service;

import com.shop.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image findById(Long id);
    Image save(MultipartFile file) throws IOException;
}
