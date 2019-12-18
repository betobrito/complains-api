# complains-api

-   Project designed to help consumers get a review of some location. Getting a brief description of it and possible grievance complaints.

-   Continuous Integration: [![CircleCI](https://circleci.com/gh/betobrito/complains-api/tree/master.svg?style=svg)](https://circleci.com/gh/betobrito/complains-api/tree/master)

# Endpoints
Below are some examples of using the application using a request tool, In my case the Postman was used.

<b>[GET] Functionality to query a complains.</b>

    https://complaints.herokuapp.com/complaint/5df9737a07412d032c09dac4

<b>[POST] Functionality of creating complaints.</b>

    https://complaints.herokuapp.com/complaint

Sample body for performing the above functionality post request
    
    {
      "company": "string",
      "description": "string",
      "locale": "string",
      "title": "string"
    }

<b>[PUT] Complaint Editing Functionality</b>

    https://complaints.herokuapp.com/complaint/5df9750e07412d032c09dac5

Sample body for performing the above functionality post request

    {
      "title": "string",
      "description": "stringwdsdsdsd",
      "locale": "string",
      "company": "string"
    }
    
<b>[POST] Functionality of listing complaints by locale.</b>

    https://complaints.herokuapp.com/complaint/locale

Sample body for performing the above functionality post request
    
    {
      "locale": "string"
    }

<b>[POST] Functionality of listing complaints by locale and company.</b>

    https://complaints.herokuapp.com/complaint/locale/company

Sample body for performing the above functionality post request
    
    {
      "locale": "string"
    }

## Development

Stack:

- Spring Boot + Family
- MongoDB
- JUnit + Cucumber (Unit and integration tests)

To run tests or run the application you need to have raised the mongodb image to
that the project can register and be available for use:

    docker-compose -f src/main/docker/complains.yml up -d
    
To download project dependencies just run the command below:

    mvn clean package -DskipTests=true
    
Command to run the tests, remembering that it should be executed in the project root folder, where the pom.xml file is located.

    mvn integration-test
