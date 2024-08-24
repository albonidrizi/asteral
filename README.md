Asteral
Asteral is a web application designed to manage and explore asteroid data. It features user authentication, personalized favorites, and profile management. This project utilizes Spring Boot for the backend and Thymeleaf for rendering views.

Features
User Authentication: Secure login and logout.
Asteroid Management: View and manage asteroid details.
Favorites: Add and remove asteroids from user favorites.
Profile Management: View and update user profile information.
Technologies Used
Backend: Spring Boot, Spring Security
Frontend: Thymeleaf
Database: PostgreSQL
Security: BCryptPasswordEncoder for password encryption
Service Layer: Custom services for managing asteroid data and user profiles
Getting Started
Prerequisites
Java 11 or higher
Maven
PostgreSQL
Installation
Clone the Repository:

bash
Copy code
git clone https://github.com/albonidrizi/asteral.git
Navigate to the Project Directory:

bash
Copy code
cd asteral
Set Up PostgreSQL:

Create a database and user for the application. Update the application.properties file with your database credentials.

Build and Run the Application:

bash
Copy code
mvn clean install
mvn spring-boot:run
Access the Application:

Open your web browser and go to http://localhost:8080.

Configuration
Update the application.properties file to configure your database connection and other settings:

properties
Copy code
spring.datasource.url=jdbc:postgresql://localhost:5432/yourdatabase
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
Usage
Login: Access the login page at /login.
Asteroids: View asteroid details at /asteroid/{referenceId}.
Favorites: Add or remove asteroids from favorites at /user/favorite/add/{referenceId} and /user/favorite/remove/{referenceId}.
Profile: View your profile at /user/profile.



License
This project is licensed under the MIT License - see the LICENSE file for details.

Acknowledgements
Spring Boot Documentation
Thymeleaf Documentation
PostgreSQL Documentation
Feel free to adjust this template to better fit the specifics of your project or any additional details you want to include.
