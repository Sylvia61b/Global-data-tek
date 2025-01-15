package com.bestbuy.userService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID
    private Long id;  // The unique ID for the User

    @Column(name = "username", nullable = false)
    private String username;  // The  username field

    @Column(name = "password", nullable = false)
    private String password;  // The password field

    @Column(name = "roles", nullable = false)
    private String roles;  // The roles field, could store roles as a comma-separated string

}

