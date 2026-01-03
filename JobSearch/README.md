# Job Search Application

Welcome to the Job Search Application! This project is a Spring Boot-based web application that allows users to search and filter job listings efficiently. The application is built with security, performance, and usability in mind, providing a seamless job search experience.

## Features
- **RESTful API**: Implemented using Spring MVC, Spring JDBC, and Hibernate for CRUD operations.
- **Search & Filtering**: Advanced filtering and search functionality to enhance job discovery.
- **Security**: Configured with Spring Security for user authentication and CSRF protection, and implemented JSON Web Tokens (JWT) for secure authentication.
- **Logging & Monitoring**: Aspect-Oriented Programming (AOP) is used to log and monitor application performance.
- **Database Management**: Utilizes PostgreSQL for database interactions, managed via the Data Access Object (DAO) pattern and Spring Data JPA.
- **Session Management**: Maintains user data consistency across sessions.
- **Collaboration**: Managed using GitHub for version control and code management.
- **Testing**: APIs tested and validated using Postman.

## Requirements
Before running the application, ensure you have the following installed:

1. **Java Development Kit (JDK)**: Java 8 or higher.
2. **PostgreSQL**: The application uses PostgreSQL for database management.
3. **Postman**: For testing and validating the API endpoints.
4. **Maven**: For project build and dependency management.
5. **Internet Connection**: Required for dependency resolution and certain application functionalities.

## Installation

### Step 1: Clone the Repository
```bash
git clone https://github.com/NatalioF22/JobSearch.git
cd JobSearch
```

### Step 2: Set Up PostgreSQL
1. **Install PostgreSQL**: If you haven't already, install PostgreSQL on your system.
2. **Create a Database**: Create a new database for the application.
3. **Configure Database Connectivity**:
   - Open the `application.properties` file located in `src/main/resources/`.
   - Replace the database connectivity details with your PostgreSQL configuration:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```
4. **Run the SQL Scripts**: If any initial schema or data scripts are provided, run them to set up your database.

### Step 3: Build the Project
```bash
mvn clean install
```

### Step 4: Run the Application
```bash
mvn spring-boot:run
```

### Step 5: Test API Endpoints with Postman
1. **Install Postman**: Download and install [Postman](https://www.postman.com/downloads/).
2. **Import API Collection**: Import the Postman collection provided in the repository (if available) to test the API endpoints.
3. **Send Requests**: Use Postman to send requests and interact with the application’s RESTful API.

## Usage
Once the application is running, you can access it locally at:
```
http://localhost:8080/
```

## Contribution
Contributions are welcome! Feel free to fork the repository and submit pull requests. Please ensure your code follows the project’s guidelines and is well-tested.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contact
For any inquiries or issues, please reach out through the project's GitHub repository: [JobSearch on GitHub](https://github.com/NatalioF22/JobSearch).

---

Thank you for checking out the Job Search Application! We hope it helps you in your job search journey.
