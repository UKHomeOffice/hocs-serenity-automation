@PaymentPreOfferChecklist @WCS
Feature: Payment Pre Offer Checklist

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim
    And I move the claim to the "PAYMENT PRE-OFFER CHECKLIST" stage

  @Workflow @WCSRegression
  Scenario: User returns the claim to QA for corrections
    When I select to return the claim to QA
    Then the claim should be moved to the "QA" stage

  @Workflow @WCSRegression
  Scenario: User approves the offer
    When I select to send the claim to offer approval
    Then the claim should be moved to the "Offer Approval" stage

  @Validation
  Scenario: User does not select an option at the Payment Pre-Offer Checklist stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Payment Pre-Offer Checklist stage