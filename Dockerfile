FROM eclipse-temurin:8-jre-alpine

EXPOSE 8080

WORKDIR /usr/app
COPY ./target/version-display-app-*.jar /usr/app/

CMD java -jar version-display-app-*.jar