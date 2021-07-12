@AwaitingPaymentConfirmation @WCS
Feature: Awaiting Payment Confirmation

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And  I create a single "WCS" claim
    And I move the claim to the "Awaiting Payment Confirmation" stage

  @Workflow @WCSRegression
  Scenario: User selects that payment confirmation has been received
    When I select that payment confirmation has been received
    Then the claim should be closed

  @Validation
  Scenario: User does not select an option at the Nil Offer Acceptance stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Awaiting Payment Confirmation stage