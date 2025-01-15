package com.bestbuy.userService.service;

import com.bestbuy.userService.dto.CartDTO;
import com.bestbuy.userService.entity.User;
import com.bestbuy.userService.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    //private final String cartServiceUrl = "http://cart-service/carts/{userId}";
    String cartServiceUrl = "lb://cart-service/carts/{userId}";

    @CircuitBreaker(name = "cartServiceCB", fallbackMethod = "fallbackMethod")
    @Retry(name = "cartServiceRetry")
    public CartDTO getUserCart(Long userId) {
        return restTemplate.getForObject(cartServiceUrl, CartDTO.class, userId);
    }
    public CartDTO fallbackMethod(Long userId, Throwable throwable) {
        // Log the exception
        System.out.println("Fallback triggered for user " + userId + ": " + throwable.getMessage());

        // Return a default or empty CartDTO
        CartDTO fallbackCart = new CartDTO();
        fallbackCart.setUserId(userId);
        fallbackCart.setItems(Collections.emptyList()); // Empty list, assuming CartDTO has a list of items

        return fallbackCart;
    }

    // Method to find user by username
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method to save a new user
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}

