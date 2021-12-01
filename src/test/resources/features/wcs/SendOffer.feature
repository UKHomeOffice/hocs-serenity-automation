@SendOffer @WCS
Feature: Send Offer

  Background:
    Given I am logged into "WCS" as user "WCS_USER"
    And I get a "WCS" claim at the "Send Offer" stage

  @Workflow @WCSRegression
  Scenario: User records that monetary offer sent to claimant
    When I select to record that a monetary offer has been sent
    Then the claim should be moved to the "Offer Acceptance" stage

  @Workflow @WCSRegression
  Scenario: User records that Nil payment offer sent to claimant
    When I select to record that a Nil payment offer has been sent
    Then the claim should be moved to the "Nil Offer Acceptance" stage

  @Workflow @WCSRegression
  Scenario: User returns the claim to Offer Approval Due to PNC failure
    When I select to return the claim to Offer Approval due to PNC failure
    Then the claim should be returned to the "Offer Approval" stage

  @Validation
  Scenario: User does not select an option at the Send Offer stage
    When I click the "Confirm" button
    Then an error message is displayed as I have not selected an option at the Send Offer stage