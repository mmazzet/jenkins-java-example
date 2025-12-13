FROM eclipse-temurin:8-jre-alpine

EXPOSE 8080

WORKDIR /usr/app
COPY ./target/version-display-app-1.0.0-NEW-SNAPSHOT.jar /usr/app/


ENTRYPOINT ["java", "-jar", "version-display-app-1.0.0-NEW-SNAPSHOT.jar"]