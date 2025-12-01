# --------- BUILD STAGE ----------
FROM eclipse-temurin:17 AS build
WORKDIR /workspace

COPY pom.xml .
COPY src ./src

RUN apt-get update && apt-get install -y maven && mvn -version
RUN mvn -B -DskipTests package

# --------- RUNTIME STAGE ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /workspace/target/*.jar /app/app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]
