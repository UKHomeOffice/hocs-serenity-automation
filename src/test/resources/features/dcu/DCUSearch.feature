@Search @DCU
Feature: DCU Search

  @DCURegression
  Scenario: User tests DCU case search criteria
    Given I am logged into "CS" as user "DCU_USER"
    When I generate a "DCU" case to validate search functionality
    And I navigate to the "Search" page
    And I enter "MIN" into the "Case Type" search field
    And I enter "01/01/2021" into the "Received on or Before date" search field
    And I enter "01/01/2021" into the "Received on or After date" search field
    And I enter "Boris Johnson" into the "Member of Parliament Name" search field
    And I enter "Sam McTester" into the "Public Correspondent Name" search field
    And I enter "AB1 2CD" into the "Correspondent Postcode" search field
    And I enter "SamMcTester@Test.com" into the "Correspondent Email Address" search field
    And I enter "Animal alternatives (3Rs)" into the "Topic" search field
    And I enter "Minister for Lords" into the "Sign off team" search field
    And I enter "Yes" into the "Home Secretary Interest" search field
    And I enter "Yes" into the "Active Cases Only" search field
    And I enter the current case reference into the Case Reference field on the search screen
    And I click the search button on the search page
    Then the created case should be the only case visible in the search results

  @SearchByCaseType @Workstacks @DCURegression
  Scenario Outline: DCU Search workstack should contain the Case Reference, Current Stage, Owner, Team, Primary Topic and Deadline
    Given I am logged into "CS" as user "DCU_USER"
    And I create a single "<createCase>" case
    And I navigate to the "search" page
    And I enter "<searchCase>" into the "Case Type" search field
    And I click the search button on the search page
    Then the "DCU Search" workstack should contain only the expected columns
    Examples:
    | createCase | searchCase          |
    | MIN        | MIN                 |
    | TRO        | TRO                 |
    | DTEN       | DTEN                |
    | MIN        | MIN + TRO           |
    | MIN        | MIN + DTEN          |
    | TRO        | TRO + DTEN          |
    | MIN        | All DCU Case Types  |

  @SearchByCaseReferenceNumber @DCURegression
  Scenario Outline: User searches for DCU cases using a substring of a case reference
    Given I am logged into "CS" as user "DECS_USER"
    And I create a single "<caseType>" case and return to the dashboard
    And I navigate to the "Search" page
    And I search for a case using a random substring of a case reference
    Then the displayed cases all contain the input substring case reference
    Examples:
      | caseType |
      | MIN      |
      | DTEN     |
      | TRO      |

  @DCURegression
  Scenario: User is able to search for case that has changed it's primary correspondent
    Given I am logged into "CS" as user "DCU_USER"
    And I create a "MIN" case with "Boris Johnson" as the correspondent
    And I load the current case
    And I add a "Member" correspondent using the People tab
    And I change the primary correspondent of the case
    And I navigate to the "search" page
    And I enter the current case reference into the case reference search field
    And I search for the case by the newly updated primary correspondent
    Then the created case should be the only case visible in the search results