package com.healthremainder.service;

import java.util.List;
import java.util.Optional;

import com.healthremainder.entity.User;

public interface UserService {


	User registerUser(User user);

	User loginUser(String email, String password);

	
	User saveUser(User user);

	Optional<User> findByEmail(String email);

	User getUserById(Long id);

	User getUserByEmail(String email);

	List<User> getAllUsers();

	List<User> findByRole(User.Role role);

	boolean existsByEmail(String email);

	User updateUser(Long id, User user);

	void deleteUser(Long id);

	
}
