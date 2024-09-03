package com.pyrat.reapply.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pyrat.reapply.data.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	Optional<User> findByUserName(String userName);

}
