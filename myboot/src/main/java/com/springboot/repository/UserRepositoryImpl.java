package com.springboot.repository;

import com.springboot.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    List<User> users = new ArrayList<>();
    @Override
    public void addUser(User user) {
        System.out.println("Before add, the size of the list is "+ users.size());
        users.add(user);
        System.out.println("The size of users is "+ users.size());
    }

    @Override
    public User findUserById(String userId) {
        return null;
    }
}
