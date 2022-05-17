package com.shop.service;

import com.shop.enumeration.UserRole;
import com.shop.entity.Role;

import java.util.List;

public interface RoleService {
    Role findByName(UserRole name);
    List<Role> findAll();
}
