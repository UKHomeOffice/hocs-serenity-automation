@Search @WCS
Feature: Search

  Background:
    Given I am logged into "WCS" as user "WCS_USER"

  @Validation
  Scenario: An error message should be displayed if a user enters a Reference number that does not exist
    When I enter a non-existent reference
    Then an error message should be displayed stating that there are no active workflows for the case

  @Validation
  Scenario: User must enter a search query in the Load Case search bar
    When I press enter in the Load Case search bar
    Then an error message should be displayed stating that a case reference is required

  @Validation
  Scenario: User must enter a WCS format reference in the Load Case search bar
    When I enter a non-hocs format case reference
    Then an error message should be displayed stating that the case reference entered is an invalid format

  @WCSRegression
  Scenario: User should be able to search for a claim by reference number
    And I create a single "WCS" claim
    And I navigate to the "search" page
    When I search for the claim by reference number
    Then the created claim should be displayed as a search result

  @WCSRegression
  Scenario: User should be able to search for claims by claimant name
    And I create a WCS claim and enter the claimant name "Micky Mouse"
    And I create a WCS claim and enter the claimant name "Minny Mouse"
    And I navigate to the "search" page
    When I search by the claimant name "Mouse"
    Then all search results should have a claimant name that contains "Mouse"

  @WCSRegression
  Scenario: User should be able to search for claims by claimant DOB
    And I create a WCS claim and enter the claimant DOB as today's date
    And I navigate to the "search" page
    When I search for Claimant DOB using today's date
    Then the created "Claimant DOB" claim should be visible in the search results

  @WCSRegression
  Scenario: User should be able to search for claims by National Insurance No
    And I create a WCS claim and enter "QQ123456C" as the National Insurance No
    And I navigate to the "search" page
    When I search for the National Insurance No "QQ123456C"
    Then the created "National Insurance No" claim should be visible in the search results

  @WCSRegression
  Scenario: User should be able to search for claims by previous HOCS reference
    And I create a WCS claim and enter "WCS/0000000/19" as the previous HOCS reference
    And I navigate to the "search" page
    When I search for the previous HOCS reference "WCS/0000000/19"
    Then the created "Previous HOCS Reference" claim should be visible in the search results

