# LifeLedger – Backend

LifeLedger Backend is a REST API built with Spring Boot that handles authentication, application logic, and database operations for the LifeLedger application.

## Live Deployment
- **Backend API:** https://lifeledger-backend-claz.onrender.com
- **Frontend:** https://life-ledger-frontend.vercel.app

## Tech Stack
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- MySQL
- Maven

## Features
- User registration and login
- JWT-based authentication
- REST API endpoints
- MySQL database integration
- CORS configuration for frontend communication
- Protected API endpoints

## Installation and Setup

**1. Clone the repository**
```bash
git clone https://github.com/ashrith-17/LifeLedger-Backend.git
cd LifeLedger-Backend
```

**2. Configure environment variables**

Configure your database connection and JWT secret using environment variables. Never commit passwords or secrets to GitHub.

**3. Run the application**

Windows:
```bash
mvnw.cmd spring-boot:run
```

macOS/Linux:
```bash
./mvnw spring-boot:run
```

The application runs on `http://localhost:8080` by default.

**4. Build the project**

Windows:
```bash
mvnw.cmd clean package
```

macOS/Linux:
```bash
./mvnw clean package
```

## Deployment
- **Backend hosting:** Render
- **Database hosting:** Aiven MySQL

## Related Repository
- **Frontend:** https://github.com/ashrith-17/LifeLedger-Frontend

## Author
Nayini Ashrith
