package com.shop.service.impl;

import com.shop.entity.CartItem;
import com.shop.enumeration.UserRole;
import com.shop.entity.Role;
import com.shop.repository.RoleRepository;
import com.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public Role findByName(UserRole name) {
        return roleRepository.findByName(name);
    }

    @Override
    @Transactional
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
