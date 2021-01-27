@Search @UKVI
Feature: UKVI Search

  Background:
    Given I log in to DECS as user "UKVI_ONLY"

  @UKVIRegression
  Scenario Outline: User tests UKVI search criteria
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
      | Public Correspondent Name             | TestApplicant  |
      | Telephone Surgery Official Engagement | Yes            |

  Scenario: User is able to search for a case by the Correspondent Reference Number
    And I create a "MPAM" case and add a correspondent with the correspondent reference number "TestRefNumber"
    And I navigate to the "search" page
    And I search for a UKVI case with "TestRefNumber 1" as its "Correspondent Reference Number"
    Then I check that the UKVI search results have the correct "Correspondent Reference Number"

  @OtherTests
  Scenario: User searches by case reference from the search page
    And I create a MPAM case  with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Triage" stage
    And I navigate to the "Search" page
    And I search for a case by it's case reference
    Then the created MPAM case should be visible in the search results

  @UKVIRegression
  Scenario: User searches for MPAM cases using a substring of a case reference
    And I create a single "MPAM" case and return to the dashboard
    And I navigate to the "Search" page
    And I search for a case using a random substring of a case reference
    Then the displayed cases all contain the input substring case reference

  Scenario: User is able to search for an MTS case that is an official engagement
    And I navigate to the "Search" page
    And I search for a UKVI case with "Yes" as its "Telephone Surgery Official Engagement"
    Then I check that the UKVI search results have the correct "Telephone Surgery Official Engagement"