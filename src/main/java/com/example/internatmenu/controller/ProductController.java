package com.example.internatmenu.controller;

import com.example.internatmenu.entity.Product;
import com.example.internatmenu.repository.ProductRepository;
import com.example.internatmenu.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        Iterable<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-new-form";
    }

    @PostMapping("/new")
    public String saveProduct(@ModelAttribute("product") @Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product-form";
        }
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/edit/{id:\\d+}")
    public String editProduct(@ModelAttribute Product product) {
        productService.updateProduct(product);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id:\\d+}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product-edit-form";
    }



    @GetMapping("/delete/{id:\\d+}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
