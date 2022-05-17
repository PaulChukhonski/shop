package com.shop.controller.admin;

import com.shop.entity.Order;
import com.shop.enumeration.OrderStatus;
import com.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/show")
    public String showOrders(@RequestParam(required = false) OrderStatus status, Model model) {
        List<Order> orders;
        if(status == null) {
            orders = orderService.findAll();
        } else {
            orders = orderService.findAllByOrderStatus(status);
        }

        model.addAttribute("orders", orders);

        return "admin/showOrders";
    }

    @GetMapping("/order/finish/{id}")
    public String finishOrder(@PathVariable Long id) {
        orderService.finishById(id);
        return "redirect:/order/show?finished";
    }
}
