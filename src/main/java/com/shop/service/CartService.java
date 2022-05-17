package com.shop.service;

import com.shop.entity.Cart;
import com.shop.entity.Product;
import com.shop.entity.User;

public interface CartService {
    Cart findById(Long id);
    void deleteById(User user);
    Cart findCart(User user);
    Cart addItemToCart(Product product, Long quantity, User user);
    Cart removeItemToCart(Product product, User user);
    Cart updateItemFromCart(Product product, Long quantity, User user);
}
