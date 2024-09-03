package com.pyrat.reapply.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.pyrat.reapply.service.MyUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	
	
	@Autowired
	private MyUserDetailService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
	    		
	    		
	    http
	            .authorizeHttpRequests(authorize -> authorize
	            		
	                .requestMatchers("/login", "/register").permitAll()
	                .requestMatchers(HttpMethod.POST,"/api/user").permitAll()
	                .requestMatchers(HttpMethod.DELETE,"/api/user/**").authenticated()
	                .requestMatchers(HttpMethod.PUT,"/api/user/**").authenticated()
	                .requestMatchers("/applications/**").hasRole("USER")
	                .anyRequest().authenticated()
	                
	            )
	            .formLogin(form -> form
	            		
	                .loginPage("/login")
	                .defaultSuccessUrl("/applications", true)
	                .failureUrl("/login?error")
	            )
	            .logout(logout -> logout
	            		
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/login?logout")
	                .clearAuthentication(true)
	            )
	            .csrf(csrf -> csrf.disable());
	    
	    http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	       
	    
	    return http.build();
		

	}
	
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
			
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailsService);
		
		return provider;
		
	}
	
	

}
