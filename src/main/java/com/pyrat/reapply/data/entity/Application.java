package com.pyrat.reapply.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name="Applications")
public class Application {
	
	
	/*
	 	2. Application Tracker Table
		This table will store the information related to job applications.
		
		Column Name	Data 			Type					Constraints							Description
		application_id				BIGINT				PRIMARY KEY, AUTO_INCREMENT			Unique identifier for each application
		user_id	BIGINT				FOREIGN KEY REFERENCES users(user_id), NOT NULL			User who made the application
		job_title					VARCHAR(150)			NOT NULL						Title of the job applied for
		company_name				VARCHAR(150)			NOT NULL						Name of the company
		application_status			VARCHAR(50)				NOT NULL						Status of the application (e.g., Applied, Interview, Offer, Rejected)
		application_date			DATE					NOT NULL						Date the application was submitted
		job_description				TEXT					NULL							Description or notes about the job
		location					VARCHAR(150)			NULL							Location of the job
		resume_used					VARCHAR(255)			NULL							Path to the resume file used for this application
		notes						TEXT					NULL							Additional notes
		created_at					TIMESTAMP				NOT NULL, DEFAULT CURRENT_TIMESTAMP	Timestamp of the record creation
		updated_at					TIMESTAMP				NOT NULL, DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP	Timestamp of the last update
		3. Resume Storage
		Since you mentioned storing user resumes, here’s how it can be handl
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="application_id")
	private long applicationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	@Column(name="job_title", nullable = false, length = 150)
	private String jobTitle;
	
	@Column(name="company_name", nullable = false, length = 150)
	private String companyName;
	
	@Column(name="application_status", nullable = false, length = 50)
	private String applicationStatus;  //change to Enum later
	
	@Column(name="applied_date", nullable = false)
	private LocalDate appliedDate;
	
	@Column(name ="job_description", columnDefinition = "TEXT")
	private String jobDescription;
	
	@Column(name="location", length = 150)
	private String location;
	
	@Column(name="resume_used", length = 255)
	private String resumeUsed;
	
	@Column(name="notes", columnDefinition = "TEXT")
	private String notes;
	
	
	
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
	
	
	
	
	
	
	
	
	

}
