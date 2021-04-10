#!/bin/bash
APPLICATION_NAME=backend-spring-app

CONTAINER_ID=$(docker ps | grep backend-spring-app | awk '{print $1}')

if [ $CONTAINER_ID ]
then
  docker stop $CONTAINER_ID
  docker rm $CONTAINER_ID
fi

sh gradlew build
docker-compose up --build -d
