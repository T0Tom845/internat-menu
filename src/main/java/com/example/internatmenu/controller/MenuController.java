package com.example.internatmenu.controller;

import com.example.internatmenu.entity.Menu;
import com.example.internatmenu.entity.MenuCostDto;
import com.example.internatmenu.service.DishService;
import com.example.internatmenu.service.MenuService;
import com.example.internatmenu.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    private final DishService dishService;

    private final ProductService productService;

    @GetMapping
    public String listMenus(Model model) {
        List<Menu> menus = menuService.findAll();
        model.addAttribute("menus", menus);
        return "menu/list";
    }

    @GetMapping("/new")
    public String showNewMenuForm(Model model) {
        model.addAttribute("menu", new Menu());
        return "menu/new";
    }

    @PostMapping("/new")
    public String createMenu(@RequestParam("dateMenu") String dateMenu) {
        Menu menu = new Menu();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(dateMenu);
            menu.setDateMenu(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        menuService.save(menu);
        return "redirect:/menus";
    }

    @GetMapping("/edit/{id}")
    public String showEditMenuForm(@PathVariable Long id, Model model) {
        Menu menu = menuService.findById(id).orElseThrow();
        model.addAttribute("menu", menu);
        return "menu/edit-form";
    }

    @PostMapping("/edit/{id}")
    public String updateMenu(@PathVariable Long id, @RequestParam String menuDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatter.parse(menuDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        menuService.update(id, date);
        return "redirect:/menus";
    }

    @GetMapping("/delete/{id}")
    public String deleteMenu(@PathVariable Long id) {
        menuService.delete(id);
        return "redirect:/menus";
    }

    @GetMapping("/with-products")
    public String getAllMenusWithProducts(Model model) {
        List<Menu> menus = menuService.findAll();
        model.addAttribute("menus", menus);
        return "menu/list_with_products";
    }

    @GetMapping("/addDish/{menuId}")
    public String showAddDishForm(Model model, @PathVariable Long menuId){
        model.addAttribute("menuId", menuId);
        model.addAttribute("dishes", dishService.getAllDishes());
        return "menu/add-dish";
    }
    @PostMapping("/addDish/{menuId}")
    public String addDishToMenu(@RequestParam Long dishId, @PathVariable Long menuId) {
        Menu menu = menuService.findById(menuId).orElseThrow();
        menuService.addDishToMenu(menu, dishId);
        return "redirect:/menus";
    }
    @GetMapping("/deleteDish/{menuId}/{dishId}")
    public String deleteIngredientFromDish(@PathVariable Long menuId, @PathVariable Long dishId) {
        menuService.removeDishFromMenu(menuId, dishId);
        return "redirect:/menus";
    }

    @GetMapping("/search-by-product")
    public String showSearchForm(Model model) {
        model.addAttribute("products", productService.getAllProducts()); // Получаем список всех продуктов
        model.addAttribute("menus", new ArrayList<>(0));
        return "menu/menus-by-product";
    }

    @PostMapping("/search-by-product")
    public String searchMenusByProduct(@RequestParam Long productId, Model model) {
        List<Menu> menus = menuService.findMenusByProductId(productId);
        model.addAttribute("menus", menus);
        model.addAttribute("products", productService.getAllProducts());
        return "menu/menus-by-product";
    }
    @GetMapping("/costs")
    public String getAllMenusWithCosts(Model model) {
        List<MenuCostDto> menuCosts = menuService.getAllMenusWithCosts();
        model.addAttribute("menuCosts", menuCosts);
        return "menu/menu-cost"; // Имя вашего шаблона
    }

}
