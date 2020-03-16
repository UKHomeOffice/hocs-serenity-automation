Feature: Search should be available for all users of the application

  Background:
    Given I am user "AUTOMATION_USER"

  @Search @SearchByCaseReferenceNumber
  Scenario: User should be be taken directly to a case when they search for the Case Reference number
    When I enter a valid case reference into the load case search bar
    Then I should be taken directly to the case

  @Search @SearchByCaseReferenceNumber @Validation
  Scenario: An error message should be displayed if a user enters a Reference number that does not exist
    When I enter a non-existent case reference
    Then an error message should be displayed stating that there are no active workflows for the case

  @Search @SearchByCaseReferenceNumber @Validation
  Scenario: User must enter a search query in the Load Case search bar
    When I press enter in the Load Case search bar
    Then an error message should be displayed stating that a case reference is required

  @Search @Validation @Ignore
  Scenario: User must enter search criteria when using the search form
    And I navigate to the "search" page
    When I click the search button on the search page
    Then an error message should be displayed as I have not entered any search criteria

  @Search @SearchByCaseType @SmokeTests
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

  @Search @SearchByCaseType
  Scenario: User should be able to click on the case link when cases are displayed in the results list
    And I create a single "MIN" case
    And I navigate to the "search" page
    When I search by the case type "MIN"
    And I click the case reference link of the first case in the results list
    Then I should be taken directly to the case

  @Search @SearchByCaseType
  Scenario: Results list should contain the Case Reference, Current Stage, Owner, Owning Team and Deadline when searching by Case Type
    And I create a single "MIN" case
    And I navigate to the "search" page
    When I search by the case type "MIN"
    Then the search results should contain the expected information

  @Search @SearchByCaseType @SearchByTopic
  Scenario: User should be able to search by multiple parameters
    And I create a "MIN" case with "Cardiff University Kittens" as the primary topic
    And I search by the case type "MIN" and another parameter "Cardiff University Kittens Topic"
    Then cases that are "MIN" case type that also contain another parameter "Cardiff University Kittens Topic" should be displayed in the results list

  @Search @SearchByDateReceived @SmokeTests
  Scenario: User should be able to search for cases received on or after a certain date
    And I create a single "DTEN" case with the correspondence received date as: "01"-"01"-"2019"
    And I navigate to the "search" page
    When I search for a "DTEN" case received on or after "01"-"01"-"2019"
    And I look for the current case that was received on or after the date searched


  @Search @SearchByDateReceived @SmokeTests
  Scenario: User should be able to search for cases received on or before a certain date
    And I create a single "DTEN" case with the correspondence received date as: "01"-"01"-"2019"
    And I navigate to the "search" page
    When I search for a "DTEN" case received on or before "01"-"01"-"2019"
    And I look for the current case that was received on or before the date searched

  @Search @SearchByDateReceived
  Scenario: No cases should be displayed if a user searches for a date range that contains no cases
    And I navigate to the "search" page
    When I search for cases received on or before "01"-"01"-"1901"
    Then 0 cases should be displayed

  @Search @SearchByCorrespondent @SmokeTests
  Scenario: User should be able to search by correspondent by entering their name
    And I create a "MIN" case with "Nicola Sturgeon" as the correspondent
    And I navigate to the "search" page
    When I search by the correspondent name "Nicola Sturgeon"
    Then cases with the queried correspondent name should be displayed in the results list

  @Search @SearchByCorrespondent
  Scenario: No cases should be displayed in the results list if there are no cases with the specified correspondent name
    And I navigate to the "search" page
    When I search by the correspondent name "HUMPTY DUMPTY"
    Then 0 cases should be displayed

  @Search @SearchByTopic @SmokeTests
  Scenario: A case with a certain Primary Topic should be displayed in the search results when that topic is searched for
    And I create a "MIN" case with "Biometrics - general queries" as the primary topic
    And I navigate to the "search" page
    When I search for the topic
    Then the created case should be visible in the search results

  @Search @SearchByTopic
  Scenario: No cases should be displayed in the results list if there are no cases with the specified topic
    And I navigate to the "search" page
    When I search for a made up topic
    Then 0 cases should be displayed


  @Search @SearchByActiveOnly
  Scenario: Both active and closed cases should be displayed when searching without selecting that the results should only
  include active cases
    And I navigate to the "search" page
    When I search for a "MIN" case by the Sign-off Team "Minister for Lords"
    Then both active and closed cases will be returned in the search results

  @Search @SearchByActiveOnly @SmokeTests @Ignore
  Scenario: Only active cases should be displayed when the user searches for a case and specifies that the case should be active
    And I navigate to the "search" page
    And I select active cases
    When I search for a "MIN" case by the Sign-off Team "Minister for Lords"
    Then Only active cases will be returned in the search results

  @Search @SearchBySignOffTeam @SmokeTests
  Scenario: User should be able to search for a case by Sign-off Team
    And I create a "MIN" case with "Cats and Dogs" as the primary topic
    And I navigate to the "search" page
    When I search for a "MIN" case by the Sign-off Team "Minister for Lords"
    Then cases with the queried Sign-off Team should be displayed in the results list

  @Search @SearchBySignOffMinister @searchByCaseType
  Scenario: User should be able to search by sign off minister and another parameter
    When I search by the case type "DTEN" and another parameter "Permanent Secretary Signoff Team"
    Then cases that are "DTEN" case type that also contain another parameter "Permanent Secretary Signoff Team" should be displayed in the results list


