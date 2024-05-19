package com.example.odev.service;

import com.example.odev.Model.Product;
import com.example.odev.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> findProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
