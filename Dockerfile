# syntax=docker/dockerfile:1

# ---- Build Stage ----
FROM eclipse-temurin:24-jdk AS build
WORKDIR /app

# 1) 래퍼/설정 먼저 복사(의존성 캐시 최적화)
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
# (있으면) gradle.properties도 함께
# COPY gradle.properties ./

# 2) 래퍼 실행 권한 부여 및 Gradle 버전 확인
RUN chmod +x gradlew
RUN ./gradlew --version

# 3) 소스 복사 후 빌드
COPY src src
# 테스트는 Docker 빌드에서 제외(원인 분리)
RUN ./gradlew clean bootJar -x test --no-daemon

# ---- Run Stage ----
FROM eclipse-temurin:24-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# Render 환경: PORT가 주입됨. (Spring에서 server.port=${PORT:8080} 권장)
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]