# Tech It Easy API – NOVI Backend Assignment

## Overview

This project is a small RESTFUL web service built as part of the Backend module at [Novi University](https://www.novi.nl).
It manages **Television** and **Remote Controller** entities and includes **authentication & authorization** using Spring Security with JWT.  
The service demonstrates best practices for separation of concerns, DTO usage, validation, and centralized exception handling.

---

## Table of Contents

- [Tech Stack](#tech-stack)
- [Key Features](#key-features)
- [API Endpoints](#api-endpoints)
    - [Authentication](#authentication)
    - [Television](#television)
    - [Remote Controller](#remote-controller)
- [How to Run](#how-to-run)
- [Credits](#credits)
- [License](#license)

---

## Tech Stack

- **Java 17+**
- **Spring Boot** (REST API, Spring Web)
- **Spring Security** (JWT Authentication)
- **Hibernate / JPA** (Entity management)
- **Maven**
- **PasswordEncoder (BCrypt)** for secure password storage

---

## Key Features

- CRUD operations on `Television` entities
- CRUD operations on `RemoteController` entities (linked to televisions)
- Authentication & Authorization:
    - **JWT (JSON Web Token)** based authentication
    - Endpoints protected with **Spring Security Config**
    - Passwords securely hashed with **PasswordEncoder**
- `User` entity and `AuthenticationController` for login/register
- Centralized exception handling (`ExceptionController`)
- Validation support with **DTOs** and `BindingResult`
- Sales statistics endpoint (`/televisions/sales`)

---

## API Endpoints

### Authentication
| Method | Endpoint         | Description      | 
|--------|------------------|------------------|
| GET    | `/authenticated` | Get user details |
| POST   | `/authenticate`  | Login & get JWT  | 

---

### Television
| Method | Endpoint               | Description                           | 
|--------|------------------------|---------------------------------------|
| GET    | `/televisions`         | Get all televisions (filter by brand) | 
| GET    | `/televisions/{id}`    | Get television by ID                  | 
| POST   | `/televisions`         | Add a new television                  | 
| PUT    | `/televisions/{id}`    | Update an existing television         | 
| PATCH  | `/televisions/{id}`    | Partially update a television         | 
| DELETE | `/televisions/{id}`    | Delete a television                   | 
| GET    | `/televisions/sales`   | Get sales information                 | 

---

### Remote Controller
*(All endpoints nested under `/televisions/{televisionId}/remote-controllers`)*

| Method | Endpoint                        | Description                        | 
|--------|---------------------------------|------------------------------------|
| GET    | `/{remoteControllerId}`         | Get remote controller by ID        | 
| POST   | ``                              | Add new remote controller          | 
| PUT    | `/{remoteControllerId}`         | Update remote controller           | 
| PATCH  | `/{remoteControllerId}`         | Partially update remote controller | 
| DELETE | `/{remoteControllerId}`         | Delete remote controller           | 

---

## How to Run

1. Clone the repository or open the project in your IDE (IntelliJ IDEA)
2. Navigate to the `TechItEasyApplication` main class.
3. Click the green play️ button to run the application or use the terminal:
    ```bash
    mvn spring-boot:run
   ```
4. The server will start at: `http://localhost:8080`


## Credits
> "This assignment was developed as part of the Backend Java module in the NOVI Software Development program. All instructions, logic, and structure are part of the official coursework."

## License
> "This repository is intended for educational purposes only. You are welcome to use the code for learning, but not for commercial use."