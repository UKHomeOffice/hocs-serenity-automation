@Search
Feature: MPAM Search

  Background:
    Given I log in to DECS as user "UKVI_ONLY"

    @Smoketests @MPAMRegression
  Scenario Outline: User tests search criteria
    And I navigate to the "Search" page
    And I search for an MPAM case with "<infoValue>" as it's "<infoType>"
    Then I check that the MPAM search results have the correct "<infoType>"
    Examples:
    |           infoType              |  infoValue  |
    |       Reference Type            |    Ministerial    |
    |       Reference Type            |    Official    |
    |   Member of Parliament Name     |Boris Johnson|
    | Correspondent Reference Number  |TestRefNumber|
      
  Scenario: User searches by case reference from the search page
    And I create a MPAM case  with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Triage" stage
    And I navigate to the "Search" page
    And I search for a case by it's case reference
    Then the one created case should be displayed