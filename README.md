# hocs-serenity-automation

This automated test pack uses the [Serenity BDD](http://www.thucydides.info/docs/serenity/) framework with Cucumber. If you are contributing to this framework, please try to use code from the Serenity library before using other code libraries or custom solutions. Above all, keep your code as simple as possible. If you have to write comments to explain what your code is doing, then it needs to be made more readable.

For more information, please consult the project [Wiki](https://github.com/UKHomeOffice/hocs-serenity-automation/wiki).


## Requirements

* [JDK 10](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or above
* Sign in to GitHub with your homeoffice account
* Clone the repository here: https://github.com/UKHomeOffice/hocs-serenity-automation
* Make sure settings.xml is not present within the .m2 folder
* Ensure that you are connected to the Kube VPN profile
* You will need to create a test run configuration template in order to run a feature file/scenario. Please provide the following in the template:
    * Name: Template
    * Main class: io.cucumber.core.cli.Main
    * Glue: com.hocs.test.glue
* Ensure that the Lombok plugin is installed
* Before running any test, ensure that you have the correct chromdriver installed:
    * Before downloading chromdriver check your Chrome version, go to the 3 dots -> Help ->  About Google Chrome
    * Download the appropriate chromedriver version that is compatible with your Chrome version from here: https://googlechromelabs.github.io/chrome-for-testing/
    * Download the chromedriver Mac-x64.zip file, example: https://storage.googleapis.com/chrome-for-testing-public/151.0.7922.
      173/mac-x64/chromedriver-mac-x64.zip
    * You will need to give chromedriver executable permission so in terminal. Open a terminal window, navigate to where your download chromdriver exe
      is located and use the following: xattr -d com.apple.quarantine chromedriver
    * Then copy the chromedriver from your downloads folder to hocs-serenity-automation/src/test/resources/webdriver/mac and overwrite the 
      existing one.

## Running the Tests Locally

There a two ways to run a test

First:
* After completing the requirements above, you should now be able to execute scenarios individually. Navigate 


Second
* After completing the requirements above, the tests should now be executable both in intellij and maven, here is an example maven command: mvn clean 
  verify
  "-Dcucumber.filter.tags=@@WCSRegression"
  -Dchrome.switches=--headless -Denvironment=QA

## Contribution Guidelines

Contributions to this pack are always welcome. Please feel free to scrutinise the code within this pack and contribute, or feed improvements back to us.

Any code that is to be added to the `develop` branch must undergo code review. Prior to submitting your code for review, please ensure that you have tested your changes and you've run Inspect Code and Code Cleanup (`Ctrl + Alt + L`) on the files you've changed.

If you identify improvement opportunities that are unreleased to your specific changes, please raise a [new issue on GitHub](https://github.com/ministryofjustice/ndelius-serenity-automation/issues/new) so it can be picked up from the backlog. Remember, if it takes longer to raise a ticket than it does to make the improvement, make the improvement.
 