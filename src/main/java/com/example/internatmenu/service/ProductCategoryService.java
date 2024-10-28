package com.example.internatmenu.service;

import com.example.internatmenu.entity.ProductCategory;
import com.example.internatmenu.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory findById(Long id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    public void save(ProductCategory category) {
        productCategoryRepository.save(category);
    }

    public void delete(Long id) {
        productCategoryRepository.deleteById(id);
    }
    public void update(Long id, ProductCategory category) {
        ProductCategory existingCategory = productCategoryRepository.findById(id).orElseThrow();
        existingCategory.setName(category.getName());
        productCategoryRepository.save(existingCategory);
    }
}
