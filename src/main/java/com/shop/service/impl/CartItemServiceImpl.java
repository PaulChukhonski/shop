package com.shop.service.impl;

import com.shop.entity.CartItem;
import com.shop.repository.CartItemRepository;
import com.shop.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    @Transactional
    public void save(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }
}
