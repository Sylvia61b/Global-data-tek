package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public String addUser(User user) {
		
		return userRepository.addUser(user);
	}

	// @Override
	// public User login(String username, String password) {
		
	// 	return userRepository.findUser(username, password);
	// }

	// @Override
	// public List<User> getAllUsers() {
		
	// 	return userRepository.allUsers();
	// }

	// @Override
	// public String deleteUser(String userId) {
		
	// 	return userRepository.delete(userId);
	// }

	// @Override
	// public User findUser(String username) {

	// 	return userRepository.findUser(username);
	// }

	// @Override
	// public void updateUser(User user) {
		
	// 	userRepository.updateUser(user);
		
	// }

}

