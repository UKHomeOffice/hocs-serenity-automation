@Deadlines @CS
Feature: Deadlines

  Background:
    Given I am logged into "CS" as user "DECS_USER"

  @DCURegression
  Scenario Outline: User creates a case and checks that the stage deadlines are correct
    When I create a "<caseType>" case and move it to the "Markup" stage
    And I load and claim the current case
    Then the stage deadline dates for a "<caseType>" case are correct
    Examples:
    |caseType|
    |MIN     |
    |DTEN    |
    |TRO     |

  @UKVIRegression2
  Scenario: User creates an MPAM case and checks that the stage deadlines are correct
    When I create a "MPAM" case and move it to the "Creation" stage
    And I load and claim the current case
    Then the stage deadline dates for a "MPAM" case are correct

  @DCURegression
  Scenario: User assigns a case to the Home Secretary team for sign-off and checks that the stage deadlines are correct
    When I create a "MIN" case and move it to the "Markup" stage
    And I load and claim the current case
    And I complete Markup with "Home Secretary" selected as the Private Office team
    And I load and claim the current case
    Then the stage deadline dates for a "Home Secretary sign-off" case are correct

  @DCURegression
  Scenario: User changes Minister from Home Secretary to another PO Team at Private office stage
    When I create a "MIN" case and move it to the "Markup" stage
    And I load and claim the current case
    And I complete Markup with "Home Secretary" selected as the Private Office team
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I load and claim the current case
    And I change the minister to "Minister for Lords"
    And I load and claim the current case
    Then the stage deadline dates for a "MIN" case are correct