@Search @TO
Feature: Search

  Background:
    Given I am logged into "CS" as user "TO_USER"

  @TORegression
  Scenario: User can search for a TO case by its case reference
    When I create a single "TO" case
    And I search for the case by its case reference
    Then the created case should be the only case visible in the search results