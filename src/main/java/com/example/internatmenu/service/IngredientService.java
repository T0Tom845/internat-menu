package com.example.internatmenu.service;

import com.example.internatmenu.entity.Ingredient;
import com.example.internatmenu.repository.IngredientRepository;
import com.example.internatmenu.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    private final ProductRepository productRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(Long id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

    public Ingredient addIngredient(Long productId, BigDecimal quantity) {
        Ingredient ingredient = new Ingredient();
        ingredient.setProduct(productRepository.findById(productId).orElseThrow());
        ingredient.setQuantity(quantity);
        ingredientRepository.save(ingredient);
        return ingredient;
    }
}

