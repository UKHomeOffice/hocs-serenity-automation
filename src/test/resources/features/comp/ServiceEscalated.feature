@ServiceEscalated @COMP
Feature: Service Escalated

  Background:
    Given I am logged into "DECS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Escalated" stage
    And I load and claim the current case

  @COMPWorkflow @COMPRegression
  Scenario: User can return the case to Service Triage stage
    And I select to return the case to Triage
    Then the case should be moved to the "Service Triage" stage
    And the summary should display the owning team as "CCT Stage 1 Triage Team"

  @COMPWorkflow @COMPRegression
  Scenario: User can return the case to Service Triage stage
    And I select to send the case to drafting
    Then the case should be moved to the "Service Draft" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"

  Scenario Outline: User can add and complete or close contributions as part of Service Escalated stage
    And I add a "<contributionType>" contribution
    And I "action" the "contributionType" contribution
    Then the "contributionType" contribution should be marked as "action"
    Examples:
      | contributionType         | action   |
      | Complainant contribution | Complete |
      | Business contribution    | Close    |
      | Complainant contribution | Complete |
      | Business contribution    | Close    |