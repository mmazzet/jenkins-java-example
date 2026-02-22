FROM eclipse-temurin:8-jre-alpine

EXPOSE 8080

WORKDIR /usr/app
COPY ./target/jenkins-java-example-1.0.0-NEW-SNAPSHOT.jar /usr/app/


ENTRYPOINT ["java", "-jar", "jenkins-java-example-1.0.0-NEW-SNAPSHOT.jar"]