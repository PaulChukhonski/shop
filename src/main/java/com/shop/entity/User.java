package com.shop.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@FieldNameConstants
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "Пожалуйста, введите имя")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    @NotEmpty(message = "Пожалуйста, введите фамилию")
    private String lastName;

    @Column(name = "email")
    @NotEmpty(message = "Пожалуйста, введите почту")
    @Email(message = "Пожалуйста, введите корректную почту")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Пожалуйста, введите пароль")
    @Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
    private String password;

    @Column(name =  "phone")
    private String phone;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_date")
    private Date modifyDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;

    @PrePersist
    private void onCreate() {
        createDate = new Date();
        modifyDate = new Date();
    }

    @PreUpdate
    private void onUpdate() {
        modifyDate = new Date();
    }
}
