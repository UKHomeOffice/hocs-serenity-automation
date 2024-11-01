@FOISearch @FOI @FOIWorkflow @FOIRegression
Feature: FOI Search

  Background:
    Given I am logged into "CS" as user "FOI_USER"

  @FOIRegression @Search
  Scenario: User tests FOI search criteria
    When I generate a "FOI" case to validate search functionality
    And I navigate to the "Search" page
    And I enter "FOI" into the "Case Type" search field
    And I enter "01/01/2022" into the "Received on or After Date" search field
    And I enter "01/01/2022" into the "Received on or Before Date" search field
    And I enter "Sam McTester" into the "Public Correspondent Name" search field
    And I enter the current case reference into the Case Reference field on the search screen
    And I click the search button on the search page
    Then the created case should be the only case visible in the search results