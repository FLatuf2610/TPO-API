package com.uade.marketplace.data.entities;

import com.uade.marketplace.models.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProductEntity> products;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;

    public UserEntity(String name, String lastName, String email, String password,
                      Role role, List<ProductEntity> products, List<OrderEntity> orders) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.products = products;
        this.orders = orders;
    }
}
