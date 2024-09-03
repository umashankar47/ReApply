# Job Application Tracker
# Overview
The Job Application Tracker is a full-stack web application designed to help users manage and track their job applications. The application allows users to register, log in, and view their job applications in a secure and user-friendly environment.

## Features
- User Registration & Login: Users can register and log in to the application. Passwords are securely hashed using Spring Security.
- Role-Based Access Control: The application supports different user roles (e.g., USER, ADMIN) with role-based access control implemented using Spring Security.
- CRUD Operations: Users can create, view, update, and delete job applications.
- Responsive UI: The front-end is built with Thymeleaf and Bootstrap, ensuring a responsive and accessible user interface.
- Security: The application includes CSRF protection and secure password storage with hashing.

## Technology Stack
- Backend: Spring Boot, Spring Security, Spring Data JPA
- Frontend: Thymeleaf, Bootstrap
- Database: PostgreSQL
- Build Tool: Maven
- Java Version: 17

## Getting Started
**Prerequisites**
- Java 17+
- Maven
- PostgreSQL

## Installation
- Clone the repository
- Update the application.properties file with your database credentials:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/job_application_tracker
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
**Build the project:**
```sh
mvn clean install
```

**Run the application:**
```sh
mvn spring-boot:run
```
**Access the application:** 
- Open your web browser and go to http://localhost:8080.

## API Endpoints
- Create User (POST): /api/user
- Get All Users (GET): /api/user
- Get User by ID (GET): /api/user/{id}
- Update User (PUT): /api/user/{id}
- Delete User (DELETE): /api/user/{id}


## Contributing
Contributions are welcome! Please fork the repository and create a pull request to contribute.
