FROM eclipse-temurin:17-jdk-focal
COPY ./target/portfolio-0.0.1-SNAPSHOT.jar portfolio.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/portfolio.jar"]