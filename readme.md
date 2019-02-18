# City Connectivity Service

By [Japan Trivedi](mailto:japan.trivedi@hotmail.com)

[Japan Trivedi - Stackoverflow Profile](https://stackoverflow.com/users/story/944108)

[Japan Trivedi - LinkedIn Profile](https://www.linkedin.com/in/japantrivedi)

## Instructions

1. Navigate to [repo](https://github.com/japan733/CityConnectivityService)
2. Clone locally using
 `git clone https://github.com/japan733/CityConnectivityService.git`
3. Build application using `gradlew clean build --refresh-dependencies`
4. Run tests using `gradlew test`
5. Start your server using `gradlew bootRun`
6. Navigate to app in [browser](http://localhost:8080/swagger-ui.html)
7. Enjoy!

## Discussion

I have used the following technologies: Java 8, Gradle, Spring Boot.
I have used Spring Boot Starter to generate the scaffolding for this program.

## Requirements

#### Write a program in Java which determines if two cities are connected. Two cities are considered connected if there’s a series of roads that can be traveled from one city to another.

The program uses Google Guava API to use the Graph data structure in order to store the list of connected cities.
At the start of the application, I am loading all the pre-defined connected cities from city.txt file from application resources.

## Bonuses!

#### Ability to add more connected cities at runtime through POST API call.
