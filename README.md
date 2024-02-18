# Technical description :
* Usage of spring boot version 2.7.18 and postgres.
* Usage of spring starter web.
* I tried to use TDD approach so i design test after that I develop the use case unfortunately the time was not enough to use TDD in the last use case, so it is not tested.
* I used DTO projection when fetching from the database to optimize the performance.

# Ideas of improvement :
* First Improve the queries in the statistics use case.
* If it's a high throughput application using virtual threads and GraalVM may be beneficial.
* Add spring security, unfortunately I didn't have the time to implement it and to implement Kafka's producers and consumers.

# How to run and prerequisites: 
## there are two ways of using the application : 
* Using docker compose - if you have docker compose installed : (running : docker compose up)
* Running the command mvnw spring-boot:run or running the application in the ide, in this case you need to have a running postgres instance locally and if necessary change the postgres configuration in application.properties.

# Why and how to integrate Hazelcast : 
## I have never used hazelcast, but I read some articles about it to prepare for this challenge so from what I have learned this is my response : 
* Using Hazelcast for this project would be beneficial because it provides distributed caching and in-memory data grid capabilities, enabling fast access to ingredient information and order processing. However, if the project requires complex data processing or extensive integration with other systems, Hazelcast may not be the best choice due to its simplicity and focus on caching rather than advanced functionalities.