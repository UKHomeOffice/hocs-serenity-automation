@Eligibility @WCS
Feature: Eligibility

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "Eligibility" stage

  @Workflow @WCSRegression
  Scenario: User put a claim on hold at Eligibility stage
    When I put the claim on hold at the Eligibility stage
    Then the claim should be moved to the "Eligibility On Hold" stage

  @Workflow @WCSRegression
  Scenario: User takes a claim off of hold at Eligibility stage
    And I put the claim on hold at the Eligibility stage
    And I load the current claim
    When I take the claim off of hold at the Eligibility stage
    Then the claim should be moved to the "Eligibility" stage

  @Workflow @WCSRegression
  Scenario: User closes a claim after no response from claimant
    And I put the claim on hold at the Eligibility stage
    And I load the current claim
    When I select to close the claim after no response from claimant
    And I confirm the claim should be closed
    Then the claim should be closed

  @Navigation
  Scenario: User decides to not close claim for no response from claimant
    And I put the claim on hold at the Eligibility stage
    And I load the current claim
    When I select to close the claim after no response from claimant
    And I decide to not close the claim
    Then the "Eligibility On Hold" page should be displayed

  @Workflow @WCSRegression
  Scenario: User confirms eligibility and sends claim to Triage
    When I confirm the eligibility of the claimant
    Then the claim should be moved to the "Triage" stage

  @Navigation
  Scenario: User select that the Claimant is not eligible
    When I select that I cannot confirm the candidates eligibility
    Then the "Eligibility Rejection Reason" page should be displayed

  @Validation
  Scenario: User select that they cant confirm claimant eligibility but dont give a reason why
    When I select that I cannot confirm the candidates eligibility
    And I click the "Confirm" button
    Then an error message should be displayed as I have not selected a rejection reason

  @Workflow @WCSRegression
  Scenario: User confirms an Eligibility Rejection Reason
    And I select that I cannot confirm the candidates eligibility
    And I select an Eligibility rejection reason
    Then the claim should be moved to the "Eligibility Rejected" stage

  @Validation
  Scenario: User presses Confirm without selecting a value for the confirm claimants eligibility question
    When I click the "Confirm" button
    Then an error message is displayed as I have not confirmed the claimants eligibility