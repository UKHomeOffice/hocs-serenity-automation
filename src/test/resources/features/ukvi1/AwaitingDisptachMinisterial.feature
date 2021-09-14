@AwaitingDispatchMinisterial @UKVI
Feature: Awaiting Dispatch (Ministerial)

  Background:
    Given I am logged into "CS" as user "UKVI_USER"
    And I create a "MPAM" case and move it to the "Awaiting Dispatch (Ministerial)" stage
    And I load and claim the current case

  @UKVIWorkflow @UKVIRegression1
  Scenario: User completes the Awaiting Dispatch (Ministerial) stage and closes the case
    When I enter the required dispatch information and confirm the closure of the case
    Then the case should be closed

  @Validation
  Scenario Outline: User tests validation at the Awaiting Dispatch (Ministerial) stage
    And I trigger the "<errorType>" error message at the "Awaiting Dispatch (Ministerial)" stage
    Then the "<errorType>" error message is displayed at the "Awaiting Dispatch (Ministerial)" stage
    Examples:
      | errorType                 |
      | RESPONSE CHANNEL REQUIRED |
      | DISPATCHED DATE REQUIRED  |