# Implementation

### Components
The project consists of -
  1. Maven
  2. Java 1.8
  3. Selenium 3.141.59
  4. Cucumber 6.1.1
  5. TestNG 6.9.4
  
  
### The project structure is as - 
   #### src/main/java : page classes, base setup, utilities
   #### src/main/resources : expected/retrieved data, config/properties files, drivers 
   #### src/test/java : step definition, test runner
   #### src/test/resources : feature files
   

## src/main/java
  ### SearchPage class
  This class consists of all the web-elements, and the corresponding functions that can be performed through the page. 'Page factory' is used to initialize the elements at once using the 'PageFactory.initElements()' method.
  Page class consists of 3 sections - 
    1. All the strings/texts available on the page
    2. All the web elements available on the page with their locators/identifiers
    3. Functionalities(methods) - that can be performed using the page.
  
  ### Setup 
    It is used to initialize the WebDriver(Chromedriver) for this particular implemntation. Initialize method can be called from the cucumber @Before method. Once called, initialize will start the driver and pass the instance to the particular class/method from which it is called.
    
   ### Utilities
    This class contains all the utility methods that will be used to execute the test scripts e.g. explicit waits, write data to file, compare files, screenshot etc.
    
 ## src/main/resources
  ### driver
    this directory is one place storage for the 'browser drivers' required to execute the automation. Location of this directory is mentioned in the config.properties file.
    
  ### Basedata/Retrieveddata
    base data : the expected data to compare the actual retrieved data
    retrieved data : the actual data retrieved during execution
    
  ### config
     config.properties file containing all the data required to execute the project
    
## src/test/java
  ### step-definition
    1. All the step-definition files are organised and placed under this package. A step-definition provides the implementation steps for the cucumber 'feature' files. 
    2. One feature with exact wordings can be re-used by multiple feature files.
    3. Pagination verification for input search text is completed
    
   ### test-runner
    This 'RunCucumberTest' class contains the code for cucumber-junit test-runner. This runner class is called first and it contains multiple options using which the execution is started. The options that can be mentioned in the runner class are 
    1. glue : step-definition root directory name 
    2. features : location of root directory for feature files
    3. tags : name of tags that should be executed/excluded
    4. plugin: majorly used for specifying the plugins used especially for reporting
    
## src/test/resources
  ### Feature file
    Written in Gherkin language, it contains the test scenarios written using the cucumber keywords (Given, when, then).
    I have added @Regression Tag in the feature file - 
      1. feature level : this tag can be used to execute all the scenarios in the feature file
      
    I have also added scenario outline and modified few steps and Example table to keep the steps generic and re-usable.
    With increasing test scenarios, 'Background' keyword can be used in feature files to specify the common steps (usually login/setup) which are common for all scenarios.
    
    ### More scenarios
      1. Different combination of inputs to verify the pagination functionality
      2. search criteria using the 'Refine your search results' option on the page
    
## Reporting
  Cucumber report are generated - 
    1. cucumber report : under 'target/site/cucumber-reports.html' directory
    
  
 ## Execution
  Execution can be started in two ways -
    1.  using testng.xml
    2.  using maven
    
   The cucumber test runner file starts the execution. It fetches the feature file and starts the execution.
   Each step in the feature file executes the corresponding step definition.
   After each step, the screenshot is taken using the takeScreenshot method of TestUtils class. The screenshot is stored in the {$project_dir}/screenshot/folder.
   
   The data - Logistics, coordinates and site-concerns is stored in the file : /src/main/resources/RetrievedData. This data is compared with pre-stored data in     /src/main/resources/BaseData/testDataExpected.csv file.
   
   The result report is generated at - target/site/cucumber-reports.html
    

