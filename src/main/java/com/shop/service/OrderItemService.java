package com.shop.service;

import com.shop.entity.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAll();
    void save(OrderItem orderItem);
    OrderItem findById(Long id);
    void deleteById(Long id);
}
