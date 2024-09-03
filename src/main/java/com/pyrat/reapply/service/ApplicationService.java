package com.pyrat.reapply.service;

import java.util.List;
import java.util.Optional;

import com.pyrat.reapply.data.entity.Application;

public interface ApplicationService {
	
	
	public List<Application> getAllApplications();
	
	public Optional<Application> getApplicationById(Long id);
	
	public List<Application> getApplicationByUserId(Long userId);

	public Application createApplication(Long userId, Application application);
	
	public Application  updateApplication(Long id, Application application );
	
	public void deleteApplication(Long id);
	
}
