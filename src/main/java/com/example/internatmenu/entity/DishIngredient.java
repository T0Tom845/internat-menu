package com.example.internatmenu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "dish_ingredient")
public class DishIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Double quantity;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Dish> Product;
}
