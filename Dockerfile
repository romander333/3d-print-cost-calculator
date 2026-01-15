FROM eclipse-timurine:17
WORKDIR /application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /application.jar
EXPOSE 8080 ["java", "-jar", "application.jar"]