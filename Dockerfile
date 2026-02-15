FROM eclipse-temurin:8-jre-alpine

EXPOSE 8080

WORKDIR /usr/app
COPY ./target/jenkins-java-example-*.jar /usr/app/

CMD java -jar jenkins-java-example-*.jar