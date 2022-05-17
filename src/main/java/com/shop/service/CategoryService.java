package com.shop.service;

import com.shop.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    void save(Category category);
    Category findById(Long id);
    void deleteById(Long id);
    Category findByName(String name);
}
