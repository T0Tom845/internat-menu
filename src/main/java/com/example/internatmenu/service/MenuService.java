package com.example.internatmenu.service;

import com.example.internatmenu.entity.Dish;
import com.example.internatmenu.entity.Menu;
import com.example.internatmenu.entity.MenuCostDto;
import com.example.internatmenu.repository.DishRepository;
import com.example.internatmenu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.math.RoundingMode;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private final DishRepository dishRepository;

    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    public Optional<Menu> findById(Long id) {
        return menuRepository.findById(id);
    }

    public Menu save(Menu menu) {
        return menuRepository.save(menu);
    }

    public Optional<Menu> update(Long id, Menu menu) {
        return menuRepository.findById(id).map(existingMenu -> {
            existingMenu.setDateMenu(menu.getDateMenu());
            return menuRepository.save(existingMenu);
        });
    }
    public void update(Long id, Date date) {
        Menu menu = this.menuRepository.findById(id).orElseThrow();
        menu.setDateMenu(date);
        this.menuRepository.save(menu);

    }

    public boolean delete(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void saveMenu(Menu menu) {
        menuRepository.save(menu);
    }

    public void addDishToMenu(Menu menu, Long dishId) {
        menu.getDishes().add(dishRepository.findById(dishId).orElseThrow());
        menuRepository.save(menu);
    }

    public void removeDishFromMenu(Long menuId, Long dishId) {
        Menu menu = menuRepository.findById(menuId).orElseThrow();
        Dish dish = dishRepository.findById(dishId).orElseThrow();
        menu.removeDish(dish);
        menuRepository.save(menu);
    }

    public List<Menu> findMenusByProductId(Long productId) {
        return menuRepository.findAll().stream()
                .filter(menu -> menu.getDishes().stream()
                        .flatMap(dish -> dish.getIngredients().stream())
                        .anyMatch(ingredient -> ingredient.getProduct().getId().equals(productId)))
                .collect(Collectors.toList());
    }
    public List<MenuCostDto> getAllMenusWithCosts() {
        return menuRepository.findAll().stream()
                .map(menu -> {
                    BigDecimal totalCost = calculateMenuCost(menu.getId());

                    BigDecimal averageCost = menu.getDishes().size() !=0 ?
                            totalCost.divide(BigDecimal.valueOf(menu.getDishes().size()), RoundingMode.UP) :
                            BigDecimal.ZERO;

                    return new MenuCostDto(menu.getId(), menu.getDateMenu(), totalCost, averageCost);
                })
                .collect(Collectors.toList());
    }

    private BigDecimal calculateMenuCost(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        return menu.getDishes().stream()
                .flatMap(dish -> dish.getIngredients().stream())
                .map(ingredient -> ingredient.getProduct().getUnitPrice().multiply(ingredient.getQuantity()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

