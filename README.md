# AiraloAPITests

Welcome to the **AiraloAPITests** repository! This project contains API test cases designed to validate the functionality of Airalo platform's APIs. The tests are implemented using **RestAssured**, **JUnit**, and **Maven**.

## Table of Contents
- [Project Structure](#project-structure)
- [Setup Instructions](#setup-instructions)
- [Running the Tests](#running-the-tests)

## Project Structure
### Folder Purpose:

- **`src/test/java/com/airalo/tests/`**: Contains JUnit test classes where the actual API tests are written.
  - Each test class corresponds to an API endpoint, and each test is written as a method inside the corresponding class.
 
- **`src/test/java/com/airalo/config/`**: Contains the configutation required to run the tests

- - **`src/test/java/com/airalo/utils/`**: Contains the utilities required for the tests e.g. token management

- **`pom.xml`**: This file contains the dependencies required for the project, including **RestAssured**, **JUnit**, and **Maven Surefire** for running tests.

---

## Setup Instructions

Follow these steps to set up and run the tests:

### Prerequisites:

Before running the tests, ensure the following software is installed:

- **Java** (preferably JDK 8 or higher)
- **Maven** (for building the project and managing dependencies)

### Steps to Set Up the Project:

1. **Clone the Repository**:
   ```bash
git clone https://github.com/SonalPawar2509/AiraloAPITests.git
cd AiraloAPITests

2. Install Dependencies: Maven will automatically handle the dependencies by reading the pom.xml file. Run the following command to download all required dependencies:
```bash
mvn clean install

3. Run Tests Using Maven:
```bash
mvn test

4. Run Specific Test Class:
```bash
mvn -Dtest=ApiAutomationTest test







