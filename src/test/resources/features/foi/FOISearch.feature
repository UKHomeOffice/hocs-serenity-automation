@FOISearch @FOI @FOIWorkflow @FOIRegression
Feature: FOI Search

  Background:
    Given I am logged into "CS" as user "FOI_USER"

  @FOIRegression
  Scenario Outline: User tests FOI search
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field in the "FOI" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "<infoType>"
    Examples:
    | infoType                | infoValue                 |
    | Case Type               | FOI                       |
    | Received on or After    | 01/09/2021                |
    | Received on or Before   | 01/09/2021                |
    | Correspondent (non-MP)  | Sam McTester             |
#    | Topic                   | Animal alternatives (3Rs) | Topic search criteria doesn't work properly
#    | Active Cases Only       | Yes                       | In FOI no cases are technically 'closed' so active search isn't really necessary

  @FOIRegression
  Scenario: User can search for a FOI case by its case reference
    When I create a single "FOI" case
    And I navigate to the "Search" page
    And I search for the case by its case reference
    Then the created case should be the only case visible in the search results