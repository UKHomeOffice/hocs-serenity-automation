@FOISearch @FOI @FOIWorkflow @FOIRegression
Feature: FOI Search

  Background:
    Given I am logged into "CS" as user "FOI_USER"

  Scenario Outline: User tests FOI search
    When I create a "FOI" case with "<infoValue>" as its "<infoType>"
    And I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" FOI search criteria
    And I click the search button on the search page
    Then I check that the FOI search results have the correct "<infoType>"
    Examples:
    | infoType                | infoValue                 |
    | Case Type               | FOI                       |
    | Received on or After    | 01/08/2021                |
    | Received on or Before   | 01/08/2021                |
    | Correspondent (non-MP)  | Test McTester             |
#    | Topic                   | Animal alternatives (3Rs) | Topic search criteria doesn't work properly
#    | Active Cases Only       | Yes                       | In FOI no cases are technically 'closed' so active search isn't really necessary

  Scenario: User can search for a FOI case by its case reference
    When I create a single "FOI" case
    And I navigate to the "Search" page
    And I search for the "FOI" case by its case reference
    Then I check that the FOI search results have the correct "Case Reference"