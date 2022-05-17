package com.shop.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cart")
@Data
@FieldNameConstants
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItemList;

    @Transient
    public Double countTotal() {
        double total = 0.0;
        for (CartItem cartItem : cartItemList) {
            total += cartItem.getQuantity() * cartItem.getProduct().getPrice();
        }
        return total;
    }
}
