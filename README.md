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
git clone https://github.com/ShresthaRujal/Fuse_Canteen.git'

```


## Installation Instructions
  You can import the project as a maven application to your favorite IDE. I made my tests by using Intellij IDEA 2019.2.
  
  If lombok gets in your way, by referring [this answer](https://stackoverflow.com/a/22332248/4130569), you can install lombok by its jar file.


##Run
Run a Project using Maven by Command Line.<br>
Change Directory to Downloaded Project Directory and execute below Maven Command.

```sh
mvn spring-boot:run
```


## To test the application
  1. To check if Application is Running
    
      `GET http://localhost:8080/api/health`
      you will get response as below:
      
      `Running .............. `
    
  2. Ask for tokens[access+refresh] using HTTP POST on /oauth/token, with grant_type=password,and resource owners credentials as req-params. Additionally, send client credentials in Authorization header.
     
     `POST http://localhost:8080/oauth/token?grant_type=password&username=rujalsh&password=rujal@123`
     
  3. Access the resource by providing an access token using access_token query param with request.
  
     `GET http://localhost:8080/api/user/create/?access_token={{Access_Token}} `  
  
  4. To check further api's/resources you will find [postman](https://www.postman.com/downloads/) collection in project folder to import into
  
  ##Technology Used
  1. Spring Boot (includes JPA Auditing)
  2. Mysql
  3. Oauth2 with JWT
  4. LogDb with Spring AOP
  
  ##Features
  #####Roles of Admin:
  1. Add Food Items with Price (CRUD)
  2. Prepare a list of food items for today
  3. View orders made by Employee
  4. View Requested Food by Popularity
  #####Roles of Employee:
  1. View list of food items in Menu for today
  2. Order food items for today (should also get total price) (CRUD)
  4. The employee can schedule the order.
  3. View previous days orders (Track history of Orders made)
  4. Request Food to be prepared today (CRUD)
  5. Ordered food status (PENDING -> INPROCESS -> READY)
