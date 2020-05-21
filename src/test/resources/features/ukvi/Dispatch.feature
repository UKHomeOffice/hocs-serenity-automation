Feature: Dispatch

  Background:
    Given I am user "AUTOMATION_USER"
    And I create a "UKVI" case and move it to the "Dispatch" stage
    And I load and claim the current case

  Scenario Outline: User triggers error message to be displayed at Dispatch
    And the user triggers the "<errorType>" error message at Dispatch by not entering the correct information
    Then then the "<errorType>" error message should be displayed at Dispatch
    Examples:
    |errorType       |
    |Actions Required|
    |Outbound Channel|
    |Dispatched Date |