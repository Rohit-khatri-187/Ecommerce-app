package com.rohit.ecommerce_backend.entities;

import com.rohit.ecommerce_backend.utils.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String category;

    private Integer stock;

    @Column(name = "image_url")
    private String imageUrl;


}
