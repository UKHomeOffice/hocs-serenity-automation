@Search @MPAM
Feature: MPAM Search

  Background:
    Given I log in to DECS as user "UKVI_ONLY"

  @MPAMRegression
  Scenario Outline: User tests search criteria
    And I navigate to the "Search" page
    And I search for an MPAM case with "<infoValue>" as it's "<infoType>"
    Then I check that the MPAM search results have the correct "<infoType>"
    Examples:
      | infoType                       | infoValue        |
      | Reference Type                 | Ministerial      |
      | Reference Type                 | Official         |
      | Member of Parliament Name      | Boris Johnson    |
      | Correspondent Reference Number | TestRefNumber1   |
      | Campaign                       | Small boats      |
      | Ministerial Sign Off Team      | Home Secretary   |
      | Public Correspondent Name      | TestApplicant    |

  Scenario: User is able to search for a case by the Correspondent Reference Number
    And I create a "MPAM" case and add a correspondent with the correspondent reference number "TestRefNumber"
    And I navigate to the "search" page
    And I search for an MPAM case with "TestRefNumber 1" as it's "Correspondent Reference Number"
    Then I check that the MPAM search results have the correct "Correspondent Reference Number"

  @OtherTests
  Scenario: User searches by case reference from the search page
    And I create a MPAM case  with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Triage" stage
    And I navigate to the "Search" page
    And I search for a case by it's case reference
    Then the one created case should be displayed

  @MPAMRegression
  Scenario: User searches for MPAM cases using a substring of a case reference
    And I create a single "MPAM" case and return to the dashboard
    And I navigate to the "Search" page
    And I search for a case using a random substring of a case reference
    Then the displayed cases all contain the input substring case reference

  Scenario: User is able to search for an MTS case that is and official engagement
    And I navigate to the "Search" page
    And I search for an MPAM case with "Yes" as it's "Telephone Surgery Official Engagement"
    Then I check that the MPAM search results have the correct "Telephone Surgery Official Engagement"