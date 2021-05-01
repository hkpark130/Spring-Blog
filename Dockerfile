FROM openjdk:8-jdk-alpine
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mkdir /app
WORKDIR /app
ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=docker-compose \
/app/build/libs/spring-blog-0.0.1-SNAPSHOT.jar"]
