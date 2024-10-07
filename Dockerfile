# 백엔드 Dockerfile

# OpenJDK 17 이미지 사용
FROM openjdk:17-jdk-alpine

# 애플리케이션 JAR 파일을 컨테이너의 /app 디렉토리에 복사
COPY target/*.jar /app/app.jar

# 작업 디렉토리 설정
WORKDIR /app

# 애플리케이션 포트 설정 (예: 8080)
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
