# 기본 OpenJDK 이미지 사용  
FROM openjdk:17-jdk  

# docker 컨테이너의 작업 디렉토리(없으면 이 이름으로 자동생성)
WORKDIR /spring-boot

# build/libs/*SNAPSHOT.jar 이 경로에 있는 jar 파일을 app.jar 파일에 옮김
COPY build/libs/*SNAPSHOT.jar app.jar

# 도커 컨테이너 실행시
ENTRYPOINT ["java", "-jar", "/spring-boot/app.jar"]