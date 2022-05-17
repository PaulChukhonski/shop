package com.shop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserAccountDto {
    private Long id;
    @NotEmpty(message = "Пожалуйста, введите имя")
    private String firstName;
    private String middleName;
    @NotEmpty(message = "Пожалуйста, введите фамилию")
    private String lastName;
    @NotEmpty(message = "Пожалуйста, введите почту")
    @Email(message = "Пожалуйста, введите корректную почту")
    private String email;
    private String password;
    private String phone;

    private Long addressId;
    @NotEmpty(message = "Пожалуйста, введите город")
    private String city;
    @NotEmpty(message = "Пожалуйста, введите улицу")
    private String street;
    @NotEmpty(message = "Пожалуйста, введите номер здания")
    private String building;
    @NotNull(message = "Пожалуйста, введите номер квартиры")
    @Min(value = 1, message = "Номер квартиры должен быть больше 0")
    private Integer room;
    private Integer floor;
}
