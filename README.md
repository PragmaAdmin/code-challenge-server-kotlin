# Pragmateam code challenge server (Kotlin)

Please refer to the provided document for the code challenge requirements.

## Framework & languages

This project uses

* SpringBoot / Kotlin / Java 11

## Available scripts

- `./gradlew bootRun` - Start the server (Port 8081)

## Relevant information about my solution

### Highlights of your improvements (tests and others you choose)
- In order to ensure the current endpoint behaviors I created 2 type of tests.
  - Unit Test;
  - Integration Test (using MockMvc).
- I created a new endpoint "/" to be used. This new endpoint will return the list with all products instead of just one temperature.
  - The logic of to join the product and the temperature were being doing in the front end;
  - In my opinion the rules to define the status should move to backend;  
  - Moreover, the front end call the service once for each product is too heavy;
  - I deprecated the other endpoint.
- When I created the new endpoint I used the pattern MVC in order separation between the layers
  - The idea is each layers be responsible for one single thing;
  - The repository is responsible for the iteration/communication with others API;
  - The service is responsible for implementation all business/rules;
  - The controller is the layer responsible for receive the request and send the response (Web layer)

### What would you improve next if you had more time?
- I would like to change the old endpoint /{id} to follow the pattern or remove this endpoint;
- We still have some Models that are shared between the layers
  - Each layer should have your own model;
  - The inner layers cannot have access to the external layer;
- I would to include some libraries to help the development
  - MapStruct or similar to create automatically map between two java beans;
  - Use WebClient or FeignClient instead of RestTemplate to perform the rest calls;
  - Create a Generic Error Handle we don't have any treatment to errors that can happen;
- Maybe we can add a cache in the endpoint
  - Instead of call the API every time we may keep the result for few minutes
- I created a structure enum called Product.
  - Product should be a table in the database or another microservice
  - This structure is the responsible for keep all products with your details (id, name, temperature)
- I think we should improve more the test
  - We could create test e2e using Cucumber in order to ensure that the api are working properly with the others apis;
    - I created it using the MockMvc, but I think that use other tools is better to create e2e;
  - We also could create performance tests (using jmeter, gatling or k6);
- We also could add static tests (SonarQube)
- We could use properties instead of have some information in hard coding.
  - The temperature url is hard coding
    
### Questions you would ask and your own answers and assumptions
- Could we create a new endpoint instead of fix the old endpoint?
  - Yes, we can change the endpoint;
- Should we remove the old endpoint (/temperature/{id})
  - Yes, after we ensure that the new endpoint is working properly. For now on we can deprecate that.
- Should we include a cache in the endpoint? I don't think we need to perform a request to get the temperature every request.
  - We can create a technical debit to see it in another opportunity;
- The information about the product (id, name, temperatureMinimum, temperatureMaximum) should be a table or another microservice
  - We can change it after if we have more Product or if we need to change the information
- Can we get all temperatures at once?
  - Currently, we don't have a service with this functionality

### Explanations of decisions or the approach you took
- I decided to create a new endpoint that would return all product with the status that the front end needs.
  - I did it because I think that it's a rules and should be developed in the backend;
  - The front end don't need to create rules in order to know the status (too low, too high, all good);
  - If the backend do this job we front end don't need to do too much request and the performance is much better;
- I decided to create an enum Product with the product information
  - I think it's easy to understand what's going on and make changes;
- I decided to create more layers
  - I think the code is more organized, but we could use other pattern like clean architecture;
- I could create a test e2e easily with MockMvc, but I think this test should be created using an external tools
  - Use cucumber, robots or newman is a better solution to create e2e. And we can test the app deployed.

### Any other notes you feel relevant to evaluate your test improvements.
- I created a test e2e (TemperatureControllerE2eTests) but I don't like this approach
  - With this test we have all Test pyramid implemented (unit test, integration test and e2e)
  - In my opinion use cucumber, robots or newman is a better solution to create e2e. And we can test the app deployed;
- Coverage 100% 