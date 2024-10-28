package com.example.internatmenu.controller;

import com.example.internatmenu.entity.Dish;
import com.example.internatmenu.entity.Ingredient;
import com.example.internatmenu.entity.Product;
import com.example.internatmenu.service.DishService;
import com.example.internatmenu.service.IngredientService;
import com.example.internatmenu.service.MenuService;
import com.example.internatmenu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    private final MenuService menuService;

    private final ProductService productService;

    private final IngredientService ingredientService;

    @GetMapping
    public String listDishes(Model model) {
        List<Dish> dishes = dishService.findAll();
        model.addAttribute("dishes", dishes);
        return "dish/list";
    }

    @GetMapping("/new")
    public String showNewDishForm(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("menus", menuService.findAll());
        return "dish/new-form";
    }

    @PostMapping("/new")
    public String createDish(@ModelAttribute Dish dish) {
        dishService.save(dish);
        return "redirect:/dishes";
    }

    @GetMapping("/edit/{id}")
    public String editDish(@PathVariable("id") Long id, Model model) {
        Dish dish = dishService.findById(id).orElseThrow();
        model.addAttribute("dish", dish);
        return "dish/edit-form";
    }

    @PostMapping("/edit/{id}")
    public String updateDish(@PathVariable Long id, @ModelAttribute Dish dish) {
        dishService.update(id, dish).orElseThrow();
        return "redirect:/dishes";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }


    @PostMapping("/{id}/add-ingredients")
    public String saveIngredients(@PathVariable("id") Long id, @ModelAttribute Dish dishForm) {

        dishService.addIngredientsToDish(id, dishForm.getIngredients());
        return "redirect:/dishes";
    }

    @GetMapping("/deleteIngredient/{dishId}/{ingredientId}")
    public String deleteIngredientFromDish(@PathVariable Long dishId, @PathVariable Long ingredientId) {
        dishService.removeIngredientFromDish(dishId, ingredientId);
        return "redirect:/dishes"; // перенаправляем обратно на страницу с блюдами
    }

    @GetMapping("/addIngredient/{id}")
    public String showAddIngredientForm(Model model, @PathVariable(name = "id") Integer id) {
        model.addAttribute("dishId", id);
        model.addAttribute("products", productService.getAllProducts());
        return "dish/add-ingredient";
    }
    @PostMapping("/addIngredient/{id}")
    public String addIngredient(@RequestParam Long productId,
                                @RequestParam BigDecimal quantity,
                                @PathVariable(name = "id") Long dishId) {
        Ingredient ingredient = ingredientService.addIngredient(productId, quantity);
        dishService.addIngredientToDish(dishId, ingredient);
        return "redirect:/dishes"; // Перенаправление после добавления
    }
}
