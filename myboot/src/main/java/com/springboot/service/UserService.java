package com.springboot.service;

import com.springboot.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void addUser(User user);
}
