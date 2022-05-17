package com.shop.repository;

import com.shop.entity.Order;
import com.shop.entity.User;
import com.shop.enumeration.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(User user);
    List<Order> findAllByOrderStatus(OrderStatus status);
}
