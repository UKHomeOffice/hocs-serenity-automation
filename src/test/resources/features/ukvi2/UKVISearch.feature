@Search @UKVI
Feature: UKVI Search

  Background:
    Given I am logged into "DECS" as user "UKVI_USER"

  @UKVIRegression
  Scenario Outline: User tests UKVI search criteria
    When I create a "UKVI" case with "<infoValue>" as its "<infoType>"
    And I navigate to the "Dashboard" page
    And I navigate to the "Search" page
    And I enter "<infoValue>" into the "<infoType>" UKVI search criteria
    And I click the search button on the search page
    Then I check that the UKVI search results have the correct "<infoType>"
    Examples:
      | infoType                              | infoValue      |
      | Reference Type                        | Ministerial    |
      | Reference Type                        | Official       |
      | Member of Parliament Name             | Boris Johnson  |
      | Correspondent Reference Number        | TestRefNumber1 |
      | Campaign                              | Small boats    |
      | Ministerial Sign Off Team             | Home Secretary |
      | Public Correspondent Name             | Sam McTester   |

  @OtherTests
  Scenario: User searches by case reference from the search page
    And I create a MPAM case with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Triage" stage
    And I navigate to the "Search" page
    And I search for a case by it's case reference
    Then the created MPAM case should be visible in the search results

  @UKVIRegression
  Scenario: User searches for MPAM cases using a substring of a case reference
    And I create a single "MPAM" case and return to the dashboard
    And I navigate to the "Search" page
    And I search for a case using a random substring of a case reference
    Then the displayed cases all contain the input substring case reference

  @SearchByCaseType @Workstacks @DCURegression
  Scenario: UKVI Search workstack should contain the Case Reference, Current Stage, Owner, Team, Deadline, MPs and Correspondents
    And I create a single "MPAM" case
    And I navigate to the "search" page
    And I click the search button on the search page
    Then the "UKVI Search" workstack should contain only the expected columns
