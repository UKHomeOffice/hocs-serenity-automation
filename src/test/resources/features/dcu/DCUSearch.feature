@Search @DCU
Feature: DCU Search

  Background:
    Given I log in to DECS

  @SearchByCaseReferenceNumber @OtherTests
  Scenario: User should be be taken directly to a case when they search for the Case Reference number
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

  @SearchByCaseType @DCURegression
  Scenario Outline: User should be able to search for a case by Case Type
    And I create a single "<caseType>" case
    And I navigate to the "search" page
    When I enter "<caseType>" into the "Case Type" search criteria for DCU
    And I click the search button on the search page
    Then the "Case Type" of the search results should be "<caseType>"
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @SearchByCaseType @OtherTests
  Scenario: User should be able to click on the case link when cases are displayed in the results list
    And I create a single "MIN" case
    And I navigate to the "search" page
    When I enter "MIN" into the "Case Type" search criteria for DCU
    And I click the search button on the search page
    And I click the case reference link of the first case in the results list
    Then I should be taken directly to the case

  @SearchByCaseType @OtherTests
  Scenario: Results list should contain the Case Reference, Current Stage, Owner, Owning Team and Deadline when searching by Case Type
    And I create a single "MIN" case
    And I navigate to the "search" page
    When I enter "MIN" into the "Case Type" search criteria for DCU
    And I click the search button on the search page
    Then the search results should contain the expected information

  @SearchByCaseType @SearchByTopic @OtherTests
  Scenario: User should be able to search by multiple parameters
    And I create a "MIN" case with "Animal alternatives (3Rs)" as the primary topic
    And I navigate to the "search" page
    And I enter "MIN" into the "Case Type" search criteria for DCU
    And I enter "Animal alternatives (3Rs)" into the "Topic" search criteria for DCU
    And I click the search button on the search page
    Then the "Case Type" of the search results should be "MIN"
    And the "Topic" of the search results should be "Animal alternatives (3Rs)"

  @SearchByDateReceived @DCURegression
  Scenario: User should be able to search for cases received on or after a certain date
    And I create a single "DTEN" case with the correspondence received date as: "22"-"09"-"2020"
    And I navigate to the "search" page
    When I enter "DTEN" into the "Case Type" search criteria for DCU
    And I enter "22/09/2020" into the "Received On Or After Date" search criteria for DCU
    And I click the search button on the search page
    And I look for the current case that was received on or after the date searched


  @SearchByDateReceived @DCURegression
  Scenario: User should be able to search for cases received on or before a certain date
    And I create a single "DTEN" case with the correspondence received date as: "01"-"01"-"2019"
    And I navigate to the "search" page
    When I enter "DTEN" into the "Case Type" search criteria for DCU
    And I enter "01/01/2019" into the "Received On Or Before Date" search criteria for DCU
    And I click the search button on the search page
    And I look for the current case that was received on or before the date searched

  @SearchByDateReceived @OtherTests
  Scenario: No cases should be displayed if a user searches for a date range that contains no cases
    And I navigate to the "search" page
    When I enter "01/01/1901" into the "Received On Or Before Date" search criteria for DCU
    And I click the search button on the search page
    Then 0 cases should be displayed

  @SearchByCorrespondent @DCURegression
  Scenario: User should be able to search by correspondent by entering their name
    And I create a "MIN" case with "Nicola Sturgeon" as the correspondent
    And I navigate to the "search" page
    When I enter "Nicola Sturgeon" into the "Correspondent Name" search criteria for DCU
    And I click the search button on the search page
    Then the "Correspondent Name" of the search results should be "Nicola Sturgeon"

  @SearchByCorrespondent @OtherTests
  Scenario: No cases should be displayed in the results list if there are no cases with the specified correspondent name
    And I navigate to the "search" page
    When I enter "Humpty Dumpty" into the "Correspondent Name" search criteria for DCU
    And I click the search button on the search page
    Then 0 cases should be displayed

  @SearchByTopic @DCURegression
  Scenario: A case with a certain Primary Topic should be displayed in the search results when that topic is searched for
    And I create a "MIN" case with "Breeding of research animals" as the primary topic
    And I navigate to the "search" page
    When I enter "Breeding of research animals" into the "Topic" search criteria for DCU
    And I click the search button on the search page
    Then the created case should be visible in the search results

  @SearchByTopic @OtherTests
  Scenario: No cases should be displayed in the results list if there are no cases with the specified topic
    And I navigate to the "search" page
    When I enter "Made Up Topic" into the "Topic" search criteria for DCU
    And I click the search button on the search page
    Then 0 cases should be displayed


  @SearchByActiveOnly @OtherTests
  Scenario: Both active and closed cases should be displayed when searching without selecting that the results should only
  include active cases
    And I navigate to the "search" page
    When I enter "MIN" into the "Case Type" search criteria for DCU
    And I enter "Minister for Lords" into the "Sign Off Team" search criteria for DCU
    And I click the search button on the search page
    Then both active and closed cases will be returned in the search results

  @SearchByActiveOnly @DCURegression
  Scenario: Only active cases should be displayed when the user specifies a search should return active cases only
    And I navigate to the "search" page
    And I select active cases
    When I enter "MIN" into the "Case Type" search criteria for DCU
    And I enter "Minister for Lords" into the "Sign Off Team" search criteria for DCU
    And I click the search button on the search page
    Then Only active cases will be returned in the search results

  @SearchBySignOffTeam @DCURegression
  Scenario: User should be able to search for a case by Sign-off Team
    And I create a "MIN" case with "Amnesty on illegal immigrants" as the primary topic
    And I navigate to the "search" page
    When I enter "MIN" into the "Case Type" search criteria for DCU
    And I enter "Minister for Immigration Compliance and the Courts" into the "Sign Off Team" search criteria for DCU
    And I click the search button on the search page
    Then cases with the queried Sign-off Team should be displayed in the results list

  @SearchBySignOffMinister @searchByCaseType @OtherTests
  Scenario: User should be able to search by sign off minister and another parameter
    When I navigate to the "search" page
    And I enter "MIN" into the "Case Type" search criteria for DCU
    And I enter "Permanent Secretary" into the "Sign Off Team" search criteria for DCU
    And I click the search button on the search page
    Then the "Case Type" of the search results should be "MIN"
    And the "Sign Off Team" of the search results should be "Permanent Secretary"

  @SearchByCaseReferenceNumber @DCURegression
  Scenario Outline: User searches for DCU cases using a substring of a case reference
    And I create a single "<caseType>" case and return to the dashboard
    And I navigate to the "Search" page
    And I search for a case using a random substring of a case reference
    Then the displayed cases all contain the input substring case reference
    Examples:
      | caseType |
      | MIN      |
      | DTEN     |
      | TRO      |

  @SearchByHomeSecInterest @DCURegression
  Scenario: User searches for cases by Home Secretary Interest
    And I navigate to the "Search" page
    And I enter "Yes" into the "Home Secretary Interest" search criteria for DCU
    And I click the search button on the search page
    Then the "Home Sec Interest" of the search results should be "Yes"