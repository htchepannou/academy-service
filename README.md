[![Build Status](https://travis-ci.org/htchepannou/academy-service.svg?branch=master)](https://travis-ci.org/htchepannou/academy-service)
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
$ mvn clean install
```

This will generate the service binary ``target/academy-service.jar``

### Run the server locally
```
$ java -Dspring.profiles.active=local -jar target/academy-service.jar
```
The server will run locally on the port `18080`
- Verify the status of the service at [http://localhost:18080/health](http://localhost:18080/health). The status should be `UP`. 
- Access the API documentation at [http://localhost:18080/swagger-ui.html](http://localhost:18080/swagger-ui.html) 

## License
This project is open source sofware under the [MIT License](https://opensource.org/licenses/MIT)
