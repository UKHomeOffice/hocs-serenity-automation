@ComplaintsQA @Complaints
Feature: Complaints QA

  Background:
    Given I am logged into "CS" as user "COMP_USER"

#    HOCS-3695
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can accept the response and send the case to Service Send stage
    When I create a "COMP" case and move it to the "Service QA" stage
    And I load and claim the current case
    When I "accept" the response at the Service QA stage
    Then the case should be moved to the "Service Send" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Service QA" stage

#    HOCS-3039
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can reject the response and send the case back to Service Draft stage
    When I create a "COMP" case and move it to the "Service QA" stage
    And I load and claim the current case
    When I "reject" the response at the Service QA stage
    Then the case should be moved to the "Service Draft" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And a rejection note should be visible showing the reason for rejection
    And the read-only Case Details accordion should contain all case information entered during the "Service QA" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can accept the response and send the case to Ex-Gratia Send stage
    When I create a "COMP" case and move it to the "Ex-Gratia QA" stage
    And I load and claim the current case
    And I "accept" the response at the Service QA stage
    Then the case should be moved to the "Ex-Gratia Send" stage
    And the summary should display the owning team as "Ex-Gratia"

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can reject the response and send the case back to Ex-Gratia Response Draft stage
    When I create a "COMP" case and move it to the "Ex-Gratia QA" stage
    And I load and claim the current case
    And I "reject" the response at the Service QA stage
    Then the case should be moved to the "Ex-Gratia Response Draft" stage
    And the summary should display the owning team as "Ex-Gratia"
    And a rejection note should be visible showing the reason for rejection

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can accept the response and send the case to Minor Misconduct Send stage
    When I create a "COMP" case and move it to the "Minor Misconduct QA" stage
    And I load and claim the current case
    And I "accept" the response at the Service QA stage
    Then the case should be moved to the "Minor Misconduct Send" stage
    And the summary should display the owning team as "Minor Misconduct"

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can reject the response and send the case back to Minor Misconduct Response Draft stage
    When I create a "COMP" case and move it to the "Minor Misconduct QA" stage
    And I load and claim the current case
    And I "reject" the response at the Service QA stage
    Then the case should be moved to the "Minor Misconduct Response Draft" stage
    And the summary should display the owning team as "Minor Misconduct"
    And a rejection note should be visible showing the reason for rejection

  @Validation
  Scenario Outline: User tests the validation at the Service QA stage
    When I create a "COMP" case and move it to the "Service QA" stage
    And I load and claim the current case
    And I trigger the "<errorType>" error message at the "Service QA" stage
    Then the "<errorType>" error message is displayed at the "Service QA" stage
    Examples:
      | errorType                 |
      | QA RESULT REQUIRED        |
      | REJECTION REASON REQUIRED |