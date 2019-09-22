FROM maven:3.6.1-jdk-8-alpine as builder
WORKDIR /usr/src/app
ADD . .
RUN ["mvn","package","-Dmaven.test.skip=true"]

FROM openjdk:8-jdk-alpine
WORKDIR /usr/src/myapp
COPY --from=builder /usr/src/app/target/*.jar app.jar
CMD tail -f /dev/null