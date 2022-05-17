package com.shop.controller;

import com.shop.entity.*;
import com.shop.service.CartService;
import com.shop.service.OrderService;
import com.shop.service.ProductService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String cart(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        Cart cart = cartService.findCart(user);

        model.addAttribute("cart", cart);

        return "cart";
    }

    @PostMapping("/item/add")
    public String addCartItem(@RequestParam("id") Long id,
                              @RequestParam("quantity") Long quantity,
                              Model model,
                              Principal principal) {
        Product product = productService.findById(id);
        User user = userService.findByEmail(principal.getName());
        Cart cart = cartService.addItemToCart(product, quantity, user);

        model.addAttribute("cart", cart);

        return "redirect:/cart?addCartItem";
    }

    @PostMapping(value="/item/update", params="action=update")
    public String updateCartItem(@RequestParam("id") Long id,
                                 @RequestParam("quantity") Long quantity,
                                 Model model,
                                 Principal principal) {
        Product product = productService.findById(id);
        User user = userService.findByEmail(principal.getName());
        Cart cart = cartService.updateItemFromCart(product, quantity, user);

        model.addAttribute("cart", cart);

        return "redirect:/cart?updateCartItem";
    }

    @PostMapping(value="/item/update", params="action=delete")
    public String deleteCartItem(@RequestParam("id") Long id,
                                 Model model,
                                 Principal principal) {
        Product product = productService.findById(id);
        User user = userService.findByEmail(principal.getName());
        Cart cart = cartService.removeItemToCart(product, user);

        model.addAttribute("cart", cart);

        return "redirect:/cart?deleteCartItem";
    }

    @PostMapping("/checkout")
    public String checkoutSave(@RequestParam Long id, Principal principal) {
        Cart cart = cartService.findById(id);

        if(cart == null || cart.getCartItemList() == null) {
            return "redirect:/cart?error";
        }

        User user = userService.findByEmail(principal.getName());

        if(user.getAddress() == null) {
            return "redirect:/account?addressEmpty";
        }

        orderService.save(cart);

        return "redirect:/account/order/history?success";
    }
}
