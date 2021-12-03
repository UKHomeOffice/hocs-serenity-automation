@PaymentApproval @WCS
Feature: Payment Approval

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "Payment Approval" stage

  @Workflow @WCSRegression
  Scenario: User sends claim back to Casework stage
    When I select to return claim to the Casework team due to PNC failure
    Then the claim should be returned to the correct WCS Casework team

  @Workflow @WCSRegression
  Scenario: User approves the payment and progresses claim to Send Payment stage
    When I select that the payment was approved and is ready to be sent
    Then the claim should be moved to the "Send Payment" stage

  @Validation
  Scenario: User does not select an option at the Payment Approval stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Payment Approval stage