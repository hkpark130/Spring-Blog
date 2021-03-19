FROM openjdk:8-jdk-alpine
RUN mkdir /app
WORKDIR /app
ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=docker-compose /app/build/libs/spring-blog-0.0.1-SNAPSHOT.jar"]
