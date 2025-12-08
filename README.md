# Asteral - NASA NEO Explorer

**Asteral** is a robust Spring Boot application that integrates with NASA's NeoWs API to visualize and track Near-Earth Objects (NEOs). It demonstrates a complete modernization of a legacy Java stack, featuring secure authentication, containerized deployment, and interactive API documentation.

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7-green) ![Docker](https://img.shields.io/badge/Docker-Compose-blue) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-13-blue) ![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-brightgreen)

## 📌 Project Overview
This project was built to demonstrate proficiency in:
*   **Third-party API Integration** (NASA NeoWs)
*   **Secure Authentication** using Spring Security
*   **Containerization** & DevOps workflows
*   **Modern Java Practices** (Java 17 migration)

## 🚀 Key Features
*   **🔐 Secure Authentication**: Full login/registration system with BCrypt password hashing and Auto-Login flow.
*   **🌌 Asteroid Explorer**: Browse Near-Earth Objects by date with real-time data from NASA.
*   **⭐ Favorites System**: Track potentially hazardous asteroids with a persistent database backend.
*   **📄 API Documentation**: Fully interactive Swagger UI for testing endpoints.
*   **📱 Responsive UI**: Server-side rendered interface using Thymeleaf and Bootstrap.

## 🛠 Tech Stack
| Category | Technology |
|----------|------------|
| **Language** | Java 17 LTS |
| **Framework** | Spring Boot 2.7 (Web, Security, Data JPA) |
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

3.  **Demo Credentials**:
    *   Username: `testuser2`
    *   Password: `password`

## 💻 Local Development
If running without Docker:
1.  Ensure **PostgreSQL** is running on port `5433` (configurable in `application.properties`).
2.  Run with Maven:
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

---
*Developed by Albon Idrizi*
