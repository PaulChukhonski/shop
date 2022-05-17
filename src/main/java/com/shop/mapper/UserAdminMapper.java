package com.shop.mapper;

import com.shop.dto.UserAdminDto;
import com.shop.dto.UserRegisterDto;
import com.shop.entity.User;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public interface UserAdminMapper extends AbstractMapper<User, UserAdminDto> {
    UserAdminDto toDto(User entity);
    User toEntity(UserAdminDto dto);
}
