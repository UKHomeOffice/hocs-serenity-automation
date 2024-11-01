@Tier2 @WCS
Feature: Tier 2

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "Tier 2" stage

  @Workflow @WCSRegression
  Scenario: User records that the claimant accepted the revised offer
    And I enter a Tier 2 review withdrawal outcome and decision date
    And I select that the claimant accepts the revised offer
    Then the claim should be moved to the "Payment Preparation" stage

  @Workflow @WCSRegression
  Scenario: User records that the claimant rejected the revised offer
    And I enter a Tier 2 review withdrawal outcome and decision date
    And I select that the claimant rejects the revised offer
    Then the claim should be closed

  @Workflow @WCSRegression
  Scenario: User records that the claimant accepted the upheld offer
    And I enter a Tier 2 review withdrawal outcome and decision date
    And I select that the claimant accepts the upheld offer
    Then the claim should be moved to the "Payment Preparation" stage

  @Workflow @WCSRegression
  Scenario: User records that the claimant rejected the upheld offer
    And I enter a Tier 2 review withdrawal outcome and decision date
    And I select that the claimant rejects the upheld offer
    Then the claim should be closed

  @Validation
  Scenario: User does not select an option on the Tier 2 Coordination page
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option on the Tier 2 Coordination page

  @WCSRegression
  Scenario: User enters information for adjudicators decision and checks it is recorded in case details
    And I enter a Tier 2 review withdrawal outcome and decision date
    And I enter a Tier 2 adjudicators office decision and decision date
    And I load the current claim
    Then the adjudicators office decision details are correctly displayed in the case details accordion