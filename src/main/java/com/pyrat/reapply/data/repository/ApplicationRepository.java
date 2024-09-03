package com.pyrat.reapply.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pyrat.reapply.data.entity.Application;

public interface ApplicationRepository  extends JpaRepository<Application, Long>{

	
	List<Application> findByUserUserId(Long userID);
	
}
