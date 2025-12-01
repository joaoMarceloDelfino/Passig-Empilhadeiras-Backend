FROM gradle:8.7-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:17-jdk
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/build/libs/passig-empilhadeiras-1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]