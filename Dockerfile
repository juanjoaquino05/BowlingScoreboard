FROM openjdk:8

COPY . /usr/src/myapp
WORKDIR /usr/src/myapp

RUN /usr/src/myapp/mvnw clean install   

env APP /usr/src/myapp/target/*.jar

cmd java -jar $APP