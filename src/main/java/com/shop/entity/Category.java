package com.shop.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="category", uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
@Data
@FieldNameConstants
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Пожалуйста, введите название категории")
    private String name;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_date")
    private Date modifyDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Product> productList;

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