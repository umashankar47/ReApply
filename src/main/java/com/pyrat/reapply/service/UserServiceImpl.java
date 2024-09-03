package com.pyrat.reapply.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.pyrat.reapply.data.entity.User;
import com.pyrat.reapply.data.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passEncoder;
	
	
	
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passEncoder) {
		
		this.userRepository= userRepository;
		this.passEncoder =  passEncoder;	
		
	}
	
	@Override
	public List<User> getAlluser(){
			
		return this.userRepository.findAll();	
	}
	
	@Override
	public Optional<User> getUserById(Long id){
		
		return this.userRepository.findById(id);	
		

	}
	
	@Override
	public User createUser(User user) {
		
		user.setPasswordhash(
				passEncoder.encode(user.getPasswordhash())
						);
		Set<String> role = new HashSet<>();
		role.add("user");
		user.setRoles(role);
		
	
		return this.userRepository.save(user);
		
	}
	
	@Override
	public User updateUser(Long id, User user) {
	
		Optional<User> checkuser = this.userRepository.findById(id);
		
		if(checkuser.isPresent() && id == user.getUserId() )
			return this.userRepository.save(user);
		
		return null;

	}
	
	@Override
	public void deleteUserById(Long id) {
		

		
        if (userRepository.existsById(id))
        	userRepository.deleteById(id);       
      
        
	
	}


	
	
	
	

}
