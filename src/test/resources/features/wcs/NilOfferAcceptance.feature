@NilOfferAcceptance @WCS
Feature: Nil Offer Acceptance

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim
    And I move the claim to the "Nil Offer Acceptance" stage

  @Workflow @WCSRegression
  Scenario: User records that there was no challenge to the Nil Offer
    When I select that there was no challenge to the Nil Offer
    Then the claim should be closed

  @Workflow @WCSRegression
  Scenario: User records that the offer was rejected by the claimant
    When I select that the nil offer was rejected by the claimant
    Then the claim should be moved to the "Tier 1" stage

  @Validation
  Scenario: User does not select an option at the Nil Offer Acceptance stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Nil Offer Acceptance stage