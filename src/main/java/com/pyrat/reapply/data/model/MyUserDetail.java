package com.pyrat.reapply.data.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.pyrat.reapply.data.entity.User;

public class MyUserDetail implements UserDetails {
	
	private final User user;
	
	public MyUserDetail(User user) {
		
		this.user = user;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
	
		Collection<GrantedAuthority>  authorities = this.user
															 .getRoles()
															 .stream()
															 .map(SimpleGrantedAuthority::new)
															 .collect(Collectors.toList());
		return authorities;
	}

	@Override
	public String getPassword() {
		
		
		return this.user.getPasswordhash();
	}

	@Override
	public String getUsername() {
	
		
		return this.user.getUserName();
	}

}
