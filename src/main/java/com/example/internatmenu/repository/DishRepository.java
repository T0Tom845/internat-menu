package com.example.internatmenu.repository;

import com.example.internatmenu.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {}
