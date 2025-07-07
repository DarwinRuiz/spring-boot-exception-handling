# Spring Boot Exception Handling

A comprehensive demonstration of global exception handling and error management in Spring Boot applications using `@RestControllerAdvice` and custom exception classes.

## 🚀 Overview

This project showcases best practices for handling exceptions in Spring Boot REST APIs, including:

- **Global Exception Handling** with `@RestControllerAdvice`
- **Custom Exception Classes** for business-specific errors
- **Structured Error Responses** with consistent JSON format
- **Multiple Exception Types** handling (built-in and custom)
- **RESTful API** with proper HTTP status codes

## 📋 Features

### Exception Handling
- ✅ **ArithmeticException** - Division by zero handling
- ✅ **NumberFormatException** - Invalid number parsing
- ✅ **NullPointerException** - Null reference errors
- ✅ **UserNotFoundException** - Custom business exception
- ✅ **NoHandlerFoundException** - 404 not found errors
- ✅ **HttpMessageNotWritableException** - JSON serialization errors

### API Endpoints
- `GET /api/app/` - Demo endpoint with intentional exceptions
- `GET /api/app/show/{id}` - User retrieval with validation

### Error Response Format
All exceptions return a consistent JSON error response:
```json
{
  "message": "Detailed error message",
  "error": "Human-readable error description",
  "status": 404,
  "path": "/api/app/show/999",
  "date": "2025-07-06T10:30:00.000Z"
}
```

## 🛠️ Technology Stack

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Web** - REST API development
- **Spring Boot DevTools** - Development utilities
- **Spring Boot Actuator** - Production monitoring
- **Maven** - Dependency management

## 📁 Project Structure

```
src/main/java/com/darwinruiz/springboot/errors/springbooterrors/
├── SpringbootErrorsApplication.java       # Main application class
├── AppConfig.java                         # Configuration and sample data
├── configurations/
│   ├── HandlerExceptionController.java    # Global exception handler
│   └── exceptions/
│       └── UserNotFoundException.java     # Custom exception
├── controllers/
│   └── AppController.java                 # REST API endpoints
├── dtos/
│   └── configurations/
│       └── Error.java                     # Error response DTO
├── models/
│   ├── User.java                          # User entity
│   └── Role.java                          # Role entity
└── services/
    ├── UserService.java                   # User service implementation
    └── interfaces/
        └── IUserService.java              # User service interface
```

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- Maven 3.6+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/DarwinRuiz/spring-boot-exception-handling.git
   cd spring-boot-exception-handling
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

   Or run the JAR file:
   ```bash
   java -jar target/springbooterrors-0.0.1-SNAPSHOT.jar
   ```

4. **Access the application**
   - Application will start at: `http://localhost:8080`
   - Actuator endpoints: `http://localhost:8080/actuator`

## 📚 API Documentation

### Test Exception Handling

#### 1. Number Format Exception
```bash
curl -X GET http://localhost:8080/api/app/
```
**Response:**
```json
{
  "message": "For input string: \"abc\"",
  "error": "Invalid number format.",
  "status": 400,
  "path": "/api/app/",
  "date": "2025-07-06T10:30:00.000Z"
}
```

#### 2. User Not Found Exception
```bash
curl -X GET http://localhost:8080/api/app/show/999
```
**Response:**
```json
{
  "message": "User not found with ID: 999",
  "error": "User not found.",
  "status": 404,
  "path": "/api/app/show/999",
  "date": "2025-07-06T10:30:00.000Z"
}
```

#### 3. Invalid User ID
```bash
curl -X GET http://localhost:8080/api/app/show/0
```
**Response:**
```json
{
  "message": "Invalid user ID",
  "error": "User not found.",
  "status": 404,
  "path": "/api/app/show/0",
  "date": "2025-07-06T10:30:00.000Z"
}
```

#### 4. Valid User Request
```bash
curl -X GET http://localhost:8080/api/app/show/1
```
**Response:**
```json
{
  "id": 1,
  "firstName": "Darwin",
  "lastName": "Ruiz",
  "role": null
}
```

### Available Users
The application comes with pre-configured sample users (IDs 1-5):
- Darwin Ruiz (ID: 1)
- John Doe (ID: 2)
- Jane Smith (ID: 3)
- Alice Johnson (ID: 4)
- Bob Brown (ID: 5)

## 🎯 Key Implementation Details

### Global Exception Handler
The `HandlerExceptionController` uses `@RestControllerAdvice` to handle exceptions globally:

```java
@RestControllerAdvice
public class HandlerExceptionController {
    
    @ExceptionHandler({ArithmeticException.class})
    public ResponseEntity<Error> divisionByZero(Exception e, HttpServletRequest request) {
        // Handle division by zero
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Error> userNotFound(UserNotFoundException e, HttpServletRequest request) {
        // Handle custom user not found exception
    }
    
    // ... other exception handlers
}
```

### Custom Exception
```java
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```

### Error Response DTO
```java
public class Error {
    private String message;
    private String error;
    private String path;
    private Integer status;
    private Date date;
    // ... getters and setters
}
```

## 🔧 Configuration

### Application Properties
```properties
# Add custom configurations in src/main/resources/application.properties
server.port=8080
spring.application.name=springboot-errors
```

### Sample Data Configuration
User data is configured in `AppConfig.java` as a `@Bean`:

```java
@Bean
List<User> userList() {
    return List.of(
        new User(1L, "Darwin", "Ruiz", null),
        new User(2L, "John", "Doe", null),
        // ... more users
    );
}
```

## 🧪 Testing


### Manual Testing with cURL
Use the provided cURL commands above to test different exception scenarios.

## 📈 Monitoring

The application includes Spring Boot Actuator for monitoring:
- Health check: `http://localhost:8080/actuator/health`
- Application info: `http://localhost:8080/actuator/info`
- All endpoints: `http://localhost:8080/actuator`


## 👨‍💻 Author

**Darwin Ruiz**
- GitHub: [@DarwinRuiz](https://github.com/DarwinRuiz)
