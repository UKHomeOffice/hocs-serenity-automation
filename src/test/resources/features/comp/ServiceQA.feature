@ServiceQA @COMP
Feature: Service QA

  Background:
    Given I am logged into "DECS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service QA" stage
    And I load and claim the current case

#    HOCS-3695
  @COMPWorkflow @COMPRegression
  Scenario: User can accept the response and send the case to Service Send stage
    When I "accept" the response at the Service QA stage
    Then the case should be moved to the "Service Send" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Service QA" stage

#    HOCS-3039
  @COMPWorkflow @COMPRegression
  Scenario: User can reject the response and send the case back to Service Draft stage
    When I "reject" the response at the Service QA stage
    Then the case should be moved to the "Service Draft" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And a rejection note should be visible showing the reason for rejection
    And the read-only Case Details accordion should contain all case information entered during the "Service QA" stage

  @Validation
  Scenario Outline: User tests the validation at the Service QA stage
    When I trigger the "<errorType>" error message at the "Service QA" stage
    Then the "<errorType>" error message is displayed at the "Service QA" stage
    Examples:
      | errorType                 |
      | QA RESULT REQUIRED        |
      | REJECTION REASON REQUIRED |