# 📘 Fundoo Notes Backend — Part 1

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-green)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Status](https://img.shields.io/badge/Status-Part1%20Complete-success)

---

## 🚀 Overview

Fundoo Notes Backend (Part 1) is a foundational backend system built using Spring Boot.  
This module focuses on implementing a clean and scalable architecture along with core backend functionalities such as user authentication and note management.

The aim is not just to build APIs, but to understand how real backend systems are structured using proper separation of concerns and layered design.

---

## 🎯 Objectives

- Build a scalable backend using layered architecture
- Understand request flow across layers
- Implement user registration and login
- Develop note management APIs
- Apply DTO-based design principles
- Implement centralized exception handling
- Enable logging and debugging

---

## 🏗️ Architecture

The application follows a standard layered architecture:

Client  
↓  
Controller (Handles HTTP Requests)  
↓  
Service (Business Logic Layer)  
↓  
Repository (Database Interaction)  
↓  
Database (MySQL)

---

## 📂 Project Structure

com.fundoonotes  
├── config  
├── controller  
├── dto  
│   ├── request  
│   └── response  
├── entity  
├── exception  
├── repository  
├── security  
├── service  
│   └── impl  
├── util

---

## ⚙️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman

---

## 🔧 Configuration

application.properties:

server.port=8080  
spring.datasource.url=jdbc:mysql://localhost:3306/fundoo_notes  
spring.datasource.username=root  
spring.datasource.password=root  
spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true  
logging.level.root=INFO  
logging.level.com.fundoonotes=DEBUG

---

## 🔐 User Module

### Register User

POST /api/users/register

Request Body:
{
"firstName": "Barnam",
"email": "barnam@gmail.com",
"password": "123456"
}

Response:
{
"id": 1,
"firstName": "Barnam",
"email": "barnam@gmail.com"
}

---

### Login User

POST /api/users/login

Request Body:
{
"email": "barnam@gmail.com",
"password": "123456"
}

Response:
{
"token": "uuid:1",
"message": "Login successful"
}

---

## 📝 Notes Module

### Create Note

POST /api/notes

Headers:
Authorization: token

Body:
{
"title": "My Note",
"description": "Sample note"
}

---

### Get All Notes

GET /api/notes

Headers:
Authorization: token

---

### Note Actions

PATCH /api/notes/{id}/pin  
PATCH /api/notes/{id}/archive  
PATCH /api/notes/{id}/trash

---

## 🔑 Token Mechanism

- Generated during login
- Format: UUID:userId
- Example: abc-123-xyz:1
- Passed via request header: Authorization

This is a simplified token system used for learning purposes.

---

## ⚠️ Exception Handling

The application uses centralized exception handling via @RestControllerAdvice to maintain consistent API responses.

Example Response:
{
"message": "User not found",
"statusCode": 404,
"timestamp": "2026-04-14T10:00:00"
}

---

## 📊 Logging

Logging is implemented using SLF4J.

Levels:
- INFO → Normal application flow
- DEBUG → Detailed internal processing
- ERROR → Failures and exceptions

Example:
log.info("Creating note");  
log.debug("Title: {}", dto.getTitle());

---

## 🧪 Testing

All APIs were tested using Postman.

Test scenarios include:
- User registration
- Duplicate email handling
- Login success and failure
- Note creation and retrieval
- Pin, archive, and trash operations
- Validation errors
- Exception responses

---

## ✅ Features Implemented

- Layered backend architecture
- User registration and login
- Token-based request handling (basic)
- Note creation and retrieval
- Note state transitions (pin, archive, trash)
- DTO-based API design
- Global exception handling
- Logging and debugging

---

## 🚧 Limitations

- Basic token implementation (not JWT)
- No full Spring Security integration
- No role-based authorization
- No caching or asynchronous processing

---

## 🚀 Future Enhancements (Part 2)

- JWT-based authentication
- Spring Security integration
- Redis caching
- Messaging systems (RabbitMQ / Kafka)
- Advanced backend features

---

## 👨‍💻 Author

Barnam Das

