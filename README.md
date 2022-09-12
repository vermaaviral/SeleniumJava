# SeleniumJava

### Introduction:

This repository includes a test automation framework using Selenium with java. It follows a PageObject Model design and based on TestNg framework. For reporting, Extent Report is utilised
which generates a report at the end of test suite excluding the retried test cases. Logging and screenshot on failures has also been included as part of report generated. Also, Retry mechanism is implemented to retry failed TC couple of times.

### Prerequisites:
1. Java version 11 or above
2. Maven 3.8.6 or above
3. Git

### Repository Structure:

| Files/Directory             | Description                                                                                                                                                 |
|-----------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|
| src/main/java/com/listeners | It contains listener to implement Retry Analyzer and Extent Reporting                                                                                       |
| src/main/java/com/Pages     | For POM design pattern, this package contains all pages class based on AUT                                                                                  |
| src/main/java/com/Util      | This package contains all utility functions and Factory classes required to trigger test suite.                                                             |
| src/resources/              | This directory contains all supporting files requires to run test suites                                                                                    |
| src/test/java/testcases     | This package contains TC segregated based on POM design. Each TC is marked with appropriate tags.                                                           |
| src/test-output             | This directory will get created at the end of execution if not present which contains directories with Timestamp of execution having reports and screenshot |


### RunTime Environment Variables:

| Variable Name | Description                                                         | Default Value  |
|---------------|---------------------------------------------------------------------|----------------|
| selenium_grid | If test suite need to execute on grid, set this to true             | false          |
| selenium_hub  | If using grid, then this variable needs to be set                   | http://localhost:4444/wd/hub |
| env           | Test Data will be picked from file based on environment             | test           |
| groups        | Tests tagged with matching value of this variable will get executed | Regression     |
| browser       | Browser on which user desired to run test suite                     | chrome         |
| threadCount | To run TCs in parallel, provide value greater than 1 | 1 |

### Execution Commands:

* Trigger test suite with default properties
```
mvn clean test
```
* Trigger test suite with selenium grid with parallel execution with 5 threads
```
mvn -DthreadCount=5 -Dselenium_grid=true -Dselenium_hub=http://<DNS>:<port> clean test
```
* Trigger test suite based on multiple tags
```
mvn -Dgroups=Regression,Smoke clean test
```
* Trigger test suite execution on Firefox browser
```
mvn -browser=Firefox clean test
```