package com.example.internatmenu.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "menu_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    private Set<Menu> menus;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "dish_ingredient",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    public void addMenus(Collection<? extends Menu> menus) {
        this.menus.addAll(menus);
    }

    public void addIngredients(Collection<? extends Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
    }

    public void removeFromIngredients(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }


    // Getters and setters
}