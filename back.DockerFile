FROM maven:3.8-jdk-11 as build
WORKDIR /app
COPY pom.xml pom.xml
COPY src src
RUN mvn clean compile
RUN mvn  package -DskipTests

FROM openjdk:11
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9000
CMD ["java", "-jar", "app.jar"]
