FROM openjdk:8-jdk-alpine
ARG JAR_FILE=build/libs/weather-1.0.jar
COPY ${JAR_FILE} /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "weather-1.0.jar"]

EXPOSE 8080