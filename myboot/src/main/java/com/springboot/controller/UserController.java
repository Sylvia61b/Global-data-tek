package com.springboot.controller;


import java.util.ArrayList;
import java.util.List;

import com.springboot.repository.UserRepository;
import com.springboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Login;
import com.springboot.model.User;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    //@Autowired
    //UserRepository userRepository;

    public UserController() {
        System.out.println("UserController no-arg constr..");
    }


    @GetMapping("/userById/{userId}")
    public User findUserById(@PathVariable String userId) {

        System.out.println("inside findUserById..."+userId);

        return userService.findUserById(userId);
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user){
        System.out.println(user.getUsername()+","+user.getPassword());
        userService.addUser(user);
        return "Sign Up successfully";
    }


    @PostMapping("/login")
    public String login( @RequestBody Login login) {

        System.out.println(login.getUsername()+", "+login.getPassword());

        return "Login Successfull";
    }

}
