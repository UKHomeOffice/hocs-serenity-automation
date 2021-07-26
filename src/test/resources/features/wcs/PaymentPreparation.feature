@PaymentPreparation @WCS
Feature: Payment Preparation

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim
    And I move the claim to the "Payment Preparation" stage

  @Workflow @WCSRegression
  Scenario: User approves the offer
    When I select to send the claim to Payment Approval
    Then the claim should be moved to the "Payment Approval" stage

  @Validation
  Scenario: User does not select an option at the Payment Preparation stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Payment Preparation stage