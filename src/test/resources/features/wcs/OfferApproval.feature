@OfferApproval @WCS
Feature: Offer Approval

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "Offer Approval" stage

  @Workflow @WCSRegression
  Scenario: User approves the offer
    When I select to Approve the offer
    Then the claim should be moved to the "Send Offer" stage

  @Workflow @WCSRegression
  Scenario: User returns the claim to QA for corrections
    When I select to return the claim to QA for corrections
    Then the claim should be returned to the "QA" stage

  @Workflow @WCSRegression
  Scenario: User sends the claim back to the Casework stage
    When I select to return claim to the Casework team due to PNC result
    Then the claim should be returned to the correct WCS Casework team

  @Validation
  Scenario: User does not select an option at the Offer Approval stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Offer Approval stage