@Search @TO
Feature: Search

  Background:
    Given I am logged into "CS" as user "TO_USER"

  @TORegression
  Scenario Outline: User tests TO search criteria
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field in the "TO" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "<infoType>"
    Examples:
    | infoType                        | infoValue             |
#    | Received on or after date       | 01/02/2022            |
#    | Received on or before date      | 01/01/2022            |
#    | Correspondent full name         | Sam McTester          |
#    | Correspondent postcode          | AB1 2CD               |
#    | Correspondent email address     | SamMcTester@Test.com  |
#    | Correspondent reference number  | Ref-ABCD-1234         |
#    | Active Cases only               | Yes                   |
    | Campaign                        | Test campaign 1       |

  @TORegression
  Scenario: User can search for a TO case by its case reference
    When I create a single "TO" case
    And I search for the case by its case reference
    Then the created case should be the only case visible in the search results