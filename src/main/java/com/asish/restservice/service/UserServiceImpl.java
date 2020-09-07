package com.asish.restservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.asish.restservice.entities.User;
import com.asish.restservice.exception.UserExistsException;
import com.asish.restservice.exception.UserNotFoundException;
import com.asish.restservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Value("${USER_NOT_FOUND_MESSAGE}")
	private String userNotFoundMsg;

	@Value("${USER_EXISTS_MESSAGE}")
	private String userExistsMsg;

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
		// Check if the user with userName already exists or not.
		boolean isExistUserName = userRepository.findByUsername(user.getUsername()).isPresent();
		boolean isExistUserSSN = userRepository.findBySsn(user.getSsn()).isPresent();
		if (!isExistUserName && !isExistUserSSN) {
			return userRepository.save(user);
		} else {
			throw new UserExistsException(userExistsMsg);
		}
	}

	@Override
	public User getUserById(long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(userNotFoundMsg));
	}

	@Override
	public User updateUserById(User user, long id) {
		Optional<User> userOpt = userRepository.findById(id);
		if (userOpt.isPresent()) {
			user.setId(userOpt.get().getId());
			return userRepository.save(user);
		} else {
			throw new UserNotFoundException(userNotFoundMsg);
		}
	}

	@Override
	public void deleteUserById(long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(userNotFoundMsg));
		userRepository.delete(user);
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("No User Found"));
	}

}
