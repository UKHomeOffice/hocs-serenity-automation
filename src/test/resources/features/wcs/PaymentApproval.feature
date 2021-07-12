@PaymentApproval @WCS
Feature: Payment Approval

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim

  @Workflow @WCSRegression
  Scenario: User sends claim back to Casework team 1
    And I move the claim to the "Payment Approval" stage via Casework Team: "WCS Casework Team 1"
    When I select to return claim to the Casework team due to PNC failure
    Then the claim should be returned to the correct WCS Casework team

  @Workflow
  Scenario Outline: User sends claim back to other Casework teams
    And I move the claim to the "Payment Approval" stage via Casework Team: "<teamName>"
    When I select to return claim to the Casework team due to PNC failure
    Then the claim should be returned to the correct WCS Casework team
    Examples:
      | teamName                       |
      | Initial Consideration Casework |
      | WCS Casework Team 2            |
      | WCS Casework Team 3            |
      | WCS Casework Team 4            |

  @Workflow @WCSRegression
  Scenario: User approves the payment and progresses claim to Send Payment stage
    And I move the claim to the "Payment Approval" stage
    When I select that the payment was approved and is ready to be sent
    Then the claim should be moved to the "Send Payment" stage

  @Validation
  Scenario: User does not select an option at the Payment Approval stage
    And I move the claim to the "Payment Approval" stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Payment Approval stage