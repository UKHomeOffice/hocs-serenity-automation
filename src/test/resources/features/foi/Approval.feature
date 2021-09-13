@FOIApproval @FOI @FOIWorkflow
Feature: Approval

  Background:
    Given I am logged into "CS" as user "FOI_USER"
    When I create a "FOI" case and move it to the "Approval" stage

  Scenario: User is able to complete the Approval stage
    And I add an Approval request to the case with the "Complete" status
    And I click the "Continue" button
    And I navigate to the "Dashboard" page
    Then the case should be moved to the "Dispatch" stage
    And the summary should display the owning team as "CCT Stage 1 Triage Team"
    And the read-only Case Details accordion should contain all case information entered during the "Approval" stage

  @FOIRegression
  Scenario Outline: User is able to see the status of Approval requests at the Approval stage
    And I add an Approval request to the case with the "<status>" status
    Then the status of the approval request should be displayed as "<status>"
    Examples:
    | status    |
    | Complete  |
    | Cancelled |
    | Due       |
    | Overdue   |