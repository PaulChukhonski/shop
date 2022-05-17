package com.shop.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
@Data
@FieldNameConstants
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "city")
    @NotEmpty(message = "Пожалуйста, введите город")
    private String city;

    @Column(name = "street")
    @NotEmpty(message = "Пожалуйста, введите улицу")
    private String street;

    @Column(name = "building")
    @NotEmpty(message = "Пожалуйста, введите номер здания")
    private String building;

    @Column(name = "room")
    @NotNull(message = "Пожалуйста, введите номер квартиры")
    @Min(value = 1, message = "Номер квартиры должен быть больше 0")
    private Integer room;

    @Column(name = "floor")
    private Integer floor;

    @Transient
    public String getFullAddress() {
        StringBuilder sb = new StringBuilder();

        if(getCity() != null) {
            sb.append(getCity()).append(", ");
        }

        if(getStreet() != null) {
            sb.append(getStreet()).append(", ");
        }

        if(getBuilding() != null) {
            sb.append(getBuilding()).append(", ");
        }

        if(getRoom() != null) {
            sb.append(getRoom()).append(", ");
        }

        if(getFloor() != null) {
            sb.append(getFloor()).append(", ");
        }

        return sb.toString();
    }
}
