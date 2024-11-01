@IdentityRejected @WCS
Feature: Identity Rejected

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "Identity Rejected" stage


  @Workflow @WCSRegression
  Scenario: User sends the claim to Tier 1 review
    When I select to send the claim to Tier 1 review
    Then the claim should be moved to the "Tier 1 Review (IR)" stage

  @Workflow @WCSRegression
  Scenario: User archives the claim - Identity Rejected
    When I select to archive the claim
    Then the claim should be moved to the "Archived Identity Rejected" stage

  @Validation
  Scenario: User does not select an option at the Identity Rejected stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Identity Rejected stage