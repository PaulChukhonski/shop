package com.shop.entity;

import com.shop.enumeration.UserRole;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
@FieldNameConstants
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name="name")
    @Enumerated(EnumType.STRING)
    private UserRole name;
}
