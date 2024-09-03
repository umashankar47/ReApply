package com.pyrat.reapply.web.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pyrat.reapply.data.entity.User;
import com.pyrat.reapply.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("api/user")
class UserApiController {
	
	private final UserService userService;
	
	public UserApiController(UserService userService) {
		
		this.userService= userService;
		
	}
	
	@GetMapping
	public List<User> getAlluser(HttpServletRequest request){
		
		if(request.isUserInRole("Admin"))
			return this.userService.getAlluser();
		
		return null;
	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id){
		
		Optional<User> user = this.userService.getUserById(id);
		
		
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
	
		return this.userService.createUser(user);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id")long id, @RequestBody User user) {
		
		
		Optional<User> checkuser = this.userService.getUserById(id);
		
		if(checkuser.isPresent() && id == user.getUserId() ) 		
//			User userUpdated = this.userRepository.save(user);	
			return ResponseEntity.ok(this.userService.updateUser(id,user));
			
		else
			return ResponseEntity.notFound().build();

	
	}
	
	
	@DeleteMapping("/{id}")
	 public ResponseEntity<Void> deleteUser(@PathVariable("id") long id) {
			
            userService.deleteUserById(id);
            
            return ResponseEntity.noContent().build();

	}
	
	
	
	
	
}
