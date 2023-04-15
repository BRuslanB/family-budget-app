FROM openjdk:17-alpine
MAINTAINER BRuslan
COPY build/libs/family-budget-0.0.1-SNAPSHOT.jar docker-family-budget-0.0.1.jar
ENTRYPOINT ["java", "-jar", "docker-family-budget-0.0.1.jar"]