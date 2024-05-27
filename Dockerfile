FROM openjdk:11-jdk-slim
                 #TARGET DEL ARCHIVO PRIMERO GENERARLO CON EL MAVEN
COPY --from=build /target/pw_adminHotelStiven-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]

