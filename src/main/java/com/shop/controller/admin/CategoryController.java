package com.shop.controller.admin;

import com.shop.entity.Category;
import com.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "admin/addEditCategory";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "admin/addEditCategory";
    }

    @GetMapping("/show")
    public String showCategories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "admin/showCategories";
    }

    @PostMapping("/save")
    public String saveCategory(@Valid @ModelAttribute Category category, BindingResult result, Model model) {
        Category categoryExists = categoryService.findByName(category.getName());
        model.addAttribute("category", category);

        if(categoryExists != null) {
            model.addAttribute("error", "name");
            return "admin/addEditCategory";
        }

        if(result.hasErrors()) {
            return "admin/addEditCategory";
        }

        categoryService.save(category);
        return "redirect:/category/show?success";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/category/show?deleted";
    }
}
