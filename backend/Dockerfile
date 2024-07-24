FROM maven:3-eclipse-temurin-21 as build
WORKDIR /backend
COPY pom.xml pom.xml
COPY src src
COPY conf conf
RUN mvn package

FROM eclipse-temurin:21-jdk
WORKDIR /backend
COPY --from=build /backend/target/api-1.0.0.jar app.jar
COPY conf conf
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
