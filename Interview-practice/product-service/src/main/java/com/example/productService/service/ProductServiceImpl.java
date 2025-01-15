package com.example.productService.service;


import com.example.productService.entity.Product;
import com.example.productService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("Products not found with id "+ id));
    }

    @Override
    public List<Product> getProductsByPrice(Double price) {
        return productRepository.findByPrice(price);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name).orElseThrow(()-> new RuntimeException("Products not found with name "+name));
    }

    @Override
    @Transactional
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true )
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setName(product.getName());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public int checkAndUpdateProductQuantity(Long productId, int threshold) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        // Check if the current quantity is less than the threshold
        if (product.getQuantity() < threshold) {
            // Calculate the amount to add to reach the threshold
            int quantityToAdd = threshold - product.getQuantity();

            // Update the product quantity to meet the threshold
            product.setQuantity(product.getQuantity() + quantityToAdd);

            productRepository.save(product);
        }

        // Return the current (or updated) quantity
        return product.getQuantity();
    }


}
