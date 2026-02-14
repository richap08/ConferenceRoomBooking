# ConferenceRoomBooking
Conference Room Booking System built using Spring Boot, JPA, MySQL, and REST APIs with external employee import integration.

# Conference Room Booking System

This is a Spring Boot based REST API application for managing conference room bookings within an organization.

The system allows management of departments, employees, rooms, equipment, bookings, and approvals. It also supports importing employees from an external API and storing them in a MySQL database.

---

## Features

- Department management
- Employee management
- External employee import via REST API
- Room management
- Equipment management
- Booking creation with conflict validation
- Booking approval workflow
- DTO-based response structure
- ModelMapper for object mapping
- MySQL database integration

---

## Technologies Used

- Java 25
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- ModelMapper
- RestTemplate
- Lombok
- Postman (for API testing)

---

## Architecture

The project follows a layered architecture:

- Controller Layer – Handles HTTP requests and responses
- Service Layer – Contains business logic
- Repository Layer – Handles database interactions
- Entity Layer – Defines JPA entity mappings
- DTO Layer – Transfers data between client and server

---

## External API Integration

Employees can be imported from:

https://jsonplaceholder.typicode.com/users

The imported data is transformed into internal employee entities, assigned to a default department, and stored in the database. Duplicate records are prevented using employeeCode validation.

---

## Database

Database Name: conference_db

Main Entities:
- Department
- Employee
- Room
- Equipment
- Booking
- Approval

---

## API Base URL

http://localhost:8080

Example Endpoints:

GET     /departments  
GET     /employees  
POST    /employees  
POST    /employees/test-import  
POST    /bookings  
POST    /approvals  

---

## How to Run

1. Clone the repository.
2. Configure MySQL credentials in application.properties.
3. Ensure the database is created.
4. Run the Spring Boot application.
5. Test APIs using Postman.

---

## Author

Richa Pandey
