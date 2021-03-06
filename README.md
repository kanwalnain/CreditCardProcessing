# Credit Card Service:
[![Actions Status](https://github.com/kanwalnain/CreditCardProcessing/workflows/Build%20and%20Test%20CI/badge.svg)](https://github.com/kanwalnain/CreditCardProcessing/actions)
![Coverage](./badges/jacoco.svg)
![Branches](./badges/branches.svg)
Microservice to provide api for credit card operations.

Api Operations:
[Open API 3.0 Swagger URL](http://34.122.130.87/api/swagger-ui/index.html) Test login details : username: demouser and password: demopass
<img width="752" alt="Screenshot 2022-02-22 at 12 34 04 AM" src="https://user-images.githubusercontent.com/99208477/155042446-f2880928-9e90-4f28-8488-21f2a1d087e4.png">


CD Pipeline- ArgoCD on Google Kubernetes Engine: [ArgoCD](https://35.193.125.8/applications/credit-card-processing ) Demo user: admin pass: pJPak0E7H53B6RJW
<img width="1076" alt="CI CD Pipeline" src="https://user-images.githubusercontent.com/99208477/154872757-b4400e57-2698-495d-9fac-6d7ff95621bd.png">

<img width="1582" alt="CreditCardProcessing" src="https://user-images.githubusercontent.com/99208477/154868730-85c84330-8857-424f-9f4e-1192fd3524d3.png">



# Database
- HSQLDB is used as a database.

# Technology Stack
- Spring Boot 2.4.0
- HQSQL DB
- Spring Data JPA
- Maven
- Docker
- Open API 3.0(Swagger)

# Setting up development environment
- Import maven project in Intellij or Eclipse.
Docker Build and Run.

```docker
docker build -t kanwalnain/creditcardprocessing .
docker run -p 8080:8080  kanwalnain/creditcardprocessing

```
Maven Run
```mvn
mvn clean install
mvn spring-boot:run
```
- Open browser and go to URL "http://localhost:8080/api/swagger-ui/index.html" and explore API operations.
For demo basic auth username: demouser and password: demopass
