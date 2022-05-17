package com.shop.controller.admin;

import com.shop.entity.Category;
import com.shop.entity.Product;
import com.shop.service.CategoryService;
import com.shop.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("product", new Product());
        return "admin/addEditProduct";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("product", productService.findById(id));
        return "admin/addEditProduct";
    }

    @GetMapping("/show")
    public String showProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "admin/showProducts";
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute Product product, BindingResult result, Model model) throws IOException {
        Product productExists = null;
        if(product.getId() != null) {
            productExists = productService.findById(product.getId());
        }

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("product", product);

        if(productExists == null && (product.getMultipartFile() == null || StringUtils.isEmpty(product.getMultipartFile().getOriginalFilename()))) {
            model.addAttribute("error", "image");
            return "admin/addEditProduct";
        }

        if(result.hasErrors()) {
            return "admin/addEditProduct";
        }

        productService.save(product);
        return "redirect:/product/show?success";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/product/show?deleted";
    }
}
