@Triage @WCS
Feature: Triage

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim
    And I move the claim to the "Triage" stage

  @Workflow @WCSRegression
  Scenario Outline: User selects a casework team at Triage stage
    When I select "<teamName>" as the casework team
    Then the claim should be sent to the correct WCS Casework team
    Examples:
      | teamName                       |
      | Initial Consideration Casework |
      | WCS Casework Team 1            |
      | WCS Casework Team 2            |
      | WCS Casework Team 3            |
      | WCS Casework Team 4            |

  @Validation
  Scenario: User does not select a casework team
    When I click the "Continue" button
    Then an error message is displayed as I have not selected a caseworking team