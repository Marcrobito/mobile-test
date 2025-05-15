FROM gradle:8.2.1-jdk17 AS build
WORKDIR /home/gradle/app
COPY --chown=gradle:gradle . .
RUN gradle installDist

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /home/gradle/app/build/install/mobile-test /app
EXPOSE 8080
RUN chmod +x /app/bin/mobile-test
ENTRYPOINT ["/app/bin/mobile-test"]