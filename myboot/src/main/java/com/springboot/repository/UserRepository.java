package com.springboot.repository;

import com.springboot.model.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    void addUser(User user);

    User findUserById(String userId);
}
