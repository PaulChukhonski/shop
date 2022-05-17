package com.shop.service.impl;

import com.shop.entity.*;
import com.shop.enumeration.OrderStatus;
import com.shop.repository.CartRepository;
import com.shop.repository.OrderItemRepository;
import com.shop.repository.OrderRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public List<Order> findAllByOrderStatus(OrderStatus status) {
        return orderRepository.findAllByOrderStatus(status);
    }

    @Override
    @Transactional
    public Order save(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setTotalPrice(cart.countTotal());

        List<OrderItem> res = new ArrayList<>();
        for(CartItem cartItem: cart.getCartItemList()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());


            orderItem.getProduct().setStock(orderItem.getProduct().getStock() - orderItem.getQuantity());
            productRepository.save(orderItem.getProduct());

            orderItemRepository.save(orderItem);
            res.add(orderItem);
        }

        order.setOrderItemList(res);
        orderRepository.save(order);

        cartRepository.delete(cart);

        return order;
    }

    @Override
    @Transactional
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Order cancelById(Long id) {
        Order order = orderRepository.findById(id).get();
        order.setOrderStatus(OrderStatus.CANCELED);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order finishById(Long id) {
        Order order = orderRepository.findById(id).get();
        order.setOrderStatus(OrderStatus.FINISHED);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }
}
