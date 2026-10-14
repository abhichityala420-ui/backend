FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src
RUN chmod +x mvnw && ./mvnw -B -DskipTests package

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
RUN mkdir -p /app/data && chown 10001:0 /app/data
COPY --from=build --chown=10001:0 /workspace/target/system-0.0.1-SNAPSHOT.jar /app/app.jar
USER 10001
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
