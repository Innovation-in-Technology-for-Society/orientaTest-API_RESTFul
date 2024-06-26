FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/orientaTest-0.0.1.jar
COPY ${JAR_FILE} orientaTest.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "orientaTest.jar"]