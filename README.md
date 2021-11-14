# Read Me First
The following was discovered as part of building this project:

* The original package name 'jooq.demo.com' is invalid and this project uses 'jooq.demo.com' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [JOOQ Access Layer](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-jooq)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
# Example run application:
* ``IDE``: [IntelliJ IDEA](#)
* ``Create database: postgres``
* ``Change database name, username, password in file`` [application.yml](#), [pom.xml](#)
* ``url: jdbc:postgresql://localhost:5432/postgres``
* ``username: postgres ``
* ``password: 123456``
* ``mvn clean, install``
* Insert data test:
```
INSERT INTO masters.author VALUES
  (1, 'Kathy', 'Sierra'),
  (2, 'Bert', 'Bates'),
  (3, 'Bryan', 'Basham');

INSERT INTO masters.book VALUES
  (1, 'Head First Java'),
  (2, 'Head First Servlets and JSP'),
  (3, 'OCA/OCP Java SE 7 Programmer');

INSERT INTO masters.author_book VALUES (1, 1), (1, 3), (2, 1);
INSERT INTO masters.accounts (username,"password",email,created_on,last_login) VALUES
('sang','1234','sang@gmail.com','2021-11-02 10:51:12.903','2021-11-02 10:51:12.903')
,('trong','4455','trong@gmail.com','2021-11-02 10:51:33.145','2021-11-02 10:51:33.145');
```
## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `jooq.demo.com.DemoJooqApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
