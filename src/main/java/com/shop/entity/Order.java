package com.shop.entity;

import com.shop.enumeration.OrderStatus;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Entity
@Table(name="orders")
@Data
@FieldNameConstants
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_date")
    private Date modifyDate;

    @Column(name ="delivery_date")
    private Date deliveryDate;

    @Column(name ="total_price")
    private Double totalPrice;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderItem> orderItemList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    private void onCreate() {
        createDate = new Date();
        modifyDate = new Date();
        orderStatus = OrderStatus.PENDING;
        deliveryDate = DateUtils.addDays(new Date(), 7);
    }

    @PreUpdate
    private void onUpdate() {
        modifyDate = new Date();
    }
}
