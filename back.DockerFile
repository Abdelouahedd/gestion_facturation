FROM maven:3.6-adoptopenjdk-11 as build
WORKDIR /app
COPY . /app
RUN mvn clean compile
RUN mvn clean package

FROM adoptopenjdk:11-jdk
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app
EXPOSE 9000
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
