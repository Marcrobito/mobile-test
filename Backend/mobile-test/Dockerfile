FROM gradle:8.4.0-jdk17 AS build
WORKDIR /app
COPY . .
RUN gradle installDist

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/install/mobile-test /app
EXPOSE 8080
CMD ["./bin/mobile-test"]