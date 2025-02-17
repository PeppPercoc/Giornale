FROM maven:3.9-amazoncorretto-17-alpine AS build

WORKDIR /app

RUN apk update && apk add --no-cache git
RUN git clone --branch release --single-branch https://github.com/PeppPercoc/Giornale.git progetto && cd progetto && mvn clean install -DskipTests

WORKDIR /app/progetto/target

ENV TZ=Europe/Rome

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "giornale-0.0.1-SNAPSHOT.jar"]
