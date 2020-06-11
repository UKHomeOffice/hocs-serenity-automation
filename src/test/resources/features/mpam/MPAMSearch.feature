@Search
Feature: MPAM Search

  Background:
    Given I log in to DECS as user "UKVI_ONLY"

  Scenario Outline: User tests search criteria
    And I navigate to the "Search" page
    And I search for an MPAM case with "<infoValue>" as it's "<infoType>"
    Then I check that the MPAM search results have the correct "<infoType>"
    Examples:
    |           infoType            |  infoValue  |
  ##  |       Reference Type          |    M:Ref    |
  ##  |       Reference Type          |    B:Ref    |
    |   Member of Parliament Name   |Boris Johnson|
  ##  | Correspondent Reference Number|TestRefNumber|