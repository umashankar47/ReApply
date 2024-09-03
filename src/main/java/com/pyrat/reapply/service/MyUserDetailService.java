package com.pyrat.reapply.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pyrat.reapply.data.entity.User;
import com.pyrat.reapply.data.model.MyUserDetail;
import com.pyrat.reapply.data.repository.UserRepository;


@Service
public class MyUserDetailService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	
	
	public MyUserDetailService(UserRepository userRepository){
		
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> optUser =  this.userRepository.findByUserName(username);
		
		User user =  optUser.orElseThrow(() -> new UsernameNotFoundException(username + "not found "));
		
		return new MyUserDetail(user);
		
	}
	
	

}
