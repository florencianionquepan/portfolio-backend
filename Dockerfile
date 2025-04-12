FROM eclipse-temurin:17-jdk-focal
COPY . .
RUN ./mvnw clean install -DskipTests
EXPOSE 8080
# Comando de inicio
ENTRYPOINT ["java", "-jar", "/target/portfolio-0.0.1-SNAPSHOT.jar"]