FROM swr.cn-north-4.myhuaweicloud.com/oomall-javaee/openjdk:17-alpine
MAINTAINER YUKIPEDIA
WORKDIR /app
ARG JAR_FILE
# 直接使用 COPY 复制构建的 .jar 文件
COPY target/exp7-Mongo-0.0.1-SNAPSHOT.jar /app/exp7-Mongo.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/app/exp7-Mongo.jar"]
CMD ["--spring.datasource.url=jdbc:mysql://mysql:3306/oomall_demo?serverTimezone=Asia/Shanghai", "--spring.datasource.username=demouser", "--spring.datasource.password=123456"]
