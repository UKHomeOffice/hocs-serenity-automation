@EndToEnd @WCS
Feature: End To End

  Background:
    Given I am logged into "WCS" as user "WCS_USER"

  @Workflow @WCSRegression
  Scenario: User moves a claim from creation to completed stage
    When I create a single "WCS" claim
    And I move the claim to the "Closed" stage
    Then the claim should be closed