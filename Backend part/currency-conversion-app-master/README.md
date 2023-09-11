## Description

This project is a web application that allows users to convert currencies from one to another 
using real-time exchange rates. Users can also compare different rates from various sources.


## Architecture

The architecture of this project is based on the Model-View-Controller (MVC) pattern. 
The model layer consists of the classes that represent the domain entities and the business 
logic of the application. The view layer consists of the web pages that display the user 
interface and the data to the users. The controller layer consists of the classes that handle 
the user requests and communicate with the model and view layers.

The following diagram shows the high-level architecture of this project:

![Screenshot 2023-08-29 163415](https://github.com/Bassiouni/currency-conversion-app/assets/142849115/1e33aeef-b39e-4e4d-a5b5-95e8af93477a)



## Technologies and Tools

---
- Java 20
- Maven
- Spring Boot
- Restful API
- Lombok
- Docker
- Postman
- Redis
- Swagger
- Deploy      
     - Render
          - https://concurrency-api.onrender.com/                     



## Features

1. Currency Conversion: Converting a given currency to another currency.

2. Currency Comparison: Compare between two currencies.

3. Favorite List: Allowing add currencies you use often to your favourites.

4. Dynamic chart: graphical representation of currencies rates



## API Contract

The API contract defines the format and structure of the requests and responses that 
are exchanged between the client and the server. The API contract follows the RESTful 
principles and uses JSON as the data format.

The following table summarizes the API endpoints and their parameters:

|           *Endpoint*                 |      *Method*      |      *Parameters*               |
|--------------------------------------|--------------------|---------------------------------|
|      getAvailableCurrencies          |      GET           |      No parameters              |     
|      getCurrencyConversion           |      GET           |      (current, target)          |
|      getCurrencyConversionWithAmount |      GET           |      (current, target, amount)  |
|      getCurrencyComparison           |      POST          |      (basecode, targetcodes)    |

#### Responses
---
- getAvailableCurrencies
  ```json
     {
       "status_code": 0,
       "status": "string",
       "message": "string",
       "data": {
         "result": "string",
         "base_code": "string",
         "target_codes": [
           "string"
         ],
         "conversion_rates": {
           "additionalProp1": 0,
           "additionalProp2": 0,
           "additionalProp3": 0
         }
       }
     }
  ```
-  getCurrencyConversion
   ```json
     {
    "status_code": 200,
    "status": "success",
    "message": "Currency list retrieved successfully",
    "data": [
        {
          "icon_url": "https://www.xe.com/static-images/egp.static.df88137050e409e79db3433d9c8b1493.svg",
          "name": "Egyptian Pound",
          "code": "EGP"
        },
        {
          "icon_url": "https://www.xe.com/static-images/usd.static.e8b657d1161a08a32415d284a8e1dc89.svg",
          "name": "United States Dollar",
          "code": "USD"
        },... ]
     }
     ```
- getCurrencyConversionWithAmount
  ```json
     {
       "status_code": 0,
       "status": "string",
       "message": "string",
       "data": {
         "base_code": "string",
         "target_code": "string",
         "conversion_rate": 0
       }
     }
  ```
- getCurrencyComparison
    ```json
     {
       "status_code": 0,
       "status": "string",
       "message": "string",
       "data": {
         "base_code": "string",
         "target_code": "string",
         "conversion_rate": 0,
         "conversion_result": 0
       }
     }
     ```






## Structure File

The structure file describes the organization and hierarchy of the files and directories in this project. The structure file follows the tree command output format.

The following is the structure file of this project:


.
├── currency-conversion-app-master


     ├── .idea
     
     │   ├── .gitignore
     
     │   ├── compiler.xml
     
     │   ├── encoding.xml
     
     │   ├── jarRepositories.xml
     
     │   ├── misc.xml
     
     │   └── workspace.xml

     
     ├── .mvn
     
     │   ├── wrapper
     
     │       ├── maven-wrapper.jar
     
     │       ├── maven-wrapper.properties
     
     ├── src
     
     │   ├── main
     
     │   │   ├── java
     
     │   │   │   ├── com
     
     │   │   │   │   └── banquemisr.currencyconversionapp
     
     │   │   │   │       └── client
     
     │   │   │   │           └──ExchangeRateAPIClient
     
     │   │   │   │       └── config
     
     │   │   │   │           └── CachingConfig
     
     │   │   │   │       └── dto    
     │   │   │   │           ├── ComparisonDTO     
     │   │   │   │           ├── CurrencyComparisonRequestBodyPOJO     
     │   │   │   │           ├── CurrencyConversionDTO     
     │   │   │   │           ├── CurrencyDTO     
     │   │   │   │           └── UnitCurrencyConversionDTO     
     │   │   │   │       └── entities     
     │   │   │   │           └── Response     
     │   │   │   │       └── exception     
     │   │   │   │           ├── BadEntryException     
     │   │   │   │           ├── CustomExceptionHandler    
     │   │   │   │           └── NotFoundException     
     │   │   │   │       └── props     
     │   │   │   │           └── AppProps     
     │   │   │   │       └── service     
     │   │   │   │           └── ExchangeRateService     
     │   │   │   │       └── validation     
     │   │   │   │           ├── AmountValidation     
     │   │   │   │           ├── CurrencyExistsValidation    
     │   │   │   │           └── validate     
     │   │   │   │       └── web.controllers   
     │   │   │   │           └── ExchangeRateController
     │   │   │   │       └── CurrencyConversionAppApplication
     │   │   └── resources
     │   │       └── application.yml
     │   └── test
     │       └── java
     │           └── com
     │               └── banquemisr.currencyconversionapp
     │                   └── service 
     │                       └── ExchangeRateServiceTest
     |                   └── CurrencyConversionAppApplicationTests
     │
     ├── .env
     ├── .gitignore
     ├── docker-compose.yml
     ├── Dockefile
     ├── mvnw
     ├── mvnw.xml
     ├── pom.xml
     ├── README.md
