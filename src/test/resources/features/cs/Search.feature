@Search @CS
Feature: Search

  Background:
    Given I am logged into "CS" as user "DECS_USER"


  @CSRegression @CSE2ETests
  Scenario Outline: User tests DECS search criteria
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field
    And I click the search button on the search page
    Then I check that the search results have the correct "<infoType>"
    Examples:
    | infoType                          | infoValue                             |
    | Case Type                         | Random                                |
    | Case Type                         | MIN                                   |
    | Active Cases Only                 | Yes                                   |
    | Correspondent Full Name           | Sam McTester                          |
    | Correspondent Postcode            | AB1 2CD                               |
    | Correspondent email address       | SamMcTester@Test.com                  |
    | Complainant Date of Birth         | 01/01/2001                            |
    | Received on or after date         | 01/09/2022                            |
#    | Received on or before date        | 01/09/2022                            |
    | Member of Parliament Name         | Boris Johnson                         |
    | Public Correspondent Name         | Sam McTester                          |
    | Topic                             | Animal alternatives (3Rs)             |
    | Sign off team                     | Minister for Lords                    |
    | Home Secretary Interest           | Yes                                   |
    | Correspondent reference number    | Ref-ABCD-1234                         |
    | Complainant Home Office Reference | Test entry for Home Office Reference  |
    | PSU Reference                     | 123456789                             |
    | Reference Type                    | Ministerial                           |
    | Reference Type                    | Official                              |
    | Ministerial Sign Off Team         | Home Secretary                        |
    | Campaign                          | Test campaign 1                       |

  @SearchByCaseReferenceNumber
  Scenario: User should be be taken directly to a case when they for enter a valid case reference in the Load Case bar
    When I enter a valid case reference into the load case search bar
    Then I should be taken directly to the case

  @SearchByCaseReferenceNumber @Validation
  Scenario: An error message should be displayed if a user enters a Reference number that does not exist
    When I enter a non-existent case reference
    Then an error message should be displayed stating that there are no active workflows for the case

  @SearchByCaseReferenceNumber @Validation
  Scenario: User must enter a search query in the Load Case search bar
    When I press enter in the Load Case search bar
    Then an error message should be displayed stating that a case reference is required