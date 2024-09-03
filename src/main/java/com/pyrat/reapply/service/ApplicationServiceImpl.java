package com.pyrat.reapply.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.pyrat.reapply.data.entity.Application;
import com.pyrat.reapply.data.entity.User;
import com.pyrat.reapply.data.repository.ApplicationRepository;
import com.pyrat.reapply.data.repository.UserRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	
	private final ApplicationRepository applicationRepository;
	private final UserRepository userRepository;
	
	public ApplicationServiceImpl(ApplicationRepository applicationRepository,
			UserRepository userRepository) {
		
		this.applicationRepository = applicationRepository;
		this.userRepository = userRepository;
	
		
	}
	
	@Override
	public List<Application> getAllApplications() {

		return this.applicationRepository.findAll();
	}
	
	@Override
	public Optional<Application> getApplicationById(Long id) {
		
		return this.applicationRepository.findById(id);
	
	
	}
	
	public List<Application> getApplicationByUserId(Long userId) {
		
	
		return this.applicationRepository.findByUserUserId(userId);
		
	}
	
	@Override
	public Application createApplication(Long userId, Application application){
		
		Optional<User> userOpt = this.userRepository.findById(userId);
				
		return userOpt.map( User -> {
			
			application.setUser(userOpt.get());
			Application savedApplication =  applicationRepository.save(application);
			return savedApplication;
			
		}).orElseThrow(
				
				() -> new RuntimeException("User Not Found") 
				
				);
		
	}	
	
	@Override
	public Application updateApplication(Long id, Application application ){
		
		Optional<Application> optApplication = this.applicationRepository.findById(id);
		
		if(optApplication.isPresent() && id == application.getApplicationId())
			return this.applicationRepository.save(application);
		
		return null;
		
		
	}
	
	@Override
	public void deleteApplication(Long id){
		
		if(this.applicationRepository.existsById(id))
			this.applicationRepository.deleteById(id);

	}

}
