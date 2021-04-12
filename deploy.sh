#!/bin/bash
APPLICATION_NAME=backend-spring-app

CONTAINER_ID=$(docker ps | grep backend-spring-app | awk '{print $1}')

if [ $CONTAINER_ID ]
then
  docker stop $CONTAINER_ID
  docker rm $CONTAINER_ID
fi

cd /home/ec2-user/spring
sh gradlew clean
sh gradlew build
docker-compose build --no-cache
docker-compose up --build
