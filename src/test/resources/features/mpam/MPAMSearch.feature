@Search @MPAM
Feature: MPAM Search

  Background:
    Given I am logged into "CS" as user "MPAM_USER"

  Scenario: User searches by case reference from the search page
    And I create a MPAM case with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Triage" stage
    And I navigate to the "Search" page
    And I search for the case by its case reference
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