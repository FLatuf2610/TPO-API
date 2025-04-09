package com.uade.marketplace.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int quantity;
    private String imageUrl;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
