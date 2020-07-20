@Deadlines
Feature: Deadlines

  Background:
    Given I log in to DECS

  Scenario Outline: User creates a case and checks that the stage deadlines are correct
    When I create a "<caseType>" case and move it to the "Markup" stage
    And I load and claim the current case
    Then I check that the stage deadline dates for a "<caseType>" case are correct
    Examples:
    |caseType|
    |MIN     |
    |DTEN    |
    |TRO     |

  Scenario: User creates an MPAM case and checks that the stage deadlines are correct
    When I create a "MPAM" case and move it to the "Creation" stage
    And I load and claim the current case
    Then I check that the stage deadline dates for a "MPAM" case are correct

  Scenario: User assigns a case to the Home Secretary team for sign-off and checks that the stage deadlines are correct
    When I create a "MIN" case and move it to the "Markup" stage
    And I load and claim the current case
    And I complete Markup with "Home Secretary" selected as the Private Office team
    And I load and claim the current case
    Then I check that the stage deadline dates for a "Home Secretary sign-off" case are correct