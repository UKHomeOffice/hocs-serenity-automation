@ComplaintsSearch @Complaints
Feature: Complaints Search

#    HOCS-2838, HOCS-3036
  @ComplaintsRegression
  Scenario Outline: User tests COMP search criteria
    Given I am logged into "CS" as user "COMP_USER"
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field in the "COMP" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "<infoType>"
    Examples:
      | infoType                          | infoValue                            |
      | Case Type                         | COMP                                 |
      | Case Type                         | COMP2                                |
      | Correspondent Full Name           | Sam McTester                         |
      | Correspondent Postcode            | AB1 2CD                              |
      | Correspondent Email Address       | SamMcTester@Test.com                 |
      | Complainant Date Of Birth         | 01/01/2001                           |
      | Complainant Home Office Reference | Test entry for Home Office Reference |

#    HOCS-2838
  @ComplaintsRegression
  Scenario: User can search for a COMP case by its case reference
    Given I am logged into "CS" as user "COMP_USER"
    When I create a single "COMP" case
    And I search for the case by its case reference
    Then the created case should be the only case visible in the search results

  #HOCS-4079
  @ComplaintsRegression
  Scenario Outline: User tests BF search criteria
    Given I am logged into "CS" as user "BF_USER"
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" search field in the "BF" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "<infoType>"
    Examples:
    | infoType                          | infoValue                             |
    | Correspondent full name           | Sam McTester                          |
    | Correspondent postcode            | AB1 2CD                               |
    | Correspondent email address       | SamMcTester@Test.com                  |
    | Complainant date of birth         | 01/01/2001                            |
    | Complainant Home Office Reference | Test entry for Home Office Reference  |

  #HOCS-4079
  @ComplaintsRegression
  Scenario: User can search for a BF case by its case reference
    Given I am logged into "CS" as user "BF_USER"
    When I create a single "BF" case
    And I search for the case by its case reference
    Then the created case should be the only case visible in the search results

#     HOCS-2847 HOCS-3161
  @ComplaintsRegression
  Scenario: COMP User sees the required information when viewing search
    Given I am logged into "CS" as user "COMP_USER"
    When I navigate to the "search" page
    And I click the search button on the search page
    Then the "COMP Search" workstack should contain only the expected columns

  Scenario: User is able to select a COMP2 case reference from the escalate case column of a COMP case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 Registration" stage
    And I navigate to the "Search" page
    And I search for the COMP case escalated to COMP2 by it's case reference
    And I load the COMP2 case by selecting its case reference from the Escalate Case column
    Then the case should be loaded