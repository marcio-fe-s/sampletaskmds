# README #

This project contains the coding assessment task for Java back-end developers.

## Instructions ##

Build a rest service that retrieves available cars from the provided service (that simulates a "car rental company") and returns the result in json format
according to the following examples.

#### Successful response: ####

```
{
  result: success,
  offer: {
    available: 2,
    vehicles: [
      {
         brand: volvo,
         category: economy,
         baseprice: 10.0
      },
      {
        brand: mercedes,
        category: A,
        baseprice: 30.5
      }
   }
  ],
  timestamp: 2019-05-19T12:34:56.789
}
```

#### Error response: ####

```
{
  result: error,
  error: {
    code: 1,
    description: vendor service not available
  }
}
```

### Further requirements ###
* The list of vehicles should be sorted ascending by base price.
* There is another rest service that simulates the car rental company's search interface, which returns the raw list of data.
* You need to extract the necessary information for the raw data and provide the result in the requested format.

The search interface can be downloaded from Docker hub (docker pull csorbazoli/samplevendor) and accessed on [http://localhost:9210/search]
It is returning one of the following answers (random):

* successful response:
    * list of currently available cars (from 0 to 6)
* error responses: an error code and a message
    * 400 - illegal access
    * 500 - system failure
    * 600 - system maintenance

Your task is to implement the business logic of the rest service, using the partially implemented Spring Boot application. Clone this repository and commit your solution to it.
The service is exposed on http://localhost:9500/avail, and provides the data described above in JSON format.
If the sample vendor service is not available, then it should return error code 1, with the description "vendor service not available"

### Contribution guidelines ###

* Commit your solution to this git repository.
* Copy the code of SimpleCypherTool here also.
* The initial version of the solution already contains some unit tests. Make sure, that you extend these tests for the newly implemented code parts.
* It is recommended to use a static code analyser (e.g. SonarLint) and check the code coverage as well.

### Who do I talk to? ###

* Repo owner or admin
