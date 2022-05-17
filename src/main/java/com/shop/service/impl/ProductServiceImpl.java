package com.shop.service.impl;

import com.shop.dto.PageDto;
import com.shop.entity.*;
import com.shop.repository.CategoryRepository;
import com.shop.repository.ImageRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Product product) throws IOException {
        Product productExists = null;
        if(product.getId() != null) {
            productExists = productRepository.findById(product.getId()).orElse(null);
        }

        if(productExists == null && product.getMultipartFile() != null && !StringUtils.isEmpty(product.getMultipartFile().getOriginalFilename())) {
            Image image = new Image();
            image.setFileName(product.getMultipartFile().getOriginalFilename());
            image.setContent(product.getMultipartFile().getBytes());
            image.setContentType(product.getMultipartFile().getContentType());
            imageRepository.save(image);
            product.setImage(image);
        }

        if(productExists != null && productExists.getImage() != null){
            product.setImage(productExists.getImage());
        }

        productRepository.save(product);
    }

    @Override
    @Transactional
    public void save(Product product, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        product.setCategory(category);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PageDto<Product> findAllByCategoryId(Long id, Pageable pageable) {
        List<Product> productList = productRepository.findAllByCategoryId(id, pageable);
        Integer total = productRepository.countAllByCategoryId(id);
        return new PageDto<>(total, productList);
    }
}
