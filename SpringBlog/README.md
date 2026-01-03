# SpringBlog
## Spring Boot Rest Blog

## Description

This project is a spring boot backend application using Spring Boot. It implements RESTful APIs and CRUD operations using Spring MVC, Spring JDBC, and Hibernate, while integrating advanced filtering and search functionality to significantly enhance the user experience.

Key features and technologies implemented:
- Spring Security with OAuth2 for user authentication and CSRF protection
- PostgreSQL database management through Spring Data JPA
- Docker containerization for consistent deployment and scalability
- Maven for project building and management
- Postman for rigorous API testing and validation
- GitHub for version control and collaboration

## Getting Started

### Prerequisites

- Java JDK 11 or later
- Maven
- Docker
- Git
- PostgreSQL

### Clone the Repository

To clone the repository, run the following command:

```
git clone git@github.com:NatalioF22/SpringBlog.git
```

### Build and Run

1. Navigate to the project directory:
   ```
   cd SpringBlog
   ```

2. Set up PostgreSQL database:
   - Install PostgreSQL if not already installed
   - Create a new database for the project

3. Update application.properties with your database credentials:
   ```
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. Refresh project dependencies:
   ```
   mvn clean install
   ```

5. Build the project using Maven:
   ```
   mvn package
   ```

6. Build the Docker image:
   ```
   docker build -t chronic-blog .
   ```

7. Run the Docker container:
   ```
   docker run -p 8080:8080 chronic-blog
   ```

The application should now be running and accessible at `http://localhost:8080`.

## API Documentation

API documentation can be found at `http://localhost:8080/swagger-ui.html` when the application is running.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct and the process for submitting pull requests.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
