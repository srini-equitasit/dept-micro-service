FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar dept-micro-service.jar
ENTRYPOINT ["java","-jar","/dept-micro-service.jar"]