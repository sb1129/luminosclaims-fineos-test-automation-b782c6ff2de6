# README #

### Summary
> This is automation framework, implemented using Java, Selenium/Webdriver, TestNG & Maven. 

### Confluence Pages
Visit the Confluence page, and its sub-pages.
* [How to get Started with Selenium Automation](https://gbtrial.atlassian.net/wiki/spaces/IDTS/pages/717816444/How+to+get+started+with+Selenium+Automation) 
 
* [Configuring IntelliJ IDEA to run Automation Tests](https://gbtrial.atlassian.net/wiki/spaces/IDTS/pages/722534940/Configuring+IntelliJ+IDEA+to+run+Automation+Tests)

* [Selenium Automation Help](https://gbtrial.atlassian.net/wiki/spaces/IDTS/pages/724107382/Selenium+Automation+Help)

* [Using the Framework](https://gbtrial.atlassian.net/wiki/spaces/IDTS/pages/724664539/Using+the+Framework)

### Prerequisite: ###
* Java
* Maven
* Selenium/WebDriver
* TestNg
* Browsers (Firefox, Chrome, IE)
* Respective Browser drivers
* Intellij or Eclipse

### How do I get set up? ###
Below is a quick-and dirty set-up guide. There is more in-depth configuration information on the [Confluence Page](https://gbtrial.atlassian.net/wiki/spaces/IDTS/pages/717816444/How+to+get+started+with+Selenium+Automation) and its [sub-page](https://gbtrial.atlassian.net/wiki/spaces/IDTS/pages/722534940/Configuring+IntelliJ+IDEA+to+run+Automation+Tests)
* Clone the Bitbucket repository
* Open IntelliJ
  * _File -> New -> Project from Existing Sources..._ -> Navigate to the folder with the pom.xml and select.
  * Navigate to: _File -> Settings -> Build, Execution, Deployment -> Build Tools -> Maven -> Runner_ and check: _Delegate IDE build/run actions to Maven_
  * Open the Maven Widget 
    * Select the appropriate 'env-', 'mode-' and 'os-windows' profiles.
    * Enable 'Skip Tests mode' (Round blue icon with a lightning bolt)
* Tests may be run inside the IntelliJ IDE by right clicking on the Class (or Package) and selecting 'Run'
* Tests may also be run from the command line using Maven. Eg. To execute tests in the AU-GI environment from a Windows operating system:

```
  mvn clean test -Penv-au-gi,os-windows,mode-smoke
```

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines
