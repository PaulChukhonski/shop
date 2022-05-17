package com.shop.service;

import com.shop.entity.CartItem;

import java.util.List;

public interface CartItemService {
    List<CartItem> findAll();
    void save(CartItem cartItem);
    CartItem findById(Long id);
    void deleteById(Long id);
}
