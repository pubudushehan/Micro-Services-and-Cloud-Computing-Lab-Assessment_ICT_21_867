# Student Course Portal - Microservices System

A complete microservices-based Student Course Portal built with Spring Boot, featuring 5 independent services with REST API communication.

## System Architecture

This system consists of 5 microservices:

1. **Student Service** (Port 8081) - Manages student information
2. **Course Service** (Port 8082) - Manages course catalog
3. **Enrollment Service** (Port 8083) - Handles student enrollments with inter-service communication
4. **Result Service** (Port 8084) - Manages student grades and results
5. **Notification Service** (Port 8085) - Handles enrollment notifications

Each service runs independently with its own H2 in-memory database.

## Tech Stack

- **Framework:** Spring Boot 3.1.5
- **Language:** Java 17
- **Database:** MySQL 8.0 (Dockerized)
- **Containerization:** Docker & Docker Compose
- **Build Tool:** Maven
- **Communication:** REST API with RestTemplate

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Docker Desktop
- Your favorite IDE (IntelliJ IDEA, Eclipse, VS Code)

## Project Structure

```
.
├── student-service/          # Student management service
├── course-service/           # Course management service
├── enrollment-service/       # Enrollment orchestration service
├── result-service/           # Result/Grade management service
├── notification-service/     # Notification service
└── README.md
```

## Setup Instructions

### Option 1: Run with Docker (Recommended)

1. **Build the project:**
   ```bash
   mvn clean install
   ```

2. **Start services:**
   ```bash
   docker compose up -d
   ```
   This will start all 5 microservices, 5 MySQL databases, and phpMyAdmin.

### Option 2: Run Each Service Separately (Manual)

1. **Open 5 separate terminal windows/tabs**

2. **Terminal 1 - Student Service:**
   ```bash
   cd student-service
   mvn clean install
   mvn spring-boot:run
   ```
   Service will start on http://localhost:8081

3. **Terminal 2 - Course Service:**
   ```bash
   cd course-service
   mvn clean install
   mvn spring-boot:run
   ```
   Service will start on http://localhost:8082

4. **Terminal 3 - Notification Service:**
   ```bash
   cd notification-service
   mvn clean install
   mvn spring-boot:run
   ```
   Service will start on http://localhost:8085

5. **Terminal 4 - Enrollment Service:**
   ```bash
   cd enrollment-service
   mvn clean install
   mvn spring-boot:run
   ```
   Service will start on http://localhost:8083

6. **Terminal 5 - Result Service:**
   ```bash
   cd result-service
   mvn clean install
   mvn spring-boot:run
   ```
   Service will start on http://localhost:8084

**Important:** Start services in this order to ensure dependencies are available:
1. Student Service
2. Course Service
3. Notification Service
4. Enrollment Service
5. Result Service

### Option 3: Build All Services First

```bash
# Build all services
cd student-service && mvn clean install && cd ..
cd course-service && mvn clean install && cd ..
cd enrollment-service && mvn clean install && cd ..
cd result-service && mvn clean install && cd ..
cd notification-service && mvn clean install && cd ..
```

Then run each service in separate terminals using `mvn spring-boot:run`

## API Documentation

### 1. Student Service (Port 8081)

#### Get All Students
```http
GET http://localhost:8081/students
```

#### Get Student by ID
```http
GET http://localhost:8081/students/{id}
```

#### Create Student
```http
POST http://localhost:8081/students
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "program": "Computer Science"
}
```

#### Update Student
```http
PUT http://localhost:8081/students/{id}
Content-Type: application/json

{
  "name": "John Updated",
  "email": "john.updated@example.com",
  "program": "Software Engineering"
}
```

#### Delete Student
```http
DELETE http://localhost:8081/students/{id}
```

---

### 2. Course Service (Port 8082)

#### Get All Courses
```http
GET http://localhost:8082/courses
```

#### Get Course by ID
```http
GET http://localhost:8082/courses/{id}
```

#### Create Course
```http
POST http://localhost:8082/courses
Content-Type: application/json

{
  "courseCode": "CS101",
  "courseName": "Introduction to Computer Science",
  "credits": 3,
  "instructor": "Dr. Smith"
}
```

#### Update Course
```http
PUT http://localhost:8082/courses/{id}
Content-Type: application/json

{
  "courseCode": "CS101",
  "courseName": "Advanced Computer Science",
  "credits": 4,
  "instructor": "Dr. Johnson"
}
```

#### Delete Course
```http
DELETE http://localhost:8082/courses/{id}
```

---

### 3. Enrollment Service (Port 8083)

#### Enroll Student in Course
```http
POST http://localhost:8083/enroll
Content-Type: application/json

{
  "studentId": 1,
  "courseId": 1
}
```

**Note:** This endpoint:
- Validates studentId by calling Student Service
- Validates courseId by calling Course Service
- Creates enrollment record
- Sends notification via Notification Service

#### Get Student Enrollments
```http
GET http://localhost:8083/enrollments/student/{studentId}
```

---

### 4. Result Service (Port 8084)

#### Create Result
```http
POST http://localhost:8084/results
Content-Type: application/json

{
  "studentId": 1,
  "courseId": 1,
  "grade": "A",
  "score": 95.5
}
```

#### Get Student Results
```http
GET http://localhost:8084/results/student/{studentId}
```

---

### 5. Notification Service (Port 8085)

#### Send Enrollment Notification
```http
POST http://localhost:8085/notify/enrollment
Content-Type: application/json

{
  "studentId": 1,
  "courseId": 1,
  "studentName": "John Doe",
  "courseName": "Computer Science"
}
```

**Note:** This endpoint prints notification to console.

---

## Testing the System - Complete Workflow

### Step 1: Create a Student
```bash
curl -X POST http://localhost:8081/students \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice Johnson","email":"alice@example.com","program":"Computer Science"}'
```

### Step 2: Create a Course
```bash
curl -X POST http://localhost:8082/courses \
  -H "Content-Type: application/json" \
  -d '{"courseCode":"CS101","courseName":"Data Structures","credits":3,"instructor":"Dr. Smith"}'
```

### Step 3: Enroll Student in Course
```bash
curl -X POST http://localhost:8083/enroll \
  -H "Content-Type: application/json" \
  -d '{"studentId":1,"courseId":1}'
```

**Expected behavior:**
- Validates student exists (calls Student Service)
- Validates course exists (calls Course Service)
- Creates enrollment
- Sends notification (calls Notification Service)
- Check Terminal 3 (Notification Service) for printed notification

### Step 4: Add Result for Student
```bash
curl -X POST http://localhost:8084/results \
  -H "Content-Type: application/json" \
  -d '{"studentId":1,"courseId":1,"grade":"A","score":92.5}'
```

### Step 5: View Student Enrollments
```bash
curl http://localhost:8083/enrollments/student/1
```

### Step 6: View Student Results
```bash
curl http://localhost:8084/results/student/1
```

---

## Database Management (phpMyAdmin)

Access the database management interface at: **http://localhost:8080**

**Login Details:**
- **Server:** (Use the container name)
  - `student-db`
  - `course-db`
  - `enrollment-db`
  - `result-db`
  - `notification-db`
- **Username:** `root`
- **Password:** `root`

---

## Inter-Service Communication

### Enrollment Service Dependencies
- **Student Service** (8081): Validates student existence
- **Course Service** (8082): Validates course existence
- **Notification Service** (8085): Sends enrollment notifications

### Result Service Dependencies
- **Student Service** (8081): Validates student existence
- **Course Service** (8082): Validates course existence

### Communication Method
All inter-service communication uses REST API calls via Spring's `RestTemplate`.

---

## Troubleshooting

### Service Won't Start
- **Port already in use:** Check if another application is using the port
- **Java version:** Ensure Java 17 or higher is installed
- **Maven:** Verify Maven is installed with `mvn --version`

### Inter-Service Communication Fails
- **Service order:** Ensure dependent services are started first
- **Network:** Check all services are accessible at localhost
- **Logs:** Check console output for error messages

### Build Failures
```bash
# Clean and rebuild
mvn clean install -U
```

---

## Features Implemented

✅ Complete CRUD operations for Students and Courses  
✅ Enrollment with validation via REST calls  
✅ Result management with grade tracking  
✅ Notification service with console output  
✅ Individual databases per service  
✅ Request/Response DTOs  
✅ Input validation  
✅ Error handling  
✅ RESTful API design  
✅ Inter-service communication  

---

## Future Enhancements

- ✅ Docker containerization
- Service discovery (Eureka)
- API Gateway
- Circuit breaker pattern
- Distributed tracing
- Event-driven architecture with message queues
- PostgreSQL/MySQL for production
- Authentication & Authorization
- Swagger/OpenAPI documentation

---

## Project Screenshots

### API Testing Results (Postman)

#### Student Service
| Create Student | Get All Students |
|:---:|:---:|
| ![Create Student](Postman%20Test%20Screenshots/API/POST_students%20.png) | ![Get All Students](Postman%20Test%20Screenshots/API/GET_students.png) |

| Get Student by ID | Update Student | Delete Student |
|:---:|:---:|:---:|
| ![Get Student by ID](Postman%20Test%20Screenshots/API/GET_students_by_id.png) | ![Update Student](Postman%20Test%20Screenshots/API/PUT_Student.png) | ![Delete Student](Postman%20Test%20Screenshots/API/DELETE_Student.png) |

#### Course Service
| Create Course | Get All Courses |
|:---:|:---:|
| ![Create Course](Postman%20Test%20Screenshots/API/POST_course.png) | ![Get All Courses](Postman%20Test%20Screenshots/API/GET_courses.png) |

| Get Course by ID | Update Course | Delete Course |
|:---:|:---:|:---:|
| ![Get Course by ID](Postman%20Test%20Screenshots/API/GET_course%20by%20id.png) | ![Update Course](Postman%20Test%20Screenshots/API/PUT_courses.png) | ![Delete Course](Postman%20Test%20Screenshots/API/DELETE_Courses.png) |

#### Enrollment & Results
| Enroll Student | Get Enrollments |
|:---:|:---:|
| ![Enroll Student](Postman%20Test%20Screenshots/API/POST_Enroll%20student%20in%20course.png) | ![Get Enrollments](Postman%20Test%20Screenshots/API/GET_Student%20enrollments.png) |

| Add Result | Get Results | Send Notification |
|:---:|:---:|:---:|
| ![Add Result](Postman%20Test%20Screenshots/API/POST_results.png) | ![Get Results](Postman%20Test%20Screenshots/API/GET_Student%20results.png) | ![Send Notification](Postman%20Test%20Screenshots/API/POST_Send%20Enrollment%20Notification.png) |

### Database Views

| Student DB | Course DB |
|:---:|:---:|
| ![Student DB](Postman%20Test%20Screenshots/DB%20s/Student_DB.png) | ![Course DB](Postman%20Test%20Screenshots/DB%20s/Course_DB.png) |

| Enrollment DB | Result DB |
|:---:|:---:|
| ![Enrollment DB](Postman%20Test%20Screenshots/DB%20s/Enrollment_DB.png) | ![Result DB](Postman%20Test%20Screenshots/DB%20s/Result_DB.png) |

---

## License

This is a educational project for learning microservices architecture.

---

## Author

Student Course Portal Microservices System

---

## Notes

- Services must be started in the correct order for proper functionality (handled automatically by Docker Compose)
- Default configuration uses localhost - modify application.properties for different deployments

