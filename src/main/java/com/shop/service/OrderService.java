package com.shop.service;

import com.shop.entity.Cart;
import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.enumeration.OrderStatus;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    List<Order> findAllByOrderStatus(OrderStatus status);
    Order save(Cart cart);
    Order findById(Long id);
    void deleteById(Long id);
    Order cancelById(Long id);
    Order finishById(Long id);
    List<Order> findAllByUser(User user);
}
