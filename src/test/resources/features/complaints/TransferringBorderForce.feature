@CCH @PSU @Complaints @BFComplaints
Feature: Transferring Border Force cases to CCH and PSU

  @BFRegression @HOCS-4951
  Scenario: User can transfer offline and close a BF complaints case
    Given I am logged into "CS" as user "BF_USER"
    And I create a "BF" case and move it to the "Triage" stage
    And I load and claim the current case
    When I do not accept the case at Triage
    And I enter reason for Triage transfer and close the case
    Then the case should be closed
    And a Case Transfer note should be visible in the timeline showing the submitted reason for transferring the case

  @BFRegression @HOCS-4951
  Scenario: User can transfer offline and close a BF2 complaints case
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF2" case at the "Triage" stage
    When I do not accept the case at Triage
    And I enter reason for Triage transfer and close the case
    Then the case should be closed
    And a Case Transfer note should be visible in the timeline showing the submitted reason for transferring the case