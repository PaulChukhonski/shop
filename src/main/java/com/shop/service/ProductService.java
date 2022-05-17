package com.shop.service;

import com.shop.dto.PageDto;
import com.shop.entity.Product;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
    void save(Product product) throws IOException;
    void save(Product product, Long categoryId);
    Product findById(Long id);
    void deleteById(Long id);
    PageDto<Product> findAllByCategoryId(Long id, Pageable pageable);
}
