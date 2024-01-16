# Checkout Service
## Problem Statement
Online marketplace, here is a sample of some of the products available on site:

|   Product code	|   Name	|   Price	| 
|---	|---	|---
|  001 	|  Travel Card Holder 	|   £9.25	| 
|   002	|   Personalised cufflinks	|  £45.00 	|
|  003 	|   Kids T-shirt	|   £19.95	|  


Marketing team want to offer promotions as an incentive for customers to purchase these items.
If you spend over £60, then you get 10% off your purchase
If you buy 2 or more travel card holders then the price drops to £8.50.

Our check-out can scan items in any order, and because our promotions will change, it needs to be flexible regarding our promotional rules.

## Design/Deployment Decisions
##### Design Considerations
 - Chain of Responsibility Design Pattern
   - We need to apply multiple discounts, under given circumstances, we might want to consider the Chain of Responsibility pattern. 
     In a nutshell, we pass the information we want to process into the first promotion processor, and it decides from there whether to pass it on 
     to further promotion processors before returning the result. 
     This way we can change the structure and sequence of the processors without ever changing the calling code.
     
 - BigDecimal for Floating Point Precision
   - There are many monetary values calculation in the financial or e-commerce application and BigDecimal is 
    most suitable for monetary calculations for accuracy.
 - Setting Priority of Promotions
   - This is also very flexible because sometimes if we don't set the priority we end up giving more discounts to the customer
    for e.g. If we applied product promotion first and then cart total became less than 60 then cart total promotions will not be applied
     But we applied promotions in reverse then both the promotions will be applied.
 - Repository Pattern for storing products data
   - This is kind of similar to spring repositories, Here I have created repository but in real application we might have pointer 
    to the product database configuration and we interact with that via ORM tools.
 - Exception Handling/Error Logging
   - Put possible exception handling and error logging to root cause issues.
 - Configurable/Flexible Promotions Rules
   - Product Promotion : We can create product promotion on any product code with any number of items with that product code in the cart
    - Cart Total Promotion : We can create promotions on the basis of different cart totals any time and feed into the system
 - Avoiding Duplication of Objects in Checkout, Introduced count for each product in cart
   - Since products in the checkout can come in any order and we could have created object for each of them but to avoid that introduced count for each product code
    and stored in map.
 - Dependency Injection
   - Injected product repository and promotions runner as dependency to checkout system
 - Tests Driven Development
   - code coverage ~90%
##### Deployment Considerations
 - Dockerized Deployment
 - Uber Jar

## Technologies Used
* Java8
* Maven
* Docker

## Prerequisites
* Java 8
* Maven 3.6
* Docker

## Run

##### Set a environment variable
This variable sets the absolute location of product.json which has all of our products in the inventory.

Assumption : Data Provided in JSON format is correct and has all required three fields

For reference : Look at [sample product json file](src/main/resources/product.json)

```cd checkout-service```

```export PRODUCT_DATA_LOCATION= $(pwd)/src/main/resources/product.json```

If we create our own custom product.json file then we can specify absolute location of that custom file as well.

##### Go Inside the Project
```cd checkout-service```
##### Build the Project
```mvn clean compile assembly:single```
##### Run the Solution
```java -jar target/checkout-service-1.0-SNAPSHOT-jar-with-dependencies.jar 1 2 3```

Note - Input is product codes separated by space present in the cart.
For e.g. when We pass ```1 2 3``` then we are passing items 1,2 and 3 as in the cart.
We assume here Input will be passed as series of integer spaced and they are present in our product.json
