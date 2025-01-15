package com.bestbuy.cartService.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor // Generates a no-args constructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique ID for the cart item

    private Long productId; // Product ID (can be a reference to the Product entity)

    private Integer quantity; // Quantity of the product in the cart

    @ManyToOne
    @JoinColumn(name = "cart_id")  // Foreign key to Cart
    private Cart cart; // Reference to the cart that owns the item

    // Constructor to create a CartItem with productId and quantity
    public CartItem(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}

