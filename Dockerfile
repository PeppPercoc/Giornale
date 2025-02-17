#FROM maven:3.9-amazoncorretto-17-alpine AS build

#WORKDIR /app

#RUN apk update && apk add --no-cache git
FROM ubuntu:latest

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    git && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"
RUN git clone --branch release --single-branch https://github.com/PeppPercoc/Giornale.git progetto && cd progetto && mvn clean install -DskipTests && ls -l target/

RUN ls -l /progetto/target/

WORKDIR /progetto/target

ENV TZ=Europe/Rome

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "giornale-0.0.1SNAPSHOT.jar"]
