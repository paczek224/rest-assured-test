# RestAssured Tests

A clean and extensible API test automation framework built with **Java 21**, **Rest Assured**, **JUnit 5**, **AssertJ**, **Lombok**, and **Allure**.

This project is designed as a lightweight foundation for REST API testing with a strong focus on readability, reusability, and maintainable test architecture.

## Overview

The framework provides a structured approach to testing REST services by separating responsibilities into dedicated layers such as:

- **models** for request and response objects
- **paths** for endpoint definitions
- **specifications** for shared Rest Assured configuration
- **controllers** for low-level HTTP communication
- **actions** for reusable business-level API operations
- **utilities** for serialization, response mapping, assertions, and test data generation

It currently includes example tests for creating and deleting resources against a sample REST API.

## Tech Stack

- **Java 21**
- **Maven**
- **Rest Assured**
- **JUnit 5**
- **AssertJ**
- **Lombok**
- **Allure Report**
- **Checkstyle**

## Key Features

- reusable API action layer
- centralized request specification handling
- object mapping and JSON serialization utilities
- custom response assertion helpers
- random test data generation for payloads
- environment-based service endpoint configuration
- Allure reporting support
- Checkstyle validation in the Maven lifecycle

## Example Test Coverage

The current project demonstrates scenarios such as:

- retrieving existing resources
- creating a new post
- deleting a post
- validating HTTP status codes
- validating response body fields
- verifying API state after write operations

## Configuration

### Active environment

The active environment is defined in:

properties src/test/resources/environmentProvider.properties

Example:

properties environment=dev

### Service base URLs

Service endpoints are configured in:
properties src/test/resources/serviceProvider.properties

Example:

properties dev.resources=[https://jsonplaceholder.typicode.com](https://jsonplaceholder.typicode.com) 
test.resources=[https://test.jsonplaceholder.typicode.com](https://test.jsonplaceholder.typicode.com)

### Allure results directory

Configured in:
properties src/test/resources/allure.properties
properties allure.results.directory=target/allure-results



## Getting Started

### Prerequisites

Before running the project, make sure the following tools are installed:

- **Java 25**
- **Maven 3.9+**

### Clone the repository

bash git clone 

### Run all tests
bash mvn clean test


## Reporting

### Generate Allure report
bash mvn allure:report

### Open Allure report locally
bash mvn allure:serve

## Code Quality

The project uses **Checkstyle** as part of the Maven build lifecycle.

To run validation:

bash mvn validate


## Architecture Approach

This framework follows a layered design to keep tests small, readable, and easy to expand.

### Models

Model classes represent API payloads and mapped responses.

### Paths

Path classes centralize endpoint definitions and make requests easier to maintain when API routes change.

### Specs

Specification classes are responsible for shared Rest Assured configuration such as:

- base URI
- object mapper configuration
- filters
- logging behavior

### Controllers

Controllers encapsulate low-level HTTP communication and act as a transport layer.

### Actions

Actions expose reusable business-oriented operations that tests can call directly, which keeps test code focused on behavior instead of request mechanics.

### Utilities

Utility classes help with:

- JSON serialization
- response mapping
- response assertions
- endpoint resolution
- random test data generation

## Why This Structure

This project structure helps achieve the following goals:

- reduce duplication in test code
- separate HTTP details from test scenarios
- improve readability of automated tests
- make the framework easier to scale with more endpoints and test cases
- keep assertions expressive and consistent

In short: fewer repeated lines, fewer future headaches, and fewer “why is this request built here again?” moments.

## Example Workflow

A typical automated API test in this framework follows these steps:

1. prepare test data
2. call the API through the actions layer
3. assert response status and body
4. optionally perform a follow-up request for verification

This makes test cases straightforward and close to business intent.

## Maven Plugins Used

The project is configured with Maven plugins for:

- **Surefire** for test execution
- **Compiler Plugin** for Java 25 compilation and Lombok annotation processing
- **Allure Maven Plugin** for reporting
- **Checkstyle Plugin** for code quality validation

## Use Case

This repository can serve as:

- a starter framework for API test automation
- a learning project for Rest Assured architecture
- a portfolio project for QA Automation Engineers
- a base template for extending automated REST API coverage

## License

This project is intended for educational, demonstration, and framework development purposes.