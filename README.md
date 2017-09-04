Master: [![Build Status](https://travis-ci.org/htchepannou/academy-service.svg?branch=master)](https://travis-ci.org/htchepannou/academy-service)
[![Code Coverage](https://img.shields.io/codecov/c/github/htchepannou/academy-service/master.svg)](https://codecov.io/github/htchepannou/academy-service?branch=master)
[![JDK](https://img.shields.io/badge/jdk-1.8-brightgreen.svg)](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)

# Academy Service
Academy Service provide a REST API for managing a library of online courses

## Requirements
- Java 1.8
- Maven
- MySQL

## Installation
Initialize the local database
```
$ mysql -uroot
...
mysql> CREATE DATABASE academy;
```

Clone the code repository locally and build it.
```
$ git clone git@github.com:htchepannou/academy-service.git
$ cd academy-service
$ mvn compile flyway:clean flyway:migrate
$ mvn clean install
```

This will generate the service binary ``target/academy-service.jar``

## Run
### Database Setup 
Initialize the local database with the sample data:
```
$ mvn compile flyway:clean flyway:migrate
$ mvn compile sql:execute
```

### Run the server
```
$ java -jar target/academy-service.jar
```

# Links
- Local Environment
    - [API Documentation](http://localhost:8080/swagger-ui.html) 
    - [Service Health](http://localhost:8080/health) 

- Integration Environment
    - [API Documentation](https://io-tchepannou-academy-service.herokuapp.com/swagger-ui.html) 
    - [Service Health](https://io-tchepannou-academy-service.herokuapp.com/health) 
