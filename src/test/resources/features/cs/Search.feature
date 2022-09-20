@Search @CS
Feature: Search

  Background:
    Given I am logged into "CS" as user "DECS_USER"

  @CSRegression
  Scenario Outline: User tests DECS search criteria
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field in the "<searchConfig>" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "<infoType>"
    Examples:
    | infoType    | infoValue    |  searchConfig    |
    | Case Type   | MIN          |  DCU             |
    |