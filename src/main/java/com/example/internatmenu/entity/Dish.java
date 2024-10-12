package com.example.internatmenu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "dish_ingredient",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<DishIngredient> dishIngredients;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "menu_dish",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Menu> menus;

}