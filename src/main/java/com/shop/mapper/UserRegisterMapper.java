package com.shop.mapper;

import com.shop.dto.UserRegisterDto;
import com.shop.entity.User;
import org.mapstruct.Mapper;

@Mapper(config = MapstructConfig.class)
public interface UserRegisterMapper extends AbstractMapper<User, UserRegisterDto> {
    UserRegisterDto toDto(User entity);
    User toEntity(UserRegisterDto dto);
}
