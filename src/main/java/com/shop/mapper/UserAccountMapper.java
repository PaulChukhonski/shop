package com.shop.mapper;

import com.shop.dto.UserAccountDto;
import com.shop.entity.Address;
import com.shop.entity.User;
import org.mapstruct.*;

@Mapper(config = MapstructConfig.class)
public interface UserAccountMapper extends AbstractMapper<User, UserAccountDto> {
    UserAccountDto toDto(User entity);
    User toEntity(UserAccountDto dto);

    @AfterMapping
    default void setAddress(@MappingTarget User entity, UserAccountDto dto) {
        Address address = new Address();
        address.setCity(dto.getCity());
        address.setStreet(dto.getStreet());
        address.setBuilding(dto.getBuilding());
        address.setRoom(dto.getRoom());
        address.setFloor(dto.getFloor());
        entity.setAddress(address);
    }

    @AfterMapping
    default void setAddress(@MappingTarget UserAccountDto dto, User entity) {
        if(entity.getAddress() != null) {
            dto.setAddressId(entity.getAddress().getId());
            dto.setCity(entity.getAddress().getCity());
            dto.setStreet(entity.getAddress().getStreet());
            dto.setBuilding(entity.getAddress().getBuilding());
            dto.setRoom(entity.getAddress().getRoom());
            dto.setFloor(entity.getAddress().getFloor());
        }
    }
}
