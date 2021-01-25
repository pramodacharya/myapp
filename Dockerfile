FROM openjdk:8-jdk-alpine
WORKDIR /
COPY target/myapp-0.0.1-SNAPSHOT.jar myapp.jar
EXPOSE 8080
CMD java -jar myapp.jar

