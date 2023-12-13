FROM dockerhub.ecasoft.cn/ecabase:latest
MAINTAINER czg
WORKDIR /
COPY ./target/webserver.jar /
COPY ./target/lib /lib
COPY ./target/resources /resources
ENTRYPOINT ["java", "-jar","-Dloader.path=lib,resources","-Dfile.encoding=utf-8","webserver.jar"]
