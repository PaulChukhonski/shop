package com.shop.service;

import com.shop.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);
    void saveNew(User user);
    void update(User user);
    void save(User user);
    List<User> findAll();
    User findById(Long id);
    UserDetails loadUserByUsername(String username);
//    void mergeAndSaveFromAccount(User user);
}
