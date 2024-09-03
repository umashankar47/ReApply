package com.pyrat.reapply.service;

import java.util.List;
import java.util.Optional;

import com.pyrat.reapply.data.entity.User;

public interface UserService {
	
	public List<User> getAlluser();
	
	public Optional<User> getUserById(Long id);
	
	public User createUser(User user);
	
	public User updateUser(Long id, User user);
	
	public void deleteUserById(Long id);
	
	
	
	

}
