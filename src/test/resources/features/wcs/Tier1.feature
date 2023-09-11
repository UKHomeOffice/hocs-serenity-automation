@Tier1 @WCS
Feature: Tier 1

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "Tier 1" stage

  @Navigation
  Scenario: User upholds the caseworkers decision
    And I enter a Tier 1 review withdrawal outcome and decision date
    And I enter a interim first outcome and decision date
    And I enter a interim  second outcome and decision date
    And I enter a final first outcome and decision date
    And I enter a final second outcome and decision date
    When I select to progress the claim
    Then the "Tier 1 Awaiting Response" page should be displayed

  @Validation
  Scenario: User does not select an option on the Tier 1 Review page
    And I click the "confirm" button
    Then error messages are displayed as I have not entered any outcomes
    And an error message is displayed as I have not selected an action on the Tier 1 Review page

  @Workflow @WCSRegression
  Scenario: User records that the claimants has accepted the upheld decision
    And I complete the Tier 1 Review page
    When I select that the claimant accepted the offer
    Then the claim should be moved to the "Payment Preparation" stage

  @Workflow @WCSRegression
  Scenario: User records that the claimants has rejected the interim offer and send the claim back to Casework stage
    And I complete the Tier 1 Review page
    When I select that the claimant rejected the interim or preliminary offer, or the offer was rejected
    Then the claim should be moved to the "Casework" stage

  @Workflow @WCSRegression
  Scenario: User records that the claimants has rejected the final offer
    And I complete the Tier 1 Review page
    When I select that the claimant rejected the final offer
    Then the claim should be moved to the "Tier 2" stage

  @Workflow @WCSRegression
  Scenario: User records that the claim is moved to Eligibility
    And I complete the Tier 1 Review page
    When I select move claim to Eligibility
    Then the claim should be moved to the "Eligibility" stage

  @Workflow @WCSRegression
  Scenario: User records that the claim is moved to Triage
    And I complete the Tier 1 Review page
    When I select move claim to Triage
    Then the claim should be moved to the "Triage" stage

  @Workflow @WCSRegression
  Scenario: User records that the claim is moved to Casework
    And I complete the Tier 1 Review page
    When I select move claim to Casework
    Then the claim should be moved to the "Casework" stage

  @Validation
  Scenario: User does not select an option on the Tier 1 Awaiting Response page
    And I complete the Tier 1 Review page
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option on the Tier 1 Awaiting Response page
    