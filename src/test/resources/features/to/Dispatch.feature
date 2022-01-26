@Dispatch @TO
Feature: Dispatch

  Background:
    Given I am logged into "CS" as user "TO_USER"
    And I get a "TO" case at the "Dispatch" stage

  @TOWorkflow @TORegression
  Scenario: As a Disptach user, I want to be able to enter the details of dispatch, so the case can be closed
    When I add a "Final response" type document to the case
    And I enter the details of the dispatch
    And I confirm the case is completed
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Dispatch" stage

  @TORegression
  Scenario: As a Dispatch user, I want to be able to save changes to the case, so corrections can be made
    When I open the "Case Details" accordion section
    And I change the channel the correspondence was received by
    And I save the changes
    Then the amended value for Channel Received should be saved to the case
