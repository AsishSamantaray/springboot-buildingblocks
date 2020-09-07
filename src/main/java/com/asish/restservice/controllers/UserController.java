package com.asish.restservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asish.restservice.entities.User;
import com.asish.restservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping("/{id}")
	public User getUserById(@PathVariable long id) {
		return userService.getUserById(id);
	}

	@PutMapping("/{id}")
	public User updateUserById(@RequestBody User user, @PathVariable long id) {
		return userService.updateUserById(user, id);
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable long id) {
		userService.deleteUserById(id);
	}

	@GetMapping("/byusername/{username}")
	public User getUserByUsername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}
}
