FROM eclipse-temurin:25
COPY target/vehicle-service-0.0.1-SNAPSHOT.jar /opt/app/vehicle-service-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
EXPOSE 8086

ENTRYPOINT ["java","-jar","vehicle-service-0.0.1-SNAPSHOT.jar"]


