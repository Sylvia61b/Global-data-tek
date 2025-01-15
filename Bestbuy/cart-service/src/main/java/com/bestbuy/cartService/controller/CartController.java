package com.bestbuy.cartService.controller;

import com.bestbuy.cartService.dto.CartDTO;
import com.bestbuy.cartService.dto.CartItemDTO;
import com.bestbuy.cartService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable Long userId) {
        CartDTO cartDTO = cartService.getCartByUserId(userId);
        if (cartDTO != null) {
            return ResponseEntity.ok(cartDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Add an item to the cart
    @PostMapping("/{userId}/items")
    public ResponseEntity<CartDTO> addItemToCart(@PathVariable Long userId, @RequestBody CartItemDTO cartItemDTO) {
        CartDTO updatedCart = cartService.addItemToCart(userId, cartItemDTO);
        return ResponseEntity.ok(updatedCart);
    }

    // Remove an item from the cart
    @DeleteMapping("/{userId}/items/{productId}")
    public ResponseEntity<CartDTO> removeItemFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        CartDTO updatedCart = cartService.removeItemFromCart(userId, productId);
        if (updatedCart != null) {
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update the quantity of an item in the cart
    @PutMapping("/{userId}/items/{productId}")
    public ResponseEntity<CartDTO> updateItemQuantity(@PathVariable Long userId, @PathVariable Long productId, @RequestParam Integer quantity) {
        CartDTO updatedCart = cartService.updateItemQuantity(userId, productId, quantity);
        if (updatedCart != null) {
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a cart for the user if it doesn't exist
    @PostMapping("/{userId}")
    public ResponseEntity<CartDTO> createCart(@PathVariable Long userId) {
        CartDTO newCart = cartService.createCart(userId);
        return ResponseEntity.ok(newCart);
    }
}

