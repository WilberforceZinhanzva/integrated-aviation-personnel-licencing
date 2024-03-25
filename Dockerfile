FROM eclipse-temurin:17-jdk-alpine
ADD target/*.jar /app/aviation_licencing.jar
WORKDIR /app
EXPOSE 8017
ENTRYPOINT ["java", "-jar", "aviation_licencing.jar"]