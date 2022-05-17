package com.shop.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="image")
@Data
@FieldNameConstants
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;

    @Column(name = "content")
    @Lob
    private byte[] content;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "create_date")
    private Date createDate;

    @PrePersist
    private void onCreate() {
        createDate = new Date();
    }
}
