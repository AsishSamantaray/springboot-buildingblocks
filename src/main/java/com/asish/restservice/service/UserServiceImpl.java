package com.asish.restservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asish.restservice.entities.User;
import com.asish.restservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("No User Found"));
	}

	@Override
	public User updateUserById(User user, long id) {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isPresent()) {
			user.setId(userOpt.get().getId());
			return userRepository.save(user);
		} else {
			throw new RuntimeException("No User Found");
		}
	}

	@Override
	public void deleteUserById(long id) {
		userRepository.delete(userRepository.findById(id).get());
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("No User Found"));
	}

}
