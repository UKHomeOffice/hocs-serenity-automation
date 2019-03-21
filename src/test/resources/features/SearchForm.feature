Feature: Search should be available for all users of the application

  Background:
    Given I am user "EAMON"
#    When I am on the "SEARCH" page

  @SearchForm @SearchByCaseReferenceNumber
  Scenario: User should be be taken directly to a case when they search for the Case Reference number
    When I enter a valid search query
    Then I should be taken directly to the case

  @SearchForm @SearchByCaseReferenceNumber
  Scenario: User should be taken directly to the case when they enter the Case Reference number if they have permission to view the case
    And I have permissions to view the case
    When I enter a search query <query>
    Then I should be taken directly to the case

  @SearchForm @SearchByCaseReferenceNumber
  Scenario: An error message should be displayed if a user searches for a case using the Reference number which they do not have permission to view
    And I do not have permissions to view the case
    When I search for a case which exists
    Then an error message should be displayed stating <errorMessage>

  @SearchForm @SearchByCaseReferenceNumber
  Scenario: An error message should be displayed if a user enters a Reference number that does not exist
#    And I have permissions to view the case
    When I enter a non-existent case reference
    Then an error message should be displayed stating that there are no active workflows for the case

  @SearchForm @SearchByCaseReferenceNumber
  Scenario: User must enter a search query in the case reference search bar
    When I click the find button
    Then an error message should be displayed stating that a case reference is required

  @SearchForm @SearchByCaseType
  Scenario: User should be able to search for a case by Case Type
    And I search by the <caseType>
    Then only <caseType> results should be displayed in the results list

  @SearchForm @SearchByCaseType
  Scenario: If a user searches for a Case Type that does not exist, no results should be displayed
    And I search by <caseTypeThatHasNotBeenBuiltYet>
    Then no results should be displayed as there are no cases with the <caseTypeThatHasNotBeenBuiltYet>

  @SearchForm @SearchByCaseType
  Scenario: User with permissions to view the case they search for should have the cases displayed in the list when searching by Case Type
    And I have permissions to view the case
    When I search by <caseType>
    Then cases with <caseType> should be displayed if the user has permissions to access them

  @SearchForm @SearchByCaseType
  Scenario: User should be able to click on the case link when cases are displayed in the results list after search by Case Type
    When I search by the <caseType>
    And I click the link of a case in the results list
    Then I should be taken to the screen of the selected case

  @SearchForm @SearchByCaseType
  Scenario: Results list should contain the Case Reference, Current Stage, Owner, Owning Team and Deadline when searching by Case Type
    And I search by the <caseType>
    Then the search results should contain the expected information

  @SearchForm @SearchByCaseType
  Scenario: User should be able to search by Case Type and another parameter
    And I search by the <caseType> and <anotherParameter>
    Then cases that are <caseType> that also contain <anotherParameter> should be displayed in the results list

  @SearchForm @SearchByCaseType
  Scenario: No cases should be displayed if the user searches by Case Type and another parameter when a case does not exist with both parameters
    And I search by the <caseType> and <anotherParameter>
    Then there should be no cases displayed in the results list if no cases match the search parameters

  @SearchForm @SearchByDateReceived
  Scenario: User should be able to search by date received
    And I search by the <dateReceived>
    Then only cases with <dateReceived> should be displayed in the results list

  @SearchForm @SearchByDateReceived
  Scenario: User should be able to search within a specified date range
    And I search by the <dateRange>
    Then only cases within the <dateRange> should be displayed in the results list

  @SearchForm @SearchByDateReceived
  Scenario: No cases should be displayed if a user searches for a date with no cases received on that date
    And I search by the <dateReceived>
    Then no cases should be displayed if there are no cases within the specified <dateReceived>

  @SearchForm @SearchByDateReceived
  Scenario: No cases should be displayed if a user searches for cases within a specific date range if no cases exist within this range
    And I search by <dateRange>
    Then no cases should be displayed if there are no cases within the specified <dateRange>

  @SearchForm @SearchByDateReceived
  Scenario: User with permissions to view cases with their specified date received should be able to see these cases in the results list when searching for the specified date received
    And I have permissions to view the case
    When I search by <dateReceived>
    Then cases with <dateReceived> should be displayed if the user has permissions to access them

  @SearchForm @SearchByDateReceived
  Scenario: User with permissions to view cases within their specified date range should able to see these cases in the results list when searching for the specified date range
    And I have permissions to view the case
    When I search by <dateRange>
    Then cases within the <dateRange> should be displayed if the user has permissions to access them

  @SearchForm @SearchByDateReceived
  Scenario: User should be able to click on the case link when cases are displayed in the results list after search by date received
    When I search by <dateReceived>
    And I click the link of a case in the results list
    Then I should be taken directly to the case

  @SearchForm @SearchByDateReceived
  Scenario: User should be able to click on the case link when cases are displayed in the results list after search within a date range
    When I search by <dateRange>
    And I click the link of a case in the results list
    Then I should be taken directly to the case

  @SearchForm @SearchByDateReceived
  Scenario: Results list should contain the Case Reference, Current Stage, Owner, Owning Team and Deadline when searching by date received
    And I search by the <dateReceived>
    Then the search results should contain the expected information

  @SearchForm @SearchByDateReceived
  Scenario: Results list should contain the Case Reference, Current Stage, Owner, Owning Team and Deadline when searching within a date range
    And I search within a <dateRange>
    Then the search results should contain the expected information

  @SearchForm @SearchByDateReceived
  Scenario: User should be able to search by date received and another parameter
    And I search by the <dateReceived> and <anotherParameter>
    Then cases that are <dateReceived> that also contain <anotherParameter> should be displayed in the results list

  @SearchForm @SearchByDateReceived
  Scenario: User should be able to search within a date range and with another parameter
    And I search within a <dateRange> and <anotherParameter>
    Then cases that are within the specified <dateRange> that also contain <anotherParameter> should be displayed in the results list

  @SearchForm @SearchByDateReceived
  Scenario: No cases should be displayed if the user searches by date received and another parameter when a case does not exist with both parameters
    And I search by <dateReceived> and <anotherParameter>
    Then there should be no cases displayed in the results list if no cases match the search parameters

  @SearchForm @SearchByDateReceived
  Scenario: No cases should be displayed if the user searches within a date range and another parameter when a case does not exist with both parameters
    And I search within a <dateRange> and <anotherParamter>
    Then there should be no cases displayed in the results list if no cases match the search parameters

  @SearchForm @SearchByCorrespondent
  Scenario: User should be able to search by correspondent either by select a member of a house from a drop down list or by entering a name
    And I search by <correspondentName>
    Then cases with specified <correspondentName> should be displayed in the results list

  @SearchForm @SearchByCorrespondent
  Scenario: No cases should be displayed in the results list if there are no cases with the specified correspondent name
    And I search by <correspondentName>
    Then no results should be displayed as there are no cases with the specified <correspondentName>

  @SearchForm @SearchByCorrespondent
  Scenario: User with permissions to view cases with their specified correspondent name should have the cases displayed when searching for that correspondent name
    And I have permissions to view the case
    When I search by <correspondentName>
    Then cases with that <correspondentName> should be displayed if the user has permissions to access them

  @SearchForm @SearchByCorrespondent
  Scenario: User should be able to click on the case link when cases are displayed in the results list after search by correspondent name
    When I search by <correspondentName>
    And I click the link of a case in the results list
    Then I should be taken to the screen of the selected case

  @SearchForm @SearchByCorrespondent
  Scenario: Results list should contain the Case Reference, Current Stage, Owner, Owning Team and Deadline when searching by correspondent name
    And I search by <correspondentName>
    Then the search results should contain the expected information

  @SearchForm @SearchByCorrespondent
  Scenario: User should be able to search by correspondent name and another parameter
    And I search by <correspondentName> and <anotherParameter>
    Then cases that are <correspondentName> that also contain <anotherParameter> should be displayed in the results list

  @SearchForm @SearchByCorrespondent
  Scenario: No cases should be displayed if the user searches by correspondent name and another parameter when a case does not exist with both parameters
    And I search by <correspondentName> and <anotherParameter>
    Then there should be no cases displayed in the results list if no cases match the search parameters

  @SearchForm @SearchByTopic
  Scenario: Users in the Central Drafting Team should see only see cases overriden to the Central Drafting Team when searching by topic
    And I am in the Central Drafting Team
    When I search for a topic
    Then only cases which have been overridden to the Central Draft Team for Drafting will be shown

  @SearchForm @SearchByTopic
  Scenario: Users in the Performance Process Team should see only see cases overriden to the Performance Process Team when searching by topic
    And I am in the Performance Process Team
    When I search for a topic
    Then no search results should be shown

  @SearchForm @SearchByTopic
  Scenario: Users in the Animals in Science Unit should see only see cases overriden to the Animals in Science Unit when searching by topic
    And I am in the Animals in Science Unit
    When I search for a topic <fromAnimalsInScienceUnit>
    Then all cases with this topic will be shown in the search results

  @SearchForm @SearchByTopic
  Scenario: Users in private office team should see only see cases overriden to the private office team when searching by topic
    And I am in the <privateOfficeTeam>
    When I search for a topic <fromPrivateOfficeTeam>
    Then all cases with this topic will be shown in the search results

  @SearchForm @SearchByTopic
  Scenario: No cases should be shown if I am a user in the Animals in Science Unit and I search for a case not in the Animals in Science Unit
    And I am in the Animals in Science Unit
    When I search for a topic <notFromAnimalsInScienceUnit>
    Then no cases will be shown in the search results

  @SearchForm @SearchByTopic
  Scenario: No cases should be shown if I am a user in the private office team and I search for a case not in the private office team
    And I am in the <privateOfficeTeam>
    When I search for a topic <notFromPrivateOfficeTeam>
    Then no cases will be shown in the search results

  @SearchForm @SearchByActiveClosed
  Scenario: Both active and closed cases should be displayed when the user search for a case without specifying whether the results should be active or closed
    And I search for any other parameter
    When I do not specify active or closed in the search query
    Then all active and closed cases matching this parameter will be displayed in the search results

  @SearchForm @SearchByActiveClosed
  Scenario: Only active cases should be displayed when the user searches for a case and specifies that the case should be active
    And I search for any other parameter
    When I specify active in the search query
    Then all active cases matching this parameter will be displayed in the search results

  @SearchForm @SearchByActiveClosed
  Scenario: Only closed cases should be displayed when the user searches for a case and specifies that the case should be closed
    And I search for any other parameter
    When I specify closed in the search query
    Then all closed cases matching this parameter will be displayed in the search results

  @SearchForm @SearchBySignOffMinister
  Scenario: Users in the private office should be able to search by sign off minister
    And I am a user in the private office
    When I search by <signOffMinister>
    Then no cases with the <signOffMinister> should be displayed in the results list

  @SearchForm @SearchBySignOffMinister
  Scenario: No cases should be shown if the user searches for a sign off minister that has not been assigned to any cases
    And I am a user in the private office
    When I search by <signOffMinister>
    Then no cases should be displayed in the results list if there are no cases with <signOffMinister>

  @SearchForm @SearchBySignOffMinister
  Scenario: User should be able to click on the case link when cases are displayed in the results list after search by sign off minister
    When I search by the <signOffMinister>
    And I click the link of a case in the results list
    Then I should be taken to the screen of the selected case

  @SearchForm @SearchBySignOffMinister
  Scenario: Results list should contain the Case Reference, Current Stage, Owner, Owning Team and Deadline when searching by sign off minister
    When I search by the <signOffMinister>
    Then the search results should contain the expected information

  @SearchForm @SearchBySignOffMinister
  Scenario: User should be able to search by sign off minister and another parameter
    When I search by the <signOffMinister> and <anotherParameter>
    Then cases that are <signOffMinister> that also contain <anotherParameter> should be displayed in the results list

  @SearchForm @SearchBySignOffMinister
  Scenario: No cases should be displayed if the user searches by sign off minister and another parameter when a case does not exist with both parameters
    When I search by the <signOffMinister> and <anotherParameter>
    Then there should be no cases displayed in the results list if no cases match the search parameters



