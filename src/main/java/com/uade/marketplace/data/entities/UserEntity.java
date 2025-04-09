package com.uade.marketplace.data.entities;

import com.uade.marketplace.models.UserType;
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
    private UserType userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProductEntity> products;

    @OneToMany(mappedBy = "user")
    private List<OrderEntity> orders;

    public UserEntity(String name, String lastName, String email, String password,
                      UserType userType, List<ProductEntity> products, List<OrderEntity> orders) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.products = products;
        this.orders = orders;
    }
}
