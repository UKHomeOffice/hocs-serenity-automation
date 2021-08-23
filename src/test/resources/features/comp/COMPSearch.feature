@COMPSearch @COMP
Feature: COMP Search

  Background:
    Given I am logged into "CS" as user "COMP_USER"

#    HOCS-2838, HOCS-3036
  @COMPRegression
  Scenario Outline: User tests COMP search criteria
    When I create a "COMP" case with "<infoValue>" as its "<infoType>"
    And I navigate to the "Dashboard" page
    And I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" COMP search criteria
    And I click the search button on the search page
    Then I check that the COMP search results have the correct "<infoType>"
    Examples:
      | infoType                          | infoValue                            |
      | Correspondent Full Name           | Sam McTester                         |
      | Correspondent Postcode            | AB1 2CD                              |
      | Correspondent Email Address       | SamMcTester@Test.com                 |
      | Complainant Date Of Birth         | 01/01/2001                           |
      | Complainant Home Office Reference | Test entry for Home Office Reference |

#    HOCS-2838
  @COMPRegression
  Scenario: User can search for a COMP case by its case reference
    When I create a single "COMP" case
    And I search for the "COMP" case by its case reference
    Then I check that the COMP search results have the correct "Case Reference"

#     HOCS-2847 HOCS-3161
  @COMPRegression
  Scenario: COMP User sees the required information when viewing search
    Given I am logged into "CS" as user "COMP_USER"
    And I navigate to the "search" page
    And I click the search button on the search page
    Then the "COMP Search" workstack should contain only the expected columns