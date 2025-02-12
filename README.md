# Newspaper
## Idea


## Start docker container
To start the docker containers of Mongo and Mongo-express, in the project root use the terminal and type:
`docker-compose up --build`
Docker must be in running status to get a successful result.

## Install Java dependencies
To install the Java dependencies, move into the root of the project and type:
`mvn clean install`
If you get errors related to the test suite or test cases, please type:
`mvn install -DskipTests`

## Start the project
To start the project, open MongorestApplication and use the button run.

## Check the DB
To check the DB go to [mongo express](http://localhost:8081/unimolab/) (localhost on port 8081)

## Try the api
To try the api go to [swagger-ui](http://localhost:8080/swagger-ui/index.html) (localhost on port 8080 /swagger-ui)