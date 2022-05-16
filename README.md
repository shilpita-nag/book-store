# Book-Store
A Spring Boot Application implementing Book Store Operation.
This application used H2 in-memory database with JPA and Hibernate

### Adding H2 Dependencies
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

### Adding JPA Dependency
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
Above dependencies can as well be added while generation of spring boot project from Spring Initializer.

### Configuring Persistence Layer
Adding below annotations to BookStoreApplication.java
```java
@EnableJpaRepositories("<path_to_repository>") 
@EntityScan("<path_to_model")
```
@EnableJpaRepositories to scan the specified package for repositories
@EntityScan to pick up our JPA entities

### Initial data load
Defining schema in <b>schema.sql</b>\
Inserting data in <b>data.sql</b>

Also add the following configuration to <b>application.properties</b>
```properties
spring.jpa.defer-datasource-initialization=true
```
### Postman Collection:
[Book Store](https://www.getpostman.com/collections/00becb579e4066cf833f)

## References:
[https://www.baeldung.com/spring-boot-start](https://www.baeldung.com/spring-boot-start)
[https://www.springboottutorial.com/spring-boot-crud-rest-service-with-jpa-hibernate](https://www.springboottutorial.com/spring-boot-crud-rest-service-with-jpa-hibernate)
[https://www.javatpoint.com/spring-boot-h2-database](https://www.javatpoint.com/spring-boot-h2-database)
