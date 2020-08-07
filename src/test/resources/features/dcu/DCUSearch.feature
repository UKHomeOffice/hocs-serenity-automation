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

  @SearchByCaseType @DCUSmokeTests
  Scenario Outline: User should be able to search for a case by Case Type
    And I create a single "<caseType>" case
    And I navigate to the "search" page
    When I search by the case type "<caseType>"
    Then only DCU "<caseType>" case type results should be displayed in the results list
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @SearchByCaseType @OtherTests
  Scenario: User should be able to click on the case link when cases are displayed in the results list
    And I create a single "MIN" case
    And I navigate to the "search" page
    When I search by the case type "MIN"
    And I click the case reference link of the first case in the results list
    Then I should be taken directly to the case

  @SearchByCaseType @OtherTests
  Scenario: Results list should contain the Case Reference, Current Stage, Owner, Owning Team and Deadline when searching by Case Type
    And I create a single "MIN" case
    And I navigate to the "search" page
    When I search by the case type "MIN"
    Then the search results should contain the expected information

  @SearchByCaseType @SearchByTopic @OtherTests
  Scenario: User should be able to search by multiple parameters
    And I create a "MIN" case with "Cardiff University Kittens" as the primary topic
    And I search by the case type "MIN" and another parameter "Cardiff University Kittens Topic"
    Then cases that are "MIN" case type that also contain another parameter "Cardiff University Kittens Topic" should be displayed in the results list

  @SearchByDateReceived @DCUSmokeTests
  Scenario: User should be able to search for cases received on or after a certain date
    And I create a single "DTEN" case with the correspondence received date as: "01"-"01"-"2019"
    And I navigate to the "search" page
    When I search for a "DTEN" case received on or after "01"-"01"-"2019"
    And I look for the current case that was received on or after the date searched


  @SearchByDateReceived @DCUSmokeTests
  Scenario: User should be able to search for cases received on or before a certain date
    And I create a single "DTEN" case with the correspondence received date as: "01"-"01"-"2019"
    And I navigate to the "search" page
    When I search for a "DTEN" case received on or before "01"-"01"-"2019"
    And I look for the current case that was received on or before the date searched

  @SearchByDateReceived @OtherTests
  Scenario: No cases should be displayed if a user searches for a date range that contains no cases
    And I navigate to the "search" page
    When I search for cases received on or before "01"-"01"-"1901"
    Then 0 cases should be displayed

  @SearchByCorrespondent @DCUSmokeTests
  Scenario: User should be able to search by correspondent by entering their name
    And I create a "MIN" case with "Nicola Sturgeon" as the correspondent
    And I load and claim the current case
    And I navigate to the "search" page
    When I search by the correspondent name "Nicola Sturgeon"
    Then cases with the queried correspondent name should be displayed in the results list

  @SearchByCorrespondent @OtherTests
  Scenario: No cases should be displayed in the results list if there are no cases with the specified correspondent name
    And I navigate to the "search" page
    When I search by the correspondent name "HUMPTY DUMPTY"
    Then 0 cases should be displayed

  @SearchByTopic @DCUSmokeTests
  Scenario: A case with a certain Primary Topic should be displayed in the search results when that topic is searched for
    And I create a "MIN" case with "Biometrics - general queries" as the primary topic
    And I navigate to the "search" page
    When I search for the topic
    Then the created case should be visible in the search results

  @SearchByTopic @OtherTests
  Scenario: No cases should be displayed in the results list if there are no cases with the specified topic
    And I navigate to the "search" page
    When I search for a made up topic
    Then 0 cases should be displayed


  @SearchByActiveOnly @OtherTests
  Scenario: Both active and closed cases should be displayed when searching without selecting that the results should only
  include active cases
    And I navigate to the "search" page
    When I search for a "MIN" case by the Sign-off Team "Minister for Lords"
    Then both active and closed cases will be returned in the search results

  @SearchByActiveOnly @DCUSmokeTests
  Scenario: Only active cases should be displayed when the user specifies a search should return active cases only
    And I navigate to the "search" page
    And I select active cases
    When I search for a "MIN" case by the Sign-off Team "Minister for Lords"
    Then Only active cases will be returned in the search results

  @SearchBySignOffTeam @DCUSmokeTests
  Scenario: User should be able to search for a case by Sign-off Team
    And I create a "MIN" case with "Hani Al Sibai" as the primary topic
    And I navigate to the "search" page
    When I search for a "MIN" case by the Sign-off Team "Minister of State for Immigration"
    Then cases with the queried Sign-off Team should be displayed in the results list

  @SearchBySignOffMinister @searchByCaseType @OtherTests
  Scenario: User should be able to search by sign off minister and another parameter
    When I search by the case type "DTEN" and another parameter "Permanent Secretary Signoff Team"
    Then cases that are "DTEN" case type that also contain another parameter "Permanent Secretary Signoff Team" should be displayed in the results list

  @SearchByCaseReferenceNumber @DCUSmokeTests
  Scenario Outline: User searches for DCU cases using a substring of a case reference
    And I create a single "<caseType>" case and return to the dashboard
    And I navigate to the "Search" page
    And I search for a case using a random substring of a case reference
    Then the displayed cases all contain the input substring case reference
    Examples:
    |caseType|
    |MIN     |
    |DTEN    |
    |TRO     |

  @SearchByHomeSecInterest @DCUSmokeTests
  Scenario: User searches for cases by Home Secretary Interest
    And I navigate to the "Search" page
    And I search for cases that are of interest to the Home Secretary
    Then the first and last search results are of interest to the Home Secretary