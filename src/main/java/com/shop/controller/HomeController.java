package com.shop.controller;

import com.shop.service.CategoryService;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String homePage(@RequestParam(required = false) Long categoryId, Model model) {
        model.addAttribute("categories", categoryService.findAll());

        if(categoryId != null) {
            model.addAttribute("products", productService.findAllByCategoryId(categoryId, Pageable.unpaged()).getRecords());
        } else {
            model.addAttribute("products", productService.findAll());
        }

        return "index";
    }
}
