# Credit Card Service:
Microservice to provide api for credit card operations.
Api Operations:
[Open API 3.0 Swagger URL](http://34.122.130.87/api/swagger-ui/index.html) For demo basic auth username: demouser and password: demopass
<img width="988" alt="Api Operations" src="https://user-images.githubusercontent.com/99208477/154868858-e9561db4-254e-4580-bf35-bb60ddee9970.png">


CD Pipeline- ArgoCD on Google Kubernetes Engine: [ArgoCD](https://35.193.125.8/applications/credit-card-processing )
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
