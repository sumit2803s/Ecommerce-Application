package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @NotBlank
    @Size(min=3,message = "Product Name must contain at least 3 characters")
    private String productName;
    @NotBlank
    @Size(min=6,message = "Product Description must contain at least 6 characters")
    private String description;
    private Integer quantity;
    private String image;
    private double price;
    private double discount;
    private double specialPrice;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
