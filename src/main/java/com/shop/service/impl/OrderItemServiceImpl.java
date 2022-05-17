package com.shop.service.impl;

import com.shop.entity.CartItem;
import com.shop.entity.OrderItem;
import com.shop.repository.OrderItemRepository;
import com.shop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    @Transactional
    public void save(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    @Override
    @Transactional
    public OrderItem findById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
