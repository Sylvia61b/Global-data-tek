package com.example.productService.service;

import com.example.productService.entity.Product;
import com.example.productService.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;  // Mocking the ProductRepository

    @InjectMocks
    private ProductServiceImpl productService;  // Injecting the mock into ProductServiceImpl

    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        product = new Product(1L, "Sample Product", 100.0, "Electronics", 50);  // Example Product
    }

    @Test
    void getAllProducts() {
        // Mock the repository to return a list of products
        when(productRepository.findAll()).thenReturn(List.of(product));

        // Call the method to be tested
        List<Product> products = productService.getAllProducts();

        // Verify the result
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Sample Product", products.get(0).getName());
    }

    @Test
    void getProductById() {
        // Mock the repository to return a product when finding by ID
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Call the method
        Product result = productService.getProductById(1L);

        // Verify the result
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Sample Product", result.getName());
    }

    @Test
    void getProductsByPrice() {
        // Mock the repository to return products based on price
        when(productRepository.findByPrice(100.0)).thenReturn(List.of(product));

        // Call the method
        List<Product> products = productService.getProductsByPrice(100.0);

        // Verify the result
        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Sample Product", products.get(0).getName());
    }

    @Test
    void getProductByName() {
        // Mock the repository to return products by name
        when(productRepository.findByName("Sample Product")).thenReturn(Optional.of(product));

        // Call the method
        Product product = productService.getProductByName("Sample Product");

        // Verify the result
        assertNotNull(product);

        assertEquals("Sample Product", product.getName());
    }

    @Test
    void addProduct() {
        // Mock the repository to save the product
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Call the method
        Product result = productService.addProduct(product);

        // Verify the result
        assertNotNull(result);
        assertEquals("Sample Product", result.getName());
        assertEquals(100.0, result.getPrice());
        assertEquals("Electronics", result.getCategory());
        assertEquals(50, result.getQuantity());
        verify(productRepository, times(1)).save(product);  // Verify that save method was called once
    }

    @Test
    void updateProduct() {
        // Mock the repository to find the product by ID and then update
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Call the method to update product
        Product updatedProduct = productService.updateProduct(1L, new Product(1L, "Updated Product", 120.0, "Electronics", 60));

        // Verify the result
        assertNotNull(updatedProduct);
        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals(120.0, updatedProduct.getPrice());
        assertEquals("Electronics", updatedProduct.getCategory());
        assertEquals(60, updatedProduct.getQuantity());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void deleteProduct() {
        // Mock the repository to find the product by ID
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Call the method to delete the product
        productService.deleteProduct(1L);

        // Verify that the delete method was called once
        verify(productRepository, times(1)).deleteById(1L);
    }

    @Test
    void checkAndUpdateProductQuantity() {
        // Mock the repository to return a product
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Call the method to check and update the product quantity
        productService.checkAndUpdateProductQuantity(1L, 100);

        // Verify that the save method was called with the updated quantity
        verify(productRepository, times(1)).save(any(Product.class));
        assertEquals(100, product.getQuantity());  // Ensure the quantity is updated
    }

}