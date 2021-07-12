@QA @WCS
Feature: QA

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim

  @Workflow @WCSRegression
  Scenario: User Approves offer
    And I move the claim to the "QA" stage
    When I select to approve the offer
    Then the claim should be moved to the "Payment Pre-Offer Checklist" stage

  @Workflow @WCSRegression
  Scenario: User sends claim back to Casework team 1
    And I move the claim to the "QA" stage via Casework Team: "WCS Casework Team 1"
    When I select to return claim to the Casework team
    Then the claim should be returned to the correct WCS Casework team

  @Workflow
  Scenario Outline: User sends claim back to the other Casework teams
    And I move the claim to the "QA" stage via Casework Team: "<teamName>"
    When I select to return claim to the Casework team
    Then the claim should be returned to the correct WCS Casework team
    Examples:
      | teamName                       |
      | Initial Consideration Casework |
      | WCS Casework Team 2            |
      | WCS Casework Team 3            |
      | WCS Casework Team 4            |

  @Validation
  Scenario: User does not select a Claim status
    And I move the claim to the "QA" stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected a Claim Status