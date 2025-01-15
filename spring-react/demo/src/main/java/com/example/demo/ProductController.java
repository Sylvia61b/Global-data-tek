package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class ProductController {

    private final List<Product> products;

    // Constructor to initialize the product list
    public ProductController() {
        products = new ArrayList<>();
        products.add(new Product(1, "SamSung", 1500, "tv"));
        products.add(new Product(2, "iPhone", 725, "phone"));
    }


    @GetMapping("/allProducts")
    public List<Product> getAllProducts() {
        return products;
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product){
        System.out.println("product is "+product);
        return "Product Added Successfully";
    }
}
