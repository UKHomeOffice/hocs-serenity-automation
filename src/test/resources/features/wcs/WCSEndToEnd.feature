@EndToEnd @WCS
Feature: End To End

  Background:
    Given I am logged into "WCS" as user "WCS_USER"

  @Workflow @WCSRegression
  Scenario: User moves a claim from creation to closed
    When I create a single "WCS" claim
    And I complete the "Registration" stage
    And I complete the "Triage" stage
    And I complete the "Casework" stage
    And I complete the "QA" stage
    And I complete the "Payment Pre-Offer Checklist" stage
    And I complete the "Offer Approval" stage
    And I complete the "Send Offer" stage
    And I complete the "Offer Acceptance" stage
    And I complete the "Payment Preparation" stage
    And I complete the "Payment Approval" stage
    And I complete the "Send Payment" stage
    And I complete the "Awaiting Payment Confirmation" stage
    Then the claim should be closed

  @E2ETests
  Scenario: Robust E2E Worklow with WCS Casetype
    And I wipe the record data
    And I choose not to wipe the record data until the end
    And I get a "WCS" claim at the "Casework" stage
    And I enter some value into each possible field during the Casework stage
    And I complete the "QA" stage
    And I complete the "Payment Pre-Offer Checklist" stage
    And I complete the "Offer Approval" stage
    And I complete the "Send Offer" stage
    And I complete the "Offer Acceptance" stage
    And I complete the "Payment Preparation" stage
    And I complete the "Payment Approval" stage
    And I complete the "Send Payment" stage
    And I complete the "Awaiting Payment Confirmation" stage
    Then the claim should be closed
    And the summary tab should display the details entered at various stages
    Then All fields should be populated in the read-only case info accordion