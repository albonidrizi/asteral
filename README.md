# Asteral - NASA NEO Explorer

**Asteral** is a robust Spring Boot application that integrates with NASA's NeoWs API to visualize and track Near-Earth Objects (NEOs). It demonstrates a complete modernization of a legacy Java stack, featuring secure authentication, containerized deployment, and interactive API documentation.

![Java](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5-green) ![Docker](https://img.shields.io/badge/Docker-Compose-blue) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13-blue) ![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-brightgreen)

## 📌 Project Overview
This project was built to demonstrate proficiency in:
*   **Third-party API Integration** (NASA NeoWs)
*   **Secure Authentication** using Spring Security
*   **Containerization** & DevOps workflows
*   **Modern Java Practices** (Java 21 with Spring Boot 3.5)
*   **Upgraded Tech Stack** from Spring Boot 2.7 → 3.5 (Jakarta EE migration)

## 🚀 Key Features
*   **🔐 Secure Authentication**: Full login/registration system with BCrypt password hashing and Auto-Login flow.
*   **🌌 Asteroid Explorer**: Browse Near-Earth Objects by date with real-time data from NASA.
*   **⭐ Favorites System**: Track potentially hazardous asteroids with a persistent database backend.
*   **📄 API Documentation**: Fully interactive Swagger UI for testing endpoints.
*   **📱 Responsive UI**: Server-side rendered interface using Thymeleaf and Bootstrap.

## 🛠 Tech Stack
| Category | Technology |
|----------|------------|
| **Language** | Java 21 LTS |
| **Framework** | Spring Boot 3.5 (Web, Security, Data JPA) |
| **Database** | PostgreSQL 13 |
| **DevOps** | Docker, Docker Compose (Multi-stage builds) |
| **Tools** | Maven, Lombok, Swagger UI |
| **Frontend** | Thymeleaf, HTML5/CSS3 |

## 🐳 Quick Start (Docker)
The application is fully containerized. Prerequisites: Docker & Docker Compose.

1.  **Clone and Start**:
    ```bash
    git clone https://github.com/albonidrizi/asteral.git
    cd asteral
    docker-compose up --build -d
    ```

2.  **Access the Application**:
    *   **Web UI**: [http://localhost:8080](http://localhost:8080)
    *   **Swagger Docs**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
    *   **Database**: PostgreSQL available on `localhost:5433`

3.  **Demo Credentials**:
    *   Username: `testuser2`
    *   Password: `password`

**Optional**: Set NASA API Key for full functionality:
```bash
export NASA_API_KEY=your_api_key_here
docker-compose up --build -d
```

## 💻 Local Development
If running without Docker:
1.  Ensure **PostgreSQL** is running on port `5433` with database `nasa_challenge`.
2.  Set environment variables:
    ```bash
    export DB_HOST=localhost
    export DB_PORT=5433
    export DB_NAME=nasa_challenge
    export DB_USERNAME=postgres
    export DB_PASSWORD=password
    ```
3.  Run with Maven:
    ```bash
    mvn spring-boot:run
    ```

## 📂 Project Structure
```
src/
├── main/
│   ├── java/com/nasa/asteral/
│   │   ├── configuration/  # Security & API configs
│   │   ├── controller/     # MVC & Rest Controllers
│   │   ├── model/          # JPA Entities & DTOs
│   │   ├── repository/     # Spring Data Repositories
│   │   └── service/        # Business Logic & NASA Integration
│   └── resources/
│       ├── templates/      # Thymeleaf views
│       └── application.properties
└── test/                   # JUnit 5 & Mockito tests
```

## 📹 Video Demo
[![Project Demo](https://img.youtube.com/vi/JfnWMoPzA8g/0.jpg)](https://www.youtube.com/watch?v=JfnWMoPzA8g)
[Watch the video demo](https://www.youtube.com/watch?v=JfnWMoPzA8g)

## 🧪 Testing & Quality Metrics

### Test Coverage
![Coverage](https://img.shields.io/badge/Coverage-75%25-green) ![Tests](https://img.shields.io/badge/Tests-30%20passing-brightgreen)

The project maintains **75% code coverage** with comprehensive unit tests focusing on business logic:

| Layer | Coverage | Status |
|-------|----------|--------|
| **Service Layer** | 94% | ⭐ Excellent |
| **Configuration** | 100% | ✅ Complete |
| **Exception Handling** | 100% | ✅ Complete |
| **Model Layer** | 80% | ✅ Good |
| **Controller Layer** | 31% | ⚠️ In Progress |
| **Overall** | **75%** | ✅ **Good** |

### Running Tests
```bash
# Run all tests with coverage report
mvn clean test

# Run only unit tests
mvn test -Dtest='*ServiceTest'

# Generate coverage report (opens in browser)
mvn test jacoco:report
start target/site/jacoco/index.html
```

### Test Suite Breakdown
- **30 Unit Tests** using JUnit 5 & Mockito
  - `FavoriteAsteroidServiceTest` - 8 tests (CRUD operations)
  - `IntegrationServiceTest` - 5 tests (API client)
  - `AsteroidFeedServiceTest` - 5 tests (NASA API integration)
  - `MenuServiceTest` - 4 tests (authorization menus)
  - `MyUserDetailsServiceTest` - 4 tests (Spring Security)
  - `MyProfileServiceTest` - 3 tests (user profiles)
  - `AsteroidDetailServiceTest` - 1 test (details fetching)

### Quality Tools
- **JaCoCo** - Code coverage analysis (minimum 80% enforced)
- **JUnit 5** - Modern testing framework
- **Mockito** - Mocking dependencies
- **Maven Surefire** - Test execution

---
*Developed by Albon Idrizi*
