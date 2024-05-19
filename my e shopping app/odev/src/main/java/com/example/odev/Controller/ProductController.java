package com.example.odev.Controller;

import com.example.odev.Model.Product;
import com.example.odev.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/urunler")
    public String getProducts(@RequestParam(required = false) String category, Model model) {
        List<Product> products;
        if (category != null && !category.isEmpty()) {
            products = productService.findProductsByCategory(category);
        } else {
            products = productService.findAllProducts();
        }
        model.addAttribute("products", products);
        return "urunler";
    }
    @GetMapping("/detay/{id}")
    public String showProductDetail(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "detay";
    }
}
