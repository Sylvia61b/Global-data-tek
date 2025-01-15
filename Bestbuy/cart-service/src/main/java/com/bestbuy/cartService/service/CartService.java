package com.bestbuy.cartService.service;

import com.bestbuy.cartService.dto.CartDTO;
import com.bestbuy.cartService.dto.CartItemDTO;
import com.bestbuy.cartService.dto.UserDTO;
import com.bestbuy.cartService.entity.Cart;
import com.bestbuy.cartService.entity.CartItem;
import com.bestbuy.cartService.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CartRepository cartRepository;

    Logger logger= LoggerFactory.getLogger(CartService.class);

    private final String userServiceUrl = "lb://user-service/users/{userId}";

    public UserDTO getUser(Long userId) {
        logger.info("Fetching user details for userId: {}", userId);
        // Call UserService and get UserDTO
        ResponseEntity<UserDTO> response = restTemplate.exchange(
                userServiceUrl,
                HttpMethod.GET,
                null,
                UserDTO.class,
                userId
        );
        return response.getBody();
    }

    public CartDTO getCartByUserId(Long userId) {
        logger.info("Retrieving cart for userId: {}", userId);
        // Logic to retrieve cart for the given userId
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if (cart.isPresent()) {
            return convertToDTO(cart.get());
        } else {
            return null;
        }
    }

    // Create a cart for the user if it doesn't exist
    public CartDTO createCart(Long userId) {
        Optional<Cart> existingCart = cartRepository.findByUserId(userId);
        if (existingCart.isPresent()) {
            return convertToDTO(existingCart.get()); // Return the existing cart if found
        }
        Cart newCart = new Cart();
        newCart.setUserId(userId);
        newCart.setItems(new ArrayList<>()); // Initialize with an empty list of items
        cartRepository.save(newCart); // Save the new cart to the database
        return convertToDTO(newCart); // Convert and return the new cart as a DTO
    }

    public CartDTO addItemToCart(Long userId, CartItemDTO cartItemDTO) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        // Check if the cart is present
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();  // Extract the Cart

            // Add item to the cart
            CartItem cartItem = new CartItem(cartItemDTO.getProductId(), cartItemDTO.getQuantity());
            cart.getItems().add(cartItem);  // Access items now

            cartRepository.save(cart);
            return convertToDTO(cart);
        } else {
            // Handle case when the cart is not found
            System.out.println("Cart not found for user " + userId);
            return null;
        }
    }

    public CartDTO removeItemFromCart(Long userId, Long productId) {
        // Find the cart for the user
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        // Check if the cart exists
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();  // Get the Cart object

            // Remove the item from the cart
            cart.getItems().removeIf(item -> item.getProductId().equals(productId));

            // Save the updated cart
            cartRepository.save(cart);

            // Return the updated CartDTO
            return convertToDTO(cart);
        }

        // Return null if cart is not found
        return null;
    }


    public CartDTO updateItemQuantity(Long userId, Long productId, Integer quantity) {
        // Find the cart for the user
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        // Check if the cart exists
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();  // Get the Cart object

            // Iterate over the items and update the quantity of the matching item
            for (CartItem item : cart.getItems()) {
                if (item.getProductId().equals(productId)) {
                    item.setQuantity(quantity);  // Update the item quantity
                    cartRepository.save(cart);  // Save the updated cart
                    return convertToDTO(cart);  // Return updated CartDTO
                }
            }
        }

        // Return null if cart or item is not found
        return null;
    }



    private CartDTO convertToDTO(Cart cart) {
        List<CartItemDTO> cartItemDTOs = cart.getItems().stream()
                .map(cartItem -> new CartItemDTO(cartItem.getProductId(), cartItem.getQuantity()))
                .collect(Collectors.toList());

        return new CartDTO(cart.getUserId(), cartItemDTOs);
    }

}

