package com.example.internatmenu.service;

import com.example.internatmenu.entity.Dish;
import com.example.internatmenu.entity.Ingredient;
import com.example.internatmenu.entity.Menu;
import com.example.internatmenu.repository.DishRepository;
import com.example.internatmenu.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DishService {

    private final DishRepository dishRepository;

    private final IngredientRepository ingredientRepository;


    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id);
    }

    public Dish create(Dish dish) {
        return dishRepository.save(dish);
    }

    public Optional<Dish> update(Long id, Dish dish) {
        return dishRepository.findById(id).map(existingDish -> {
            existingDish.setName(dish.getName());
            if (dish.getMenus() != null) existingDish.addMenus(dish.getMenus());
            return dishRepository.save(existingDish);
        });
    }

    public boolean delete(Long id) {
        if (dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void save(Dish dish) {
        dishRepository.save(dish);
    }

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public void addIngredientsToDish(Long id,  Collection<? extends Ingredient> ingredients) {
        Dish existingDish = findById(id).orElseThrow();

        existingDish.addIngredients(ingredients);

        dishRepository.save(existingDish);
    }

    public void removeIngredientFromDish(Long dishId, Long ingredientId) {
        Dish dish = dishRepository.findById(dishId).orElseThrow();
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElseThrow();
        dish.removeFromIngredients(ingredient);
        ingredientRepository.delete(ingredient);
        dishRepository.save(dish);

    }

    public void addIngredientToDish(Long dishId, Ingredient ingredient) {
        Dish dish = dishRepository.findById(dishId).orElseThrow();
        dish.addIngredient(ingredient);
        dishRepository.save(dish);

    }

}
