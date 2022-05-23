FROM openjdk:8-jdk-alpine
COPY build/libs/*.jar weather.jar

ENTRYPOINT ["java", "-jar", "/weather.jar"]

EXPOSE 8080