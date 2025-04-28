FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# 의존성 캐시 최적화
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
RUN chmod +x gradlew \
    && ./gradlew dependencies --no-daemon

# 소스 복사 및 빌드
COPY . .
RUN chmod +x gradlew \
    && ./gradlew clean build -x test --no-daemon

# 실행 스테이지
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 빌드 스테이지에서 생성된 JAR 파일만 복사
COPY --from=build /app/build/libs/*SNAPSHOT.jar ./app.jar

# 애플리케이션 실행 (프로파일은 환경변수로 주입)
ENTRYPOINT [ "java", "-jar", "app.jar" ]