# Connect4 Game Backend

### Description
This code has been developed as a part of Pratilipi Hiring Challenge conducted on Hackerearth.
This application provides the backend APIs for the connect4 game. <br>
To know more or get your hands dirty, please visit - https://www.mathsisfun.com/games/connect4.html <br>

### Game points
1. The API request to make moves accepts the column where the coin is dropped. Valid column values are from 0-6.
2. This is a 2 player game with player labelled as - "RED" & "YELLOW" where "YELLOW" takes the first move.
3. To start the game, create a session token using Token endpoint and use it in subsequent Move endpoint calls.

### System Requirements
Please use the following minimum version of java and maven
~~~
Java version: 1.8.0_181
Apache Maven 3.6.1
~~~

### How to run
This is a standard Spring Framework project. To run the project directly - 
~~~
mvn spring-boot:run
~~~
To build a jar and run it -
~~~
mvn clean package
cd target/
java -Dserver.port=8080 -jar connect4-pratilipi-0.0.1.jar
~~~

### How to use
There are 4 endpoints the application provides - 
1. Start game
2. Submit player's moves
3. Fetch sequence of moves taken in the game by both players
4. Heartbeat API to perform health check of the application

##### The payload description is attached as Postman Tool collection in the `docs/` folder.

##### Swagger specification link - 
```
http://localhost:8080/swagger-ui
```
##### Here are the sample curl commands to hit the endpoints once the code/jar is run

###### Start Game
```
curl --request POST 'http://localhost:8080/v1/connect4/start' \
     --header 'Content-Type: application/json' \
     --header 'Accept: application/json' \
     --header 'X-C4-TrackingId: postman-start-test' \
     --data-raw ''
``` 
###### Make Move
```
curl --request POST 'http://localhost:8080/v1/connect4/move' \
     --header 'Content-Type: application/json' \
     --header 'Accept: application/json' \
     --header 'X-C4-TrackingId: postman-start-move' \
     --data-raw '{
         "sessionId": "000f6e6a-cd3c-4341-8dd1-14aff64ef21a",
         "player": "YELLOW",
         "position": 1
     }'
```

###### Fetch Past Moves
```
curl --request POST 'http://localhost:8080/v1/connect4/view' \
     --header 'Content-Type: application/json' \
     --header 'Accept: application/json' \
     --header 'X-C4-TrackingId: postman-heartbeat' \
     --data-raw '{
         "sessionId":  "a1c088d1-08d2-494b-b80c-e334bf82c491"
     }'
```

###### Health Check
```
curl --request GET 'http://localhost:8080/v1/connect4/heartbeat' \
     --header 'Content-Type: application/json' \
     --header 'Accept: application/json' \
     --header 'X-C4-TrackingId: postman-heartbeat' \
     --data-raw ''
```

### TODO
- [ ] Java & Spring Basics
    - [x] Strategy design pattern
    - [x] Builder design pattern
    - [x] Java Generics
    - [x] Constant & enum usages
    - [x] Exception declaration & management
    - [x] Object 2 Json conversions & vice-versa
    - [x] Project package structure 
    - [x] Utility class usages
    - [x] Utility class usages
    - [x] Java 8 Optional & Streams
    - [x] Placement of code comments & documentation blocks
    - [ ] Usage of spring application lifecycle events 
- [ ] Documentation 
    - [x] .gitignore usage
    - [x] ReadMe.md & documentation formats
    - [x] Changelog format
- [ ] Database Related
    - [x] Hibernate ORM
    - [x] Table joins & @Formula usages
    - [x] Hibernate enum converter
    - [x] H2 Database usage
    - [x] Spring transaction management
- [ ] Rest APIs with spring-boot
    - [x] REST API 
    - [x] @Controller Advice Usage
    - [x] HTTP Request Interceptor
    - [x] Swagger setup 
    - [x] API payload validations
    - [ ] API Security
    - [ ] HTTP request response log capture
- [ ] Enterprise Application practices
    - [x] Log4j2 Implementation
    - [x] Log4j2 patterns & appenders
    - [x] Spring profiles
    - [ ] Application health monitoring using spring-actuators
- [ ] Unit & Integration Testing
    - [x] Unit Testing with JUnit5
    - [x] Declaration of separate resources for test scope
    - [ ] Mocks & Stubs usages
    - [ ] REST API unit testing
    - [ ] Maven surefire & failsafe plugin usages
- [ ] Deployment & Automation pipelines
    - [ ] Build scripts
    - [ ] Dockerfile
    - [ ] Jenkinsfile
    - [ ] Kubernetes deployment scripts
    
