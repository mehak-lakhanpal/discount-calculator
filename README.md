# Spring Boot Discount Engine Project

This is a sample Java / Maven / Spring Boot (version 3.3.1) application that can be used to calculate discount over a list of products provided. I hope it helps you.

## How to Run the Application

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.store.discount_engine.DiscountEngineApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```
mvn spring-boot:run
```

##How to Run test cases

You can use below command to build the project and to run test cases and to generate test case report.

```
mvn clean install
```

You can find the code coverage report under folder target/site/index.html

## About the Service

The service is just a simple discount calculator service. It accepts the order and calculates discount on the basis of various parameters which are-

* If the user is an employee of the store, he gets a 30% discount.
* If the user is an affiliate of the store, he gets a 10% discount.
* If the user has been a customer for over 2 years, he gets a 5% discount.
* For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).
* The percentage based discounts do not apply on groceries.
* A user can get only one of the percentage based discounts on a bill.

## Assumptions

There are some assumptions which i took while creating this service are-

* As user will get only discount at a time on a bill. So If user is not an employee or affiliate or customer has a relationship with store over 2 years, Only then this discount - For every $100 on the bill, there would be a $ 5 discount , will be applied on bill.
* As it is mentioned in the problem statement that the program should only return the net amount to be paid,API is returning only the net amount as response, no other fields. Other fields will be added if required in future.
* Product List and Customer Object which contains his name , type etc is mandatory.
* Product's name, category, price and quantity is mandatory . Price and quantity of product should not be equals to 0.

Here is the endpoint you can call to calculate net amount to be paid after discount:

### Calculate Net amount on a bill after discount

In case of Success-

```
POST http://localhost:9867/discount
Accept: application/json
Content-Type: application/json

{
    "products": [
        {
            "name": "product1",
            "category": "books",
            "price": 100,
            "quantity": 1
        },
        {
            "name": "product2",
            "category": "grocery",
            "price": 100,
            "quantity": 1
        }
    ],
    "customer": {
        "name": "abc",
        "customerType": "CUSTOMER",
        "yearsWithStore": 3
    }
}

RESPONSE: HTTP 200 (OK)
{
	netAmount: 195.0
}
```
In case of Exception-

```
{
    "products": [
    ],
    "customer": {
        "name": "mehak",
        "customerType": "CUSTOMER",
        "yearsWithStore": 3
    }
}

RESPONSE: HTTP 200 (OK)
{
    "message": "Product List should not be empty"
}
```

## UML Diagram

![alt text](https://github.com/mehak-lakhanpal/discount-calculator/blob/main/UML_Diagram.jpeg?raw=true)

## How to Run Static Code Analysis Using Sonarlint

Install the plugin Sonarlint on IntelliJ IDE. Restart your IDE then right click on your project and click on Analyze with Sonarlint. This will show the sonar issues report for your whole project.


## Run Sonarqube using cmd

Use below command to run sonar analysis in your project using cmd

```
mvn clean verify sonar:sonar 
                   -Dsonar.projectKey=discount-calculator 
                   -Dsonar.projectName='discount-calculator' 
                   -Dsonar.host.url=http://localhost:9000
                   -Dsonar.token=sqp_ddf186e8d57b60b46ebbdadb9d9520bfcafeba49
```

## Sonar Analysis Report

![alt text](https://github.com/mehak-lakhanpal/discount-calculator/blob/main/SonarAnalysisReport.png?raw=true)


