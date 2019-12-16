Feature: Search should be available for all users of the application

  Background:
    Given I log in as the designated user

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

  @Search @Validation
  Scenario: User must enter search criteria when using the search form
    And I navigate to the "search" page
    When I click the search button on the search page
    Then an error message should be displayed as I have not entered any search criteria

  @Search @SearchByCaseType
  Scenario Outline: User should be able to search for a case by Case Type
    And I navigate to the "search" page
    When I search by the case type "<caseType>"
    Then only the chosen "<caseType>" case type results should be displayed in the results list
    Examples:
      | caseType |
      | Min      |
      | TRO      |
      | DTEN     |

  @Search @SearchByCaseType
  Scenario: User should be able to click on the case link when cases are displayed in the results list
    And I navigate to the "search" page
    When I search by the case type "Min"
    And I click the case reference link of the first case in the results list
    Then I should be taken directly to the case

  @Search @SearchByCaseType
  Scenario: Results list should contain the Case Reference, Current Stage, Owner, Owning Team and Deadline when
  searching by Case Type
    And I navigate to the "search" page
    When I search by the case type "Min"
    Then the search results should contain the expected information

  @Search @SearchByCaseType
  Scenario: User should be able to search by Case Type and another parameter
    And I search by the case type "MIN" and another parameter "KITTENS TOPIC"
    Then cases that are "MIN" case type that also contain another parameter "KITTENS TOPIC" should be displayed in the results list

  @Search @SearchByDateReceived
  Scenario: User should be able to search for cases received on or after a certain date
    And I navigate to the "search" page
    When I search for cases received on or after "01"/"11"/"2019"
    Then cases received on or "after" "01/11/2019" should be displayed

  @Search @SearchByDateReceived
  Scenario: User should be able to search for cases received on or before a certain date
    And I navigate to the "search" page
    When I search for cases received on or before "01"/"11"/"2019"
    Then cases received on or "before" "01/11/2019" should be displayed

  @Search @SearchByDateReceived
  Scenario: No cases should be displayed if a user searches for a date range that contains no cases
    And I navigate to the "search" page
    When I search for cases received on or before "01"/"01"/"1901"
    Then 0 cases should be displayed

  @Search @SearchByCorrespondent
  Scenario: User should be able to search by correspondent by entering their name
    And I navigate to the "search" page
    When I search by the correspondent name "NICOLA"
    Then cases with the queried correspondent name should be displayed in the results list

  @Search @SearchByCorrespondent
  Scenario: No cases should be displayed in the results list if there are no cases with the specified correspondent name
    And I navigate to the "search" page
    When I search by the correspondent name "HUMPTY DUMPTY"
    Then 0 cases should be displayed

  @Search @SearchByTopic
  Scenario: A case with a certain Primary Topic should be displayed in the search results when that topic is searched for
    And I create a "DCU MIN" case with "Biometrics - general queries" as the primary topic
    And I click the "finish" button
    And I navigate to the "search" page
    When I search for the topic "Biometrics - general queries"
    Then the created case should be visible in the search results

  @Search @SearchByTopic
  Scenario: No cases should be displayed in the results list if there are no cases with the specified topic
    And I navigate to the "search" page
    When I search for the topic "Made up topic"
    Then 0 cases should be displayed


  @Search @SearchByActiveClosed
  Scenario: Both active and closed cases should be displayed when searchnig without selecting that the results should only include active cases
    And I navigate to the "search" page
    When I search by the case type "DCU MIN"
    Then both active and closed cases will be returned in the search results

  @Search @SearchByActiveClosed
  Scenario: Only active cases should be displayed when the user searches for a case and specifies that the case should be active
    And I navigate to the "search" page
    And I select active cases
    When I search by the case type "DCU MIN"
    Then Onlny active cases will be returned in the search results

  @Search @SearchByActiveClosed
  Scenario: Only closed cases should be displayed when the user searches for a case and specifies that the case should be closed
    And I search for any other parameter
    When I specify closed in the search query
    Then all closed cases matching this parameter will be displayed in the search results

  @Search @SearchBySignOffMinister
  Scenario: Users in the private office should be able to search by sign off minister
    And I am a user in the private office
    When I search by <signOffMinister>
    Then no cases with the <signOffMinister> should be displayed in the results list

  @Search @SearchBySignOffMinister
  Scenario: No cases should be shown if the user searches for a sign off minister that has not been assigned to any cases
    And I am a user in the private office
    When I search by <signOffMinister>
    Then no cases should be displayed in the results list if there are no cases with <signOffMinister>

  @Search @SearchBySignOffMinister
  Scenario: User should be able to click on the case link when cases are displayed in the results list after search by sign off minister
    When I search by the <signOffMinister>
    And I click the link of a case in the results list
    Then I should be taken to the screen of the selected case

  @Search @SearchBySignOffMinister
  Scenario: Results list should contain the Case Reference, Current Stage, Owner, Owning Team and Deadline when searching by sign off minister
    When I search by the <signOffMinister>
    Then the search results should contain the expected information

  @Search @SearchBySignOffMinister
  Scenario: User should be able to search by sign off minister and another parameter
    When I search by the <signOffMinister> and <anotherParameter>
    Then cases that are <signOffMinister> that also contain <anotherParameter> should be displayed in the results list

  @Search @SearchBySignOffMinister
  Scenario: No cases should be displayed if the user searches by sign off minister and another parameter when a case does not exist with both parameters
    When I search by the <signOffMinister> and <anotherParameter>
    Then there should be no cases displayed in the results list if no cases match the search parameters



