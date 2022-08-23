FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/xier.jar xier.jar
ENTRYPOINT ["java","-jar","/xier.jar", "&"]