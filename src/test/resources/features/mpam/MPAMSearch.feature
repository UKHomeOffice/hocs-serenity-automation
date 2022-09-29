@MPAM @Search
Feature: MPAM Search

  Background:
    Given I am logged into "CS" as user "MPAM_USER"

  @MPAMRegression2
  Scenario: User tests MPAM search criteria
    When I generate a "MPAM" case to validate search functionality
    And I navigate to the "Search" page
    And I enter "Ministerial" into the "Reference Type" search field
    And I enter "Home Secretary" into the "Ministerial Sign Off Team" search field
    And I enter "Boris Johnson" into the "Member of Parliament Name" search field
    And I enter "Sam McTester" into the "Correspondent full name (applicant or constituent)" search field
    And I enter "Ref-ABCD-1234" into the "Correspondent Reference Number" search field
    And I enter "01/01/2022" into the "Received on or Before Date" search field
    And I enter "01/01/2022" into the "Received on or After Date" search field
    And I enter "Small boats" into the "Campaign" search field
    And I enter the current case reference into the Case Reference field on the search screen
    And I click the search button on the search page
    Then the created case should be the only case visible in the search results

  @MPAMRegression2
  Scenario: User searches for MPAM cases using a substring of a case reference
    And I create a single "MPAM" case and return to the dashboard
    And I navigate to the "Search" page
    And I search for a case using a random substring of a case reference
    Then the displayed cases all contain the input substring case reference

  @Workstacks @MPAMRegression2
  Scenario: MPAM Search workstack should contain the Case Reference, Current Stage, Owner, Team, Deadline, MPs and Correspondents
    And I create a single "MPAM" case
    And I navigate to the "search" page
    And I click the search button on the search page
    Then the "MPAM Search" workstack should contain only the expected columns