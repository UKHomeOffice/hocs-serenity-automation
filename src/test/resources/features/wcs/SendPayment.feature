@SendPayment @WCS
Feature: Send Payment

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim

  @Workflow @WCSRegression
  Scenario: User selects that an interim payment has been sent and returns claim to Casework team 1
    And I move the claim to the "Send Payment" stage via Casework Team: "WCS Casework Team 1"
    When I select that an interim payment has been sent
    Then the claim should be returned to the correct WCS Casework team

  @Workflow
  Scenario Outline: User selects that an interim payment has been sent and returns claim to other Casework teams
    And I move the claim to the "Send Payment" stage via Casework Team: "<teamName>"
    When I select that an interim payment has been sent
    Then the claim should be returned to the correct WCS Casework team
    Examples:
      | teamName                       |
      | Initial Consideration Casework |
      | WCS Casework Team 2            |
      | WCS Casework Team 3            |
      | WCS Casework Team 4            |

  @Workflow @WCSRegression
  Scenario: User selects that a final payment has been sent
    And I move the claim to the "Send Payment" stage
    When I select that a final payment has been sent
    Then the claim should be moved to the "Awaiting payment confirmation" stage

  @Validation
  Scenario: User does not select an option at the Offer Approval stage
    And I move the claim to the "Send Payment" stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Send Payment stage