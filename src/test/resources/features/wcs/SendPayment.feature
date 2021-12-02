@SendPayment @WCS
Feature: Send Payment

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "Send Payment" stage

  @Workflow @WCSRegression
  Scenario: User selects that an interim payment has been sent and returns claim to Casework stage
    When I select that an interim payment has been sent
    Then the claim should be returned to the correct WCS Casework team

  @Workflow @WCSRegression
  Scenario: User selects that a final payment has been sent
    When I select that a final payment has been sent
    Then the claim should be moved to the "Awaiting payment confirmation" stage

  @Validation
  Scenario: User does not select an option at the Offer Approval stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Send Payment stage