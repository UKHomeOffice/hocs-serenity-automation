@Casework @WCS
Feature: Casework

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "Casework" stage

  @Workflow @WCSRegression
  Scenario: User put a claim on hold at Casework stage
    When I put the claim on hold at the Casework stage
    Then the claim should be moved to the "Casework On Hold" stage

  @Workflow @WCSRegression
  Scenario: User takes a claim off of hold at Casework stage
    And I put the claim on hold at the Casework stage
    And I load the current claim
    When I take the claim off of hold at the Casework stage
    Then the claim should be moved to the "Casework" stage

  @Workflow @WCSRegression
  Scenario: User sends the claim back to Eligibility from Casework stage
    And I select to send the claim back to Eligibility from Casework stage
    And I enter a reason
    When I click the "Finish" button
    Then the claim should be returned to the "Eligibility" stage

  @Workflow @WCSRegression
  Scenario: User sends the claim back to Registration from Casework stage
    And I select to send the claim back to Registration from Casework stage
    And I enter a reason
    When I click the "Finish" button
    Then the claim should be returned to the "Registration" stage

  @Workflow @WCSRegression
  Scenario: User sends the claim back to Triage from Casework stage
    And I select to send the claim back to Triage from Casework stage
    And I enter a reason
    When I click the "Finish" button
    Then the claim should be returned to the "Triage" stage

  @Workflow @WCSRegression
  Scenario:  User sends the claim to QA Stage
    And I select that the offer is ready to QA
    Then the claim should be moved to the "QA" stage

  @Validation
  Scenario: User does not select a claim status
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected a Claim status

  @Validation
  Scenario: User does not enter a reason for sending claim back to Eligibility
    And I select to send the claim back to Eligibility from Casework stage
    When I click the "Finish" button
    Then an error message is displayed as I have not entered a reason for returning the claim to Eligibility

  @Validation
  Scenario: User does not enter a reason for sending claim back to Registration
    And I select to send the claim back to Registration from Casework stage
    When I click the "Finish" button
    Then an error message is displayed as I have not entered a reason for returning the claim to Registration

  @Validation
  Scenario: User does not enter a reason for sending claim back to Registration
    And I select to send the claim back to Triage from Casework stage
    When I click the "Finish" button
    Then an error message is displayed as I have not entered a reason for returning the claim to Triage

  @Data
  Scenario: User takes a claim to casework stage and enters values into every field
    And I enter some value into each possible field during the Casework stage
    And I load the current claim
    Then All fields should be populated in the read-only case info accordion