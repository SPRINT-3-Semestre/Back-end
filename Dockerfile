FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY ./target/editMatch-0.0.1-SNAPSHOT.jar /app/editMatch.jar

EXPOSE 8080

CMD ["java", "-jar", "editMatch.jar"]