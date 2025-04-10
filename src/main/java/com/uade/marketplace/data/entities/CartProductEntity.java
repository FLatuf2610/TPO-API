package com.uade.marketplace.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private CartEntity order;
}
