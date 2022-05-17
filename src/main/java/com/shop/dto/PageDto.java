package com.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class PageDto<T> implements Serializable {
    private int total;
    private List<T> records;
}
