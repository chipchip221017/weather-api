FROM openjdk:8-jre-alpine

EXPOSE 8080

COPY ./build/libs/weather-api-1.0.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "weather-api-1.0.jar"]