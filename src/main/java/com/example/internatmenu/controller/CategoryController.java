package com.example.internatmenu.controller;

import com.example.internatmenu.entity.Dish;
import com.example.internatmenu.entity.ProductCategory;
import com.example.internatmenu.repository.ProductCategoryRepository;
import com.example.internatmenu.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final ProductCategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        List<ProductCategory> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "categories/list";
    }

    @GetMapping("/new")
    public String showNewCategoryForm(Model model) {
        model.addAttribute("category", new ProductCategory());
        return "categories/new-form";
    }

    @PostMapping("/new")
    public String createCategory(@ModelAttribute ProductCategory category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model) {
        ProductCategory category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "categories/edit-form";
    }

    @PostMapping("/edit/{id}")
    public String updateDish(@PathVariable Long id, @ModelAttribute ProductCategory category) {
        categoryService.update(id, category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
