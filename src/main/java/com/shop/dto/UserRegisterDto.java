package com.shop.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRegisterDto {
    @NotEmpty(message = "Пожалуйста, введите имя")
    private String firstName;
    @NotEmpty(message = "Пожалуйста, введите фамилию")
    private String lastName;
    @NotEmpty(message = "Пожалуйста, введите почту")
    @Email(message = "Пожалуйста, введите корректную почту")
    private String email;
    @NotEmpty(message = "Пожалуйста, введите пароль")
    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    private String password;
}
