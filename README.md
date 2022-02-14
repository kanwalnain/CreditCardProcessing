# Credit Card Service:
Microservice to provide api for credit card operations.

# Setting up development environment
- Import maven project in Intellij or Eclipse.

# Database
- HSQLDB is used as a database.

# Technology Stack
- Spring Boot 2.4.0
- HQSQL DB
- Spring Data JPA
- Maven
- Docker
- Open API 3.0(Swagger)

Docker Build and Run.
```docker
docker build -t kanwalnain/creditcardprocessing .
docker run -p 8082:8082  kanwalnain/creditcardprocessing

```
Maven Run
```mvn
mvn clean install
mvn spring-boot:run
```
- Open browser and go to URL "http://localhost:8080/api/swagger-ui/index.html" and explore API operations.
For demo basic auth username: demouser and password: demopass