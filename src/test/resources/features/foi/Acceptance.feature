@FOIAcceptance @FOI @FOIWorkflow
Feature: Acceptance

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Acceptance" stage
    And I load and claim the current case

  #HOCS-2586, HOCS-2723
  Scenario: User is able to complete the Acceptance stage
    And I select that the case "Does" belong in this Group
    And I select a Responsible Team and complete acceptance
    Then the case should be moved to the "Consider and Draft" stage
    And the case should still be owned by the selected FOI Group
    And the summary should contain the Responsible Team
    And the read-only Case Details accordion should contain all case information entered during the "Acceptance" stage

  # Expected failure. Defect HOCS-4876 raised.
  #HOCS-2753, HOCS-2741
  @FOIRegression
  Scenario: User rejects a case at the Acceptance stage
    And I select that the case "Doesn't" belong in this Group
    And I submit a rejection reason at the Acceptance stage
    Then the case should be returned to the "Allocation" stage
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case
    And the rejected column of the case in the "FOI Allocation" workstack should display rejected by "Acceptance"