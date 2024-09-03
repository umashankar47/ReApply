package com.pyrat.reapply.data.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
@Table(name="Users")
public class User {
	
	/*
	 * Column Name			Data Type		Constraints				Description
		user_id				BIGINT	PRIMARY KEY, AUTO_INCREMENT	Unique identifier for each user
		username			VARCHAR(100)	NOT NULL, UNIQUE	Username chosen by the user
		email				VARCHAR(150)	NOT NULL, UNIQUE	Email address of the user
		password_hash		VARCHAR(255)	NOT NULL	Hashed password
		first_name			VARCHAR(100)	NOT NULL	First name of the user
		last_name			VARCHAR(100)	NOT NULL	Last name of the user
		resume_path			VARCHAR(255)	NULL	Path to the stored resume file
		created_at			TIMESTAMP	NOT NULL, DEFAULT CURRENT_TIMESTAMP	Account creation timestamp
		updated_at			TIMESTAMP	NOT NULL, DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	Last update timestamp
*/
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long  userId;
	
	@Column(name="username",unique = true,length = 100)
	private String userName;
	
	@Column(name="password_hash",nullable = false,length = 255)
	private String passwordhash;
	
	
	//to-do --> change to custom data type/Object
	@Column(name="email", unique = true, nullable = false, length = 150 )
	private String  email;
	

	@Column(name="first_name", nullable =false,length = 100 )
	private String firstName;
	
	@Column(name="last_name", nullable =false,length = 100)
	private String lastName;
	
	@Column(name="resume_path", length = 255)
	private String resumePath;
	
	@Column(name="profile_pic_path", length = 255)
	private String profilePic;
	
	//@Column(nullable = false, updatable = false)
   // @CreationTimestamp
	
	
	
	@Column(name="created_at",nullable=false , updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name="updated_at",nullable =false)
	private LocalDateTime updatedAt;
	
	

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Application> applications;
	
	 @ElementCollection(fetch = FetchType.EAGER)
	 @CollectionTable(
			 			name = "user_roles", 
			 			joinColumns = @JoinColumn(name = "user_id"))
	 @Column(name = "role")
	 private Set<String> roles;

}
