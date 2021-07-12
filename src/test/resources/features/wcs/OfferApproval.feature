@OfferApproval @WCS
Feature: Offer Approval

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim

  @Workflow @WCSRegression
  Scenario: User approves the offer
    And I move the claim to the "Offer Approval" stage
    When I select to Approve the offer
    Then the claim should be moved to the "Send Offer" stage

  @Workflow @WCSRegression
  Scenario: User returns the claim to QA for corrections
    And I move the claim to the "Offer Approval" stage
    When I select to return the claim to QA for corrections
    Then the claim should be returned to the "QA" stage

  @Workflow @WCSRegression
  Scenario: User sends the claim back to the Casework team 1
    And I move the claim to the "Offer Approval" stage via Casework Team: "WCS Casework Team 1"
    When I select to return claim to the Casework team due to PNC result
    Then the claim should be returned to the correct WCS Casework team

  @Workflow
  Scenario Outline: User sends the claim back to the other Casework teams
    And I move the claim to the "Offer Approval" stage via Casework Team: "<teamName>"
    When I select to return claim to the Casework team due to PNC result
    Then the claim should be returned to the correct WCS Casework team
    Examples:
      | teamName                       |
      | Initial Consideration Casework |
      | WCS Casework Team 2            |
      | WCS Casework Team 3            |
      | WCS Casework Team 4            |

  @Validation
  Scenario: User does not select an option at the Offer Approval stage
    And I move the claim to the "Offer Approval" stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Offer Approval stage