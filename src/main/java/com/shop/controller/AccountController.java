package com.shop.controller;

import com.shop.dto.UserAccountDto;
import com.shop.entity.User;
import com.shop.mapper.UserAccountMapper;
import com.shop.service.OrderService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @GetMapping(value = "/info")
    public String accountInfoCheckout(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", userAccountMapper.toDto(user));

        return "account";
    }

    @PostMapping(value = "/save")
    public String accountSaveCheckout(@Valid @ModelAttribute("user") UserAccountDto user, BindingResult result, Model model, Principal principal) {
        User userExists = userService.findByEmail(user.getEmail());
        model.addAttribute("user", user);

        if (userExists != null && !principal.getName().equals(user.getEmail())) {
            model.addAttribute("error", "email");
            return "account";
        }

        if(result.hasErrors()) {
            return "account";
        }

        userService.update(userAccountMapper.toEntity(user));

        return "redirect:/account/info?success";
    }

    @GetMapping("/order/history")
    public String orderHistory(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("orders", orderService.findAllByUser(user));
        return "order";
    }

    @GetMapping("/order/cancel/{id}")
    public String cancelOrder(@PathVariable Long id) {
        orderService.cancelById(id);
        return "redirect:/account/order/history?canceled";
    }

    @GetMapping("/order/{id}")
    public String orderDetail(@PathVariable Long id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order-detail";
    }
}
