FROM openjdk:8
EXPOSE 8081
ADD target/product-service-0.0.1-SNAPSHOT.jar product-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "product-service-0.0.1-SNAPSHOT.jar"]

