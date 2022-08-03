This package contains all utility functions and Factory classes required to trigger test suite.

### Directory Structure:

| Files/Directory | Description                                                                                                                                                                                                                          |
|-----------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| BrowserFactory  | Based on singleton design pattern, having method which checks for type of browser and grid value user provided to run test suite on and return appropriate WebDriver                                                                 |
| DriverFactory   | Based on singleton design pattern, it instantiates a thread safe Webdriver instance which is needed to support parallel execution,                                                                                                   |
| ExtentFactory   | Based on singleton design pattern, it instantiates a thread safe ExtentTest instance needed to create, log and remove test in extent report.                                                                                         |
| ConfigReader    | Baased on singleton design pattern, this helper file will read all system properties and return property value in when method defined is called. Any user defined property set in Config.properties can also be retrieved usin this. |
| YamlReader      | This helper file is required to read any yaml file in which data is defined in a defined pattern Parent->Child. In framework, it is used to read objectRepository and TestData.                                                      |
| CommonUtil      | This file contains all common helper functions required to achieve desired functionality                                                                                                                                             |
| ExtentSetup     | This file is used to instantiate extent report instance on test suite start                                                                                                                                                          |
