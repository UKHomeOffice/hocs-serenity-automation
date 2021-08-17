@Acceptance @FOI @FOIWorkflow
Feature: FOI Dispatch

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Dispatch" stage
    And I load and claim the current case

  Scenario: User is able to complete the Dispatch stage
    And I select "Yes" to do you want to dispatch the case
    And I click the "Continue" button
    Then the case should be moved to the "Soft Close" stage

  Scenario: User is able to reject a case at the Dispatch stage
    And I select "No" to do you want to dispatch the case
    And I click the "Continue" button
    Then the case should be moved to the "Approval" stage