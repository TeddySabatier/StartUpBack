# Build stage
FROM  openjdk:latest
WORKDIR /target
COPY target ./target

EXPOSE 8081
CMD ["java", "-jar", "./target/project-0.0.1-SNAPSHOT.jar"]