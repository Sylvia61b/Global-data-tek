package com.example.productService.service;

import com.example.productService.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getProductsByPrice(Double price);
    Product getProductByName(String name);
    Product addProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    int checkAndUpdateProductQuantity(Long productId, int threshold);

}
