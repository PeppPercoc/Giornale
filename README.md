# Newspaper

## Idea

This project is a backend written in Java that simulates a database where a newspaper can insert articles and publications, and users can purchase those publications.

## Telegram

Follow us on Telegram to be updated on releases. Search **@GiornaleMGbot** and send `/start` to receive notifications

# How to use

## Introduction
There are two ways to run the project:

1. **For developers**: You can clone the repository and follow the guide below to set up the project manually.
2. **For users**: If you only want to use the backend, you can download the Docker image available at `pepppercoc/giornale`. The images are versioned, so you can choose the one you need. To run the project, you must have Docker installed and also download the MongoDB and Mongo Express images.

## For Users
### Prerequisites

Before running the project, make sure you have Docker installed on your machine.

### Docker Compose Configuration

Below is the `docker-compose.yml` file to set up the necessary services:

```yaml
services:
  mongodb:
    image: mongo
    container_name: mongodb
    ports:
      - "27017:27017"
    volumes:
      - data:/data
    environment:
      - MONGO_INITDB_ROOT_USERNAME=rootuser
      - MONGO_INITDB_ROOT_PASSWORD=rootpass
    networks:
      - giornale-network

  mongo-express:
    image: mongo-express
    container_name: mongo-express
    ports:
      - "8081:8081"
    environment:
      - ME_CONFIG_MONGODB_SERVER=mongodb
      - ME_CONFIG_MONGODB_ADMINUSERNAME=rootuser
      - ME_CONFIG_MONGODB_ADMINPASSWORD=rootpass
    networks:
      - giornale-network

  giornale:
    image: pepppercoc/giornale:latest
    container_name: giornale-app
    restart: on-failure
    ports:
      - "8080:8080"
    depends_on:
      - mongodb
      - mongo-express
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://rootuser:rootpass@mongodb:27017/giornale?authSource=admin
    networks:
      - giornale-network

volumes:
  data: {}

networks:
  giornale-network:
    driver: bridge
```
To start the Docker containers for MongoDB and Mongo Express, navigate to the project root and run:

```
docker-compose up --build
```

## For Developers
### Start Docker Containers

To start the Docker containers and run:

```
docker-compose up --build
```

Ensure that Docker is running for a successful setup.

### Install Java Dependencies

To install the required Java dependencies, move to the root of the project and execute:

```
mvn clean install
```

If you encounter errors related to the test suite or test cases, use:

```
mvn install -DskipTests
```

### Start the Project

To start the project, open `MongorestApplication` in your IDE and run it.

## Check the Database

To inspect the database, go to [Mongo Express](http://localhost:8081/unimolab/) (localhost on port 8081).

## Try the API

To test the API, visit [Swagger UI](http://localhost:8080/swagger-ui/index.html) (localhost on port 8080 at `/swagger-ui`).
