@Search @CS
Feature: Search

  @CSRegression
  Scenario Outline: User tests DECS search criteria
    Given I am logged into "CS" as user "DECS_USER"
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field in the "DECS" search configuration
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
    | Received on or before date        | 01/09/2022                            |
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