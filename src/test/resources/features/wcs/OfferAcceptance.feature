@OfferAcceptance @WCS
Feature: Offer Acceptance

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "Offer Acceptance" stage

  @Workflow @WCSRegression
  Scenario: User records that the offer was accepted
    When I select that the offer was accepted by the claimant
    Then the claim should be moved to the "Payment Preparation" stage

  @Workflow @WCSRegression
  Scenario: User records that the offer was rejected by the claimant
    When I select that the offer was rejected by the claimant
    Then the claim should be moved to the "Tier 1" stage

  @Workflow @WCSRegression
  Scenario: User records that the claim is moved to Eligibility
    When I select move claim to Eligibility
    Then the claim should be moved to the "Eligibility" stage

  @Workflow @WCSRegression
  Scenario: User records that the claim is moved to Triage
    When I select move claim to Triage
    Then the claim should be moved to the "Triage" stage

  @Workflow @WCSRegression
  Scenario: User records that the claim is moved to Casework
    When I select move claim to Casework
    Then the claim should be moved to the "Casework" stage

  @Validation
  Scenario: User does not select an option at the Offer Acceptance stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Offer Acceptance stage