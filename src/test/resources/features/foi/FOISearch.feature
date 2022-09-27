@FOISearch @FOI @FOIWorkflow @FOIRegression
Feature: FOI Search

  Background:
    Given I am logged into "CS" as user "FOI_USER"

  @FOIRegression
  Scenario: User tests FOI search criteria
    When I generate a "FOI" case to validate search functionality
    And I navigate to the "Search" page
    And I enter "FOI" into the "Case Type" search field in the "FOI" search configuration
    And I enter "01/01/2022" into the "Received on or After Date" search field in the "FOI" search configuration
    And I enter "01/01/2022" into the "Received on or Before Date" search field in the "FOI" search configuration
    And I enter "Sam McTester" into the "Public Correspondent Name" search field in the "FOI" search configuration
    And I enter the current case reference into the Case Reference field on the search screen
    And I click the search button on the search page
    Then the created case should be the only case visible in the search results

  Scenario Outline: User tests FOI search
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field in the "FOI" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "<infoType>"
    Examples:
    | infoType                     | infoValue                 |
#    | Topic                   | Animal alternatives (3Rs) | Topic search criteria doesn't work properly
#    | Active Cases Only       | Yes                       | In FOI no cases are technically 'closed' so active search isn't really necessary