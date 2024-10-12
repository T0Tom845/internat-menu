package com.example.internatmenu.service;

import com.example.internatmenu.entity.Product;
import com.example.internatmenu.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }
    @Transactional
    public void updateProduct(Product product) {
        productRepository.findById(product.getId()).ifPresentOrElse(product1 -> {
            product1.setName(product.getName());
            product1.setPrice(product.getPrice());
        }, () ->{
            throw new NoSuchElementException();
        });
    }
    @Transactional
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
