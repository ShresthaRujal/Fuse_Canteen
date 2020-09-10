# Fuse_Canteen
<img align="left" width="48" height="48" src="./spring-boot-logo.jpg">

# Spring Boot Application Example

[![Build Status](https://travis-ci.org/mertakdut/Spring-Boot-Sample-Project.svg?branch=master)](https://travis-ci.org/mertakdut/Spring-Boot-Sample-Project)
[![Coverage Status](https://coveralls.io/repos/github/mertakdut/Spring-Boot-Sample-Project/badge.svg?branch=master)](https://coveralls.io/github/mertakdut/Spring-Boot-Sample-Project?branch=master)

This is a simple Java / Maven / Spring Boot application which provides RESTful services. It can be used as a starter project.

### Prerequisites

You are required to have few tools before you start with using the source code.
- JDK 1.8
- Maven
- IDE (Recommended - Intellij IDEA)
- MySQL
- Git

Install JDK, Maven and Git as first step, before you get the the code base setup.

### Setup Code Base

Use below URL to Clone Source Code

```sh
git clone https://github.com/get2mandar/springmvc-crud-app.git
```

Build a Project using Maven by Command Line.<br>
Change Directory to Downloaded Project Directory and execute below Maven Command.

```sh
mvn clean install
```


## Run

You will require a Tomcat or JBOSS some web server to run this web application. Run with any web server and view the output on a browser.

## Installation Instructions
  You can import the project as a maven application to your favorite IDE. I made my tests by using Intellij IDEA 2019.2.
  
  If lombok gets in your way, by referring [this answer](https://stackoverflow.com/a/22332248/4130569), you can install lombok by its jar file.

## To run the application
Use one of the several ways of running a Spring Boot application. Below are just three options:

1. Build using maven goal (or by using maven wrapper): `mvn clean package` and execute the resulting artifact as follows `java -jar BankApplicationBackend-0.0.1-SNAPSHOT.jar` OR,
Directly execute command as follows mvn spring-boot:run
2. On Unix/Linux based systems: run `mvn clean package` then run the resulting jar as any other executable `./BankApplicationBackend-0.0.1-SNAPSHOT.jar`


## To test the application
  1. To check if Application is Running
  
  `GET http://localhost:8080/api/health`
  you will get response as below:
  
  `Running .............. `
  
  
  2. To check further api's you will find [postman](https://www.postman.com/downloads/) collection in project folder to import into,
