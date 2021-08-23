@FOIAcceptance @FOI @FOIWorkflow
Feature: Acceptance

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Acceptance" stage
    And I load and claim the current case

  #HOCS-2586, HOCS-2723
  Scenario: User is able to complete the Acceptance stage
    And I select that the case "Does" belong to this Directorate
    And I click the "Continue" button
    And I select the drafting team required to respond to the request
    And I click the "Finish" button
    Then the case should be moved to the "Consider and Draft" stage
    And the case should be assigned to the Drafting team selected at Acceptance

  #HOCS-2753, HOCS-2741
  @FOIRegression
  Scenario: User rejects a case at the Acceptance stage
    And I select that the case "Doesn't" belong to this Directorate
    And I enter a rejection reason at the Acceptance stage
    And I click the "Continue" button
    Then the case should be returned to the "Allocation" stage
    And a rejection note should be visible showing the reason for rejection