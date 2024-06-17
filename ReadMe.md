# Introduction

Note - Even though this is simple API to add customer in DB and push message to queue,I have used ports and adapters design pattern to achieve framework independence incase of migration to different framework(Quarkus).


# Getting Started

1. Prerequisites
2. configuration
3. Running

# Prerequisites

* Maven - This project is built using maven ,3.9.4 and above should do.
* Java - Java jdk17 and above is required to run this project.
* Intellij - Though optional, good to have Intellij.
* docker with postgres image running on default container with db assessment and user name and password configured as mentioned in application.yml - example docker run --name posttest -d -p 5432:5432 -e POSTGRES_PASSWORD=fred postgres:alpine

* kafka running on default port. 
# Build and Test

* run mvn clean install

# Contribute

     Application layer 
         

       * Utilize classes of util package.

       * Use CustomPageableRequest instead of pageable in application and domain layers.


       * Use RequestValidator to validate request by adding API wise methods. 




     Unit Testing

         * Use modules.Base class , extend this class in TestClass, use request creation method and add all the common methods here.







