FROM openjdk:11
COPY build/libs/*.jar app.jar
CMD ["java", "-jar", "-Djava.net.preferIPv4Stack=true", "app.jar"]