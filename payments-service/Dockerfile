# Stage 1: Build
FROM eclipse-temurin:17 AS build
WORKDIR /workspace

# Copy only pom.xml first for caching layer
COPY pom.xml .
RUN apt-get update && apt-get install -y maven
# Pre-download dependencies
RUN mvn -B dependency:go-offline

# Now copy source code
COPY src ./src

# Build
RUN mvn -B -DskipTests package

# Stage 2: Runtime
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
