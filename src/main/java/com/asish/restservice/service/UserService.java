package com.asish.restservice.service;

import java.util.List;

import com.asish.restservice.entities.User;

public interface UserService {

	List<User> getAllUsers();

	User createUser(User user);

	User getUserById(long id);

	User updateUserById(User user, long id);

	void deleteUserById(long id);

	User getUserByUsername(String username);
}
