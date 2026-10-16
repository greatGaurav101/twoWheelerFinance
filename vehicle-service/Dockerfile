FROM eclipse-temurin:25
COPY target/vehicle-service.jar /opt/app/vehicle-service.jar
WORKDIR /opt/app
EXPOSE 8086

ENTRYPOINT ["java","-jar","vehicle-service.jar"]


