Feature: Dispatch

  Background:
    Given I log in to DECS
    And I create a "MPAM" case and move it to the "Awaiting Dispatch" stage
    And I load the current case

  Scenario Outline: User triggers error message to be displayed at Dispatch
    And the user triggers the "<errorType>" error message at Dispatch by not entering the correct information
    Then then the "<errorType>" error message should be displayed at Dispatch
    Examples:
    |errorType       |
    |Response Channel|
    |Dispatched Date |