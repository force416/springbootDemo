FROM openjdk:8-jdk-alpine
WORKDIR /usr/src/myapp
ADD ./target/*.jar app.jar
CMD tail -f /dev/null