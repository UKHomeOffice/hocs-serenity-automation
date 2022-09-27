@Search @TO
Feature: Search

  Background:
    Given I am logged into "CS" as user "TO_USER"

  @TORegression
  Scenario: User tests TO search criteria
    When I generate a "TO" case to validate search functionality
    And I navigate to the "Search" page
    And I enter "Sam McTester" into the "Correspondent full name" search field in the "TO" search configuration
    And I enter "AB1 2CD" into the "Correspondent postcode" search field in the "TO" search configuration
    And I enter "SamMcTester@Test.com" into the "Correspondent Email Address" search field in the "TO" search configuration
    And I enter "Ref-ABCD-1234" into the "Correspondent reference number" search field in the "TO" search configuration
    And I enter "01/01/2022" into the "Received on or after date" search field in the "TO" search configuration
    And I enter "01/01/2022" into the "Received on or before date" search field in the "TO" search configuration
    And I enter "Yes" into the "Active Cases only" search field in the "TO" search configuration
    And I enter "Test campaign 1" into the "Campaign" search field in the "TO" search configuration
    And I enter the current case reference into the Case Reference field on the search screen
    And I click the search button on the search page
    Then the created case should be the only case visible in the search results