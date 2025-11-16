FROM openjdk:21-jdk

WORKDIR /app

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]

# Add a label to name the Docker image and version it
LABEL name="backend-service" version="1.0.0"
