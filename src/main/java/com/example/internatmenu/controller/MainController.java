package com.example.internatmenu.controller;

import com.example.internatmenu.entity.Dish;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @GetMapping
    public String showMenu() {
        return "index";
    }

}
