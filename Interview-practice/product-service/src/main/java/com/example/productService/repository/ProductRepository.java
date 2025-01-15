package com.example.productService.repository;

import com.example.productService.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCategory(String category);
    Optional<Product> findByName(String name);
    List<Product> findByPrice(Double price);
}
