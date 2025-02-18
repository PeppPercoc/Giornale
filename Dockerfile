FROM ubuntu:latest

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    git && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

RUN git clone --branch release --single-branch https://github.com/PeppPercoc/Giornale.git

WORKDIR /Giornale

RUN mvn package -DskipTests #skip test

EXPOSE 8080

CMD ["java", "-jar", "target/giornale-0.0.1-SNAPSHOT.jar"]
