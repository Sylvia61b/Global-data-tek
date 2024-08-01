package com.springboot.service;

import com.springboot.model.User;
import com.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public User findUserById(String userId) {
        return userRepository.findUserById(userId);
    }
}
