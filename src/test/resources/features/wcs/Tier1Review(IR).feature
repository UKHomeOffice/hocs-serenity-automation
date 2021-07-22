@Tier1Review(IR) @WCS
Feature: Tier 1 Review (IR)

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim
    And I move the claim to the "Tier 1 Review (IR)" stage

  @Workflow @WCSRegression
  Scenario: User returns the claim to Eligibility stage
    When I select to return the claim to Registration
    Then the claim should be moved to the "Registration" stage

  @Workflow @WCSRegression
  Scenario: User archives the claim
    When I select to archive the claim
    Then the claim should be moved to the "Archived Identity Rejected" stage

  @Validation
  Scenario: User does not select an option at the Identity Rejected stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an action on the Tier 1 IR Review page