@CCH @TO
Feature: CCH Returns

  Background:
    Given I am logged into "CS" as user "TO_USER"
    When I get a TO case with "HMPO" as the business area and move the case to the "CCH Returns" stage
    And I load and claim the current case

  Scenario: As a CCH User, I want to be able to close a case, so that case working stops on cases that don't require case working
    When I select to close the case at CCH Returns
    And I complete the required information on the Close Case screen at CCH Returns
    Then the case should be closed
    And a Case closure note should be visible in the timeline showing the submitted reason for closing the case
    And the read-only Case Details accordion should contain all case information entered during the "Early Closure" stage

  Scenario Outline: As a CCH User, I want to be able to transfer the case to a Business Area, so that the correct team can casework it
    When I select to transfer the case to a new business area at CCH Returns
    And I select "<businessArea>" as the new business area of the case
    Then the case should be moved to the "Triage" stage
    And the case should be moved to the correct Treat Official team for the new business area
    Examples:
    | businessArea  |
    | UKVI          |
    | HMPO          |
    | BF            |