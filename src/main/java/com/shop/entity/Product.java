package com.shop.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Entity
@Table(name = "product")
@Data
@FieldNameConstants
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "year")
    private String year;

    @Column(name = "code")
//    @NotEmpty(message = "Please, enter product code")
    private String code;

    @Column(name = "name")
    @NotEmpty(message = "Пожалуйста, введите название товара")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    @NotNull(message = "Пожалуйста, введите стоимость товара")
    private Double price;

    @Column(name = "stock")
    @NotNull(message = "Пожалуйста, введите количество товара")
    @Min(value = 0, message = "Количество товара должно быть больше 0")
    private Long stock;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "modify_date")
    private Date modifyDate;

//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Image> imageList;

    @OneToOne(fetch = FetchType.EAGER)
    private Image image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    private void onCreate() {
        createDate = new Date();
        modifyDate = new Date();
    }

    @PreUpdate
    private void onUpdate() {
        modifyDate = new Date();
    }

    @Transient
    private MultipartFile multipartFile;

    @Transient
    public String getBase64Image() {
        return Base64.getEncoder().encodeToString(image.getContent());
    }
}