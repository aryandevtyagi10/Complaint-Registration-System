# Complaint Registration System

A Spring Boot application for registering and managing complaints using an H2 in-memory database. The system provides RESTful APIs to create, read, update, and delete complaints, along with a Thymeleaf-based web interface for user interaction. It registers complaints and have options to mark it as ongoing , to be approved and resolved.

## Features
- Register complaints with a description and status (OPEN or RESOLVED).
- View all complaints or filter by status (e.g., OPEN complaints).
- Update or delete existing complaints.
- Web interface for easy complaint submission and viewing.
- H2 in-memory database for lightweight storage during development.
- Input validation for complaint status (defaults to OPEN if invalid).

## Tech Stack
- **Framework**: Spring Boot 3.2.5
- **Language**: Java 17 (though running on Java 24 in development)
- **Database**: H2 (in-memory)
- **Frontend**: Thymeleaf
- **Build Tool**: Maven
- **Dependencies**:
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-thymeleaf`
  - `spring-boot-devtools`
  - `h2`

## Prerequisites
- **Java**: JDK 17 (or 24 if you’ve updated the `pom.xml` to `<java.version>24</java.version>`).
- **Maven**: Ensure Maven is installed (`mvn --version` to check).
- **Git**: To clone the repository.

## Setup Instructions
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/complaint-registration.git
   ```
   Replace `your-username` with your GitHub username.

2. **Navigate to the Project Directory**:
   ```bash
   cd complaint-registration
   ```

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   The application will start on port 7070.

5. **Access the Application**:
   - **REST API Base URL**: `http://localhost:7070/api`
   - **Web Interface**: `http://localhost:7070/`
   - **H2 Console**: `http://localhost:7070/h2-console`
     - JDBC URL: `jdbc:h2:mem:complaintsdb`
     - Username: `sa`
     - Password: (leave blank)

## API Endpoints
The following REST API endpoints are available:

- **GET /api/complaints**  
  List all complaints.  
  Example: `curl http://localhost:7070/api/complaints`

- **GET /api/complaints/{id}**  
  Get a complaint by ID.  
  Example: `curl http://localhost:7070/api/complaints/1`

- **GET /api/complaints/status/open**  
  List all complaints with status OPEN.  
  Example: `curl http://localhost:7070/api/complaints/status/open`

- **POST /api/complaint**  
  Create a new complaint.  
  Example:  
  ```bash
  curl -X POST http://localhost:7070/api/complaint -H "Content-Type: application/json" -d '{"description":"Website is down","status":"OPEN"}'
  ```

- **PUT /api/complaint/{id}**  
  Update a complaint by ID.  
  Example:  
  ```bash
  curl -X PUT http://localhost:7070/api/complaint/1 -H "Content-Type: application/json" -d '{"description":"Website is down","status":"RESOLVED"}'
  ```

- **DELETE /api/complaint/{id}**  
  Delete a complaint by ID.  
  Example: `curl -X DELETE http://localhost:7070/api/complaint/1`

## Web Interface
- Access the Thymeleaf-based web interface at `http://localhost:7070/`.
- The interface allows you to:
  - Submit new complaints via a form.
  - View a table of all registered complaints.

## Project Structure
- `src/main/java/com/example/complaints/`
  - `ComplaintRegistrationApplication.java`: Main application entry point.
  - `model/Complaint.java`: Entity class for complaints.
  - `repository/ComplaintRepository.java`: JPA repository for database operations.
  - `service/ComplaintService.java`: Business logic for complaint management.
  - `controller/ComplaintController.java`: REST API controller.
  - `controller/WebController.java`: Thymeleaf web controller.
- `src/main/resources/`
  - `application.properties`: Configuration for H2 database, port, and JPA.
  - `templates/complaint-form.html`: Thymeleaf template for the web interface.

## Configuration
The `application.properties` file contains the following key configurations:
```properties
spring.datasource.url=jdbc:h2:mem:complaintsdb;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.transaction.jta.platform=org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform
spring.devtools.restart.enabled=false
server.port=7070
```

## Testing
1. **API Testing**:
   Use tools like curl, Postman, or a browser to test the API endpoints listed above.

2. **Web Interface Testing**:
   Open `http://localhost:7070/` to submit and view complaints.

3. **Database Verification**:
   Use the H2 console (`http://localhost:7070/h2-console`) to inspect the `COMPLAINT` table:
   ```sql
   SELECT * FROM COMPLAINT;
   ```

## Known Issues
- **Java Version Mismatch**: The project is configured for Java 17 (`<java.version>17</java.version>` in `pom.xml`), but logs show it’s running on Java 24. Update the `pom.xml` to `<java.version>24</java.version>` if you intend to use Java 24, or switch to Java 17.
- **Native Access Warning**: When running on Java 24, you may see a warning about `java.lang.System::load`. Add `--enable-native-access=ALL-UNNAMED` to your JVM arguments to suppress this.

## Future Improvements
- Add user authentication for secure access.
- Implement pagination for the complaints list.
- Switch to a persistent database (e.g., PostgreSQL) for production.
- Add input validation for the description field.
- Enhance the web interface with better styling and features.

## License
MIT License (or specify your preferred license).
