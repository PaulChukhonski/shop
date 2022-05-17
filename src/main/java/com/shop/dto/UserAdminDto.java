package com.shop.dto;

import com.shop.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class UserAdminDto extends UserRegisterDto {
    private Long id;
    @NotEmpty(message = "Пожалуйста, выберите роли")
    private Set<Role> roles;
}
