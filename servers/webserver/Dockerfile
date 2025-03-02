FROM openjdk:8-jdk-slim
# 设置工作目录，所有的操作都将在该目录下进行。
WORKDIR /
COPY ./target/webserver.jar /
COPY ./target/lib /lib
COPY ./target/resources /resources
#ENTRYPOINT ["java", "-jar","-Dloader.path=lib,resources","-Dfile.encoding=utf-8","webserver.jar"]
CMD ["java", "-jar","-Dloader.path=lib,resources","-Dfile.encoding=utf-8","webserver.jar"]
