# Selenium Java Test Automation Architecture Magento
by Kir Kan

UI Test Automation Architecture using Java Cucumber and Selenium WebDriver  



## 🏁Installation Steps / QuickStart Guide!🏁 🤗

In order to use the framework:

1. Fork the repository.

2. Clone, i.e, download your copy of the repository to your local machine.

3. Use IntelliJ IDEA to run your desired tests.
You can use  [*CRunner*](src/test/java/com/test_project/runner/CRunner.java) to run tests with tags by specifying tags in `tags = "@tagname"` and  [*FailedTestRunner*](src/test/java/com/test_project/runner/FailedTestRunner.java) to rerun failed tests!
Alternatively, you can use the terminal to run the tests, for example `mvn clean test` to run all tests, or `mvn -Dsurefire.rerunFailingTestsCount=4 clean test` to rerun failing tests automatically or `mvn -Dsurefire.rerunFailingTestsCount=4 clean test -Dbrowser=chrome -Dcucumber.filter.tags=@regression` for running specific tags / browser and rerunning failing tests automatically.
5. To see the report, go to the `target/cucumer` folder in the project root and then open any `.html` file in the browser.
## Project Structure🧐
```bash
├───.idea
└───src
    └───test
        ├───java
        │   └───com
        │       └───test_project
        │           ├───pages
        │           ├───runner
        │           ├───steps
        │           └───utility
        └───resources
            ├───features
            └───testData
```
## Languages and Frameworks🧐

The project uses the following:

- *[Java 11]
- *[Selenium WebDriver]
- *[Cucumber]
- *[cucumber reporting-plugin]
- *[Junit]
- *[apache.poi]
- *[bonigarcia webdrivermanager]
- *[maven-surefire-plugin]
- *[IntelliJ IDEA]


## Basic Usage🧐

- ### This project utilizes Maven⭐

  Dependency Management: Dependencies are defined in the `pom.xml` file.
  Build Automation: The build process is configured in the `pom.xml` file.
  Test Execution: Maven commands like `mvn clean test` can be used to run tests.

- ### Configuration⭐
  The project uses a [*config.properties*](config.properties) file to manage global configurations such as browser type and base url.

    To add a new property, register a new entry in this file:
      ```
      key=value
      ```

  use `getProperty()` method in [*ConfigurationReader*](src/test/java/com/test_project/utility/ConfigurationReader.java) class to read [*config.properties*](config.properties)
    
- ### Cucumber Integration⭐

    [Cucumber](https://cucumber.io/) is utilized for behavior-driven development (BDD) testing. Tests are written in feature files (Gherkin language) located in `src/test/resources/features`, and step definitions map Gherkin steps to Java code.

- ### Page Objects and Page Component Objects⭐
  The project uses Page Object Model Design
  `src/test/java/com/test_project/pages`
  Page Objects represent individual web pages, encapsulating their elements and related behaviors. This design promotes code reusability and easier maintenance.
- ### Test Runner Configuration⭐

    The [*CRunner*](src/test/java/com/test_project/runner/CRunner.java) class configures Cucumber options, specifying the location of feature files and step definitions for test execution.

- ###  Cucumber Reports⭐

    [Cucumber Reporting](https://cucumber.io/docs/cucumber/reporting/) enhances test result visualization.

    Cucumber reports are generated in HTML format and can be found in the `target/cucumber` directory after test execution.

  
- ### Test Data⭐
  The project uses *xlsx* file `src/test/resources/testData/userMockTestData.xlsx` to store test data and apache-poi to retrieve the data using [*ExcelUtil*](src/test/java/com/test_project/utility/ExcelUtil.java) utility class
    

- ### Browsers⭐
  The project contains the implementation of the `Chrome`,`Edge` and `Firefox` browsers. 





