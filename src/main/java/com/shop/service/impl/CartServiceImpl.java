package com.shop.service.impl;

import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Product;
import com.shop.entity.User;
import com.shop.repository.CartItemRepository;
import com.shop.repository.CartRepository;
import com.shop.service.CartService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    @Transactional
    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(User user) {
        Cart cart = cartRepository.findByUser(user);
        if(user != null && cart != null) {
            cartRepository.deleteById(cart.getId());
        }
    }

    @Override
    @Transactional
    public Cart findCart(User user) {
        Cart cart = cartRepository.findByUser(user);

        if(cart == null){
            Cart newCart = new Cart();

            newCart.setUser(user);
            newCart.setCartItemList(new ArrayList<>());

            return newCart;
        }

        return cart;
    }

    private CartItem findCartItem(Cart cart, Long productId) {
        for (CartItem cartItem : cart.getCartItemList()) {
            if (cartItem.getProduct().getId().equals(productId)) {
                return cartItem;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public Cart addItemToCart(Product product, Long quantity, User user) {
        Cart cart = findCart(user);
        CartItem cartItem = findCartItem(cart, product.getId());

        if(cartItem == null){
            cartItem = new CartItem();

            cartItem.setProduct(product);
            cartItem.setCart(cart);

            List<CartItem> cartItemList = cart.getCartItemList();
            cartItemList.add(cartItem);

            cart.setCartItemList(cartItemList);
        }

        Long totalQuantity =  (cartItem.getQuantity() == null ? 0 : cartItem.getQuantity()) + quantity;

        if(totalQuantity > product.getStock()) {
            totalQuantity = product.getStock();
        }

        cartItem.setQuantity(totalQuantity);

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

        return cart;
    }

    @Override
    @Transactional
    public Cart removeItemToCart(Product product, User user) {
        Cart cart = findCart(user);
        CartItem cartItem = findCartItem(cart, product.getId());

        cart.getCartItemList().removeIf(item -> item.equals(cartItem));

        cartRepository.save(cart);
        cartItemRepository.delete(cartItem);

        return cart;
    }

    @Override
    @Transactional
    public Cart updateItemFromCart(Product product, Long quantity, User user) {
        Cart cart = findCart(user);
        CartItem cartItem = findCartItem(cart, product.getId());

        cartItem.setQuantity(quantity);

        cartRepository.save(cart);

        return cart;
    }
}
