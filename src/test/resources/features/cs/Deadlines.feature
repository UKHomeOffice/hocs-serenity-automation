@Deadlines @CS
Feature: Deadlines

  Background:
    Given I am logged into "CS" as user "DECS_USER"

  @CSRegression
  Scenario Outline: As a DCU user, when I create a MIN or TRO case, I want to be able to see the correct stage and case deadlines in the summary
    When I create a single "<caseType>" case
    And I go to the case from the successful case creation screen
    Then the stage deadline dates displayed in the summary are correct for a "<caseType>" case
    Then the case deadline date displayed in the summary is correct for a "<caseType>" case
    Examples:
      | caseType |
      | MIN      |
      | TRO      |

  Scenario: As a DCU user, when I create a DTEN case and enter the deadlines, I want to be able to see these deadlines in the summary
    When I create a "DTEN" case and move it to the "Markup" stage
    And I load the current case
    Then the stage deadline dates displayed in the summary are correct for a "DTEN" case
    Then the case deadline date displayed in the summary is correct for a "DTEN" case

  @CSRegression
  Scenario Outline: As a CS user, when I create a case, I want to be able to see the correct case deadline in the summary
    When I create a single "<caseType>" case
    And I go to the case from the successful case creation screen
    Then the case deadline date displayed in the summary is correct for a "<caseType>" case
    Examples:
      | caseType |
      | MPAM     |
      | MTS      |
      | COMP     |
      | COMP2    |
      | IEDET    |
      | TO       |
      | BF       |
      | BF2      |
      | POGR     |

  @DCURegression
  Scenario: As a DCU User, when I select that the Home Secretary is the Private Office team, I expect the cases deadlines to reflect a 10 day SLA
    When I create a "MIN" case and move it to the "Markup" stage
    And I load and claim the current case
    And I complete Markup with "Home Secretary" selected as the Private Office team
    And I load and claim the current case
    Then the stage and case deadlines have altered to those for a 10 day SLA

  @DCURegression
  Scenario: As a DCU User, when I change the Private Office team to no longer be Home Secretary, I expect the case to revert to a 20 day SLA
    When I get a "MIN" case at the "Markup" stage
    And I complete Markup with "Home Secretary" selected as the Private Office team
    And I complete the "Initial Draft" stage
    And I complete the "QA response" stage
    And I load and claim the current case
    And I change the minister to "Minister for Lords"
    Then the stage and case deadlines revert back to those for a 20 day SLA

#    Expected failure. Defect HOCS-4230 raised.
  @COMPRegression
  Scenario: As a UKVI Complaints user, when I have a Ex-Gratia complaint, I expect the deadline to be 60 working days
    When I create a "COMP" case for a "Ex-Gratia" complaint and move it to "Ex-Gratia Triage" stage
    And I load the current case
    Then the case deadline date displayed in the summary is correct for a "Ex-Gratia" case

  @COMPRegression
  Scenario Outline: As a GRO Complaints user, when I submit details about a Stage 1 complaint, I expect the deadline to be correct
    And I get a "POGR" case at the "Data Input" stage
    And I select "GRO" as the business area for the POGR case
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    When I record that the GRO case is a "<deadlineDefiningFactor>"
    Then the case deadline date displayed in the summary is correct for a "<deadlineDefiningFactor>" case
    Examples:
      | deadlineDefiningFactor              |
      | Priority GRO complaint              |
      | non-Priority, non-Post GRO complaint |
      | non-Priority, Post GRO complaint    |

  @COMPRegression
  Scenario Outline: As a GRO Complaints user, when I escalate a Stage 1 complaint to Stage 2, I expect the escalated case to have the correct deadline
    And I get a "POGR" case at the "Data Input" stage
    And I select "GRO" as the business area for the POGR case
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I record that the GRO case is a "<deadlineDefiningFactor>"
    And I withdraw the case
    And I escalate the closed case to Stage 2
    Then the Stage 2 case deadline date displayed in the summary is correct for a "<deadlineDefiningFactor>" case
    Examples:
      | deadlineDefiningFactor              |
      | Priority GRO complaint              |
      | non-Priority, non-Post GRO complaint |
      | non-Priority, Post GRO complaint    |
