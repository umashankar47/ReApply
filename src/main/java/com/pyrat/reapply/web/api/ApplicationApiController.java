package com.pyrat.reapply.web.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pyrat.reapply.data.entity.Application;
import com.pyrat.reapply.data.entity.User;
import com.pyrat.reapply.service.ApplicationService;
import com.pyrat.reapply.service.UserService;



@RestController
@RequestMapping("api/applications")

public class ApplicationApiController {
	

	private final ApplicationService applicationService;
	private final UserService userService;

	
	public ApplicationApiController(ApplicationService applicationService,
			UserService userService) {
		
		this.applicationService = applicationService;
		this.userService = userService;
	
		
	}
	
	
	@GetMapping()
	public List<Application> getMethodName() {

		return this.applicationService.getAllApplications();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Application> getApplicationById(@PathVariable("id")long id) {
		
		Optional<Application> application =  this.applicationService.getApplicationById(id);
	
		return application.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("user/{userId}")
	public List<Application> getApplicationByUserId(@PathVariable("userId")long userId) {
		
	
		return this.applicationService.getApplicationByUserId(userId);
		
	}
	
	@PostMapping("user/{userId}")
	public ResponseEntity<Application> createApplication(@PathVariable("userId") Long userId,@RequestBody Application application){
		
		Optional<User> userOpt = this.userService.getUserById(userId);
				
		return userOpt.map( User -> {
			
			application.setUser(userOpt.get());
			Application savedApplication =  applicationService.createApplication(userId, application);
			return ResponseEntity.ok(savedApplication);
			
		}).orElseGet(
				
				() -> ResponseEntity.notFound().build()
				
				);
		
	}	
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Application>  updateApplication(@PathVariable("id") Long id,@RequestBody Application application ){
		
		
		if(id != application.getApplicationId())
			return ResponseEntity.notFound().build();
			
		else
			return ResponseEntity.ok(this.applicationService.updateApplication(id,application));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteApplication(@PathVariable("id")Long id){

			this.applicationService.deleteApplication(id);
			
			return ResponseEntity.noContent().build();

	}
	
	
}
