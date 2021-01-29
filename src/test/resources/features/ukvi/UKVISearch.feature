@Search @UKVI
Feature: UKVI Search

  Background:
    Given I log in to DECS as user "UKVI_USER"

  @UKVIRegression
  Scenario Outline: User tests search criteria
    And I navigate to the "Search" page
    And I search for an UKVI case with "<infoValue>" as it's "<infoType>"
    Then I check that the UKVI search results have the correct "<infoType>"
    Examples:
      | infoType                              | infoValue      |
      | Reference Type                        | Ministerial    |
      | Reference Type                        | Official       |
      | Member of Parliament Name             | Boris Johnson  |
      | Correspondent Reference Number        | TestRefNumber1 |
      | Campaign                              | Small boats    |
      | Ministerial Sign Off Team             | Home Secretary |
      | Public Correspondent Name             | TestApplicant  |
      | Telephone Surgery Official Engagement | Yes            |

  Scenario: User is able to search for a case by the Correspondent Reference Number
    And I create a "MPAM" case and add a correspondent with the correspondent reference number "TestRefNumber"
    And I navigate to the "search" page
    And I search for an UKVI case with "TestRefNumber 1" as it's "Correspondent Reference Number"
    Then I check that the UKVI search results have the correct "Correspondent Reference Number"

  @OtherTests
  Scenario: User searches by case reference from the search page
    And I create a MPAM case  with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Triage" stage
    And I navigate to the "Search" page
    And I search for a case by it's case reference
    Then the one created case should be displayed

  @UKVIRegression
  Scenario: User searches for MPAM cases using a substring of a case reference
    And I create a single "MPAM" case and return to the dashboard
    And I navigate to the "Search" page
    And I search for a case using a random substring of a case reference
    Then the displayed cases all contain the input substring case reference

  Scenario: User is able to search for an MTS case that is and official engagement
    And I navigate to the "Search" page
    And I search for an UKVI case with "Yes" as it's "Telephone Surgery Official Engagement"
    Then I check that the UKVI search results have the correct "Telephone Surgery Official Engagement"

  @SearchByCaseType @Workstacks @DCURegression
  Scenario: MPAM Search workstack should contain the Case Reference, Current Stage, Owner, Team, Deadline, MPs and Correspondents
    And I create a single "MPAM" case
    And I navigate to the "search" page
    And I click the search button on the search page
    Then the search results should contain the expected information