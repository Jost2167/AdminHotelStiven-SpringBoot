FROM openjdk:17-jdk-slim

COPY /target/pw_adminHotelStiven-0.0.1-SNAPSHOT.jar demo.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]

