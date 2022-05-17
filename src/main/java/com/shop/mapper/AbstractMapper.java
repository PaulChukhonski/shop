package com.shop.mapper;

import java.util.List;

public interface AbstractMapper<ENTITY, DTO> {
    DTO toDto(ENTITY entity);
    List<DTO> toDto(List<ENTITY> entities);
    ENTITY toEntity(DTO dto);
    List<ENTITY> toEntity(List<DTO> dto);
}
