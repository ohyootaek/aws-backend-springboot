# 베이스 이미지 설정
FROM bellsoft/liberica-openjdk-alpine:17

# JAR 파일 위치 지정
ARG JAR_FILE=target/*.jar

# JAR 파일을 컨테이너로 복사
COPY ${JAR_FILE} app.jar

# 도커 컨테이너를 시작할 때 실행할 명령어
ENTRYPOINT ["java","-jar","/app.jar"]
