@ArchivedEligibilityRejected @WCS
Feature: Archived Eligibility Rejected

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And  I create a single "WCS" claim
    And I move the claim to the "Archived Eligibility Rejected" stage

  @Workflow @WCSRegression
  Scenario: User returns the claim to Eligibility stage
    When I select to return the claim to Eligibility
    Then the claim should be moved to the "Eligibility" stage

  @Workflow @WCSRegression
  Scenario: User permanently closes the claim
    When I select to permanently close the claim
    Then the claim should be closed

  @Validation
  Scenario: User does not select an option at the Archived Eligibility Rejected stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Archived Eligibility Rejected stage