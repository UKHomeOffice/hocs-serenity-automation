@QA @WCS
Feature: QA

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "QA" stage

  @Workflow @WCSRegression
  Scenario: User Approves offer
    When I select to approve the offer
    Then the claim should be moved to the "Payment Pre-Offer Checklist" stage

  @Workflow @WCSRegression
  Scenario: User sends claim back to Casework stage
    When I select to return claim to the Casework team
    Then the claim should be returned to the correct WCS Casework team

  @Validation
  Scenario: User does not select a Claim status
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected a Claim Status