@CasePrivateOffice
Feature: PrivateOffice

  Background:
    Given I am user "AUTOMATION_USER"
    And I create a "UKVI" case and move it to the "Private Office" stage
    And I load and claim the current case

  Scenario Outline: User triggers error message to be displayed at Private Office
    And the user triggers the "<errorType>" error message at Private Office by not entering the correct information
    Then then the "<errorType>" error message should be displayed at Private Office
    Examples:
      |errorType       |
      |Response Channel|
      |Dispatched Date |