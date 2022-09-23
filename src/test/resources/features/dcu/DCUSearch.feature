@Search @DCU
Feature: DCU Search

  Scenario Outline: User can search for DCU case types
    Given I am logged into "CS" as user "DCU_USER"
    When I navigate to the "Search" page
    And I enter "<infoValue>" into the "Case Type" search field in the "DCU" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "Case Type"
    Examples:
      | infoValue |
      | TRO       |
      | DTEN      |

  @SearchByCaseReferenceNumber
  Scenario: User should be be taken directly to a case when they for enter a valid case reference in the Load Case bar
    Given I am logged into "CS" as user "DCU_USER"
    When I enter a valid case reference into the load case search bar
    Then I should be taken directly to the case

  @SearchByCaseReferenceNumber @Validation
  Scenario: An error message should be displayed if a user enters a Reference number that does not exist
    Given I am logged into "CS" as user "DCU_USER"
    When I enter a non-existent case reference
    Then an error message should be displayed stating that there are no active workflows for the case

  @SearchByCaseReferenceNumber @Validation
  Scenario: User must enter a search query in the Load Case search bar
    Given I am logged into "CS" as user "DCU_USER"
    When I press enter in the Load Case search bar
    Then an error message should be displayed stating that a case reference is required

  @SearchByCaseType
  Scenario: User should be able to click on the case link when cases are displayed in the results list
    Given I am logged into "CS" as user "DCU_USER"
    And I create a single "MIN" case
    And I navigate to the "search" page
    And I enter "MIN" into the "Case Type" search field in the "DCU" search configuration
    And I click the search button on the search page
    And I click the case reference of the case in search results
    Then I should be taken directly to the case

  @SearchByCaseType @Workstacks @DCURegression
  Scenario Outline: DCU Search workstack should contain the Case Reference, Current Stage, Owner, Team, Primary Topic and Deadline
    Given I am logged into "CS" as user "DCU_USER"
    And I create a single "<createCase>" case
    And I navigate to the "search" page
    And I enter "<searchCase>" into the "Case Type" search field in the "DCU" search configuration
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

  @SearchByCaseType @SearchByTopic
  Scenario: User should be able to search by multiple parameters
    Given I am logged into "CS" as user "DCU_USER"
    And I create a "MIN" case with "Animal alternatives (3Rs)" as the primary topic
    And I navigate to the "search" page
    When I enter "MIN" into the "Case Type" search field in the "DCU" search configuration
    And I enter "Animal alternatives (3Rs)" into the "Topic" search field in the "DCU" search configuration
    And I click the search button on the search page
    Then I check that the search results have the correct "Case Type"
    And I check that the search results have the correct "Topic"

  @SearchByDateReceived
  Scenario Outline: No cases should be displayed if a user searches for a criteria that would contain no cases
    Given I am logged into "CS" as user "DCU_USER"
    And I navigate to the "search" page
    When I enter "<infoValue>" into the "<infoType>" search field in the "DCU" search configuration
    And I click the search button on the search page
    Then 0 cases should be displayed
    Examples:
    | infoType                    | infoValue     |
    | Topic                       | Made up Topic |
    | Public Correspondent Name   | Humpty Dumpty |
    | Received on or before date  | 01/01/1901    |

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
    And I add a "Member" correspondent to the case
    And I change the primary correspondent of the case
    And I navigate to the "search" page
    And I enter the current case reference into the case reference search field
    And I search for the case by the newly updated primary correspondent
    Then the created case should be the only case visible in the search results