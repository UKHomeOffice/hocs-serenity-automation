@ServiceQA @COMP
Feature: Service QA

  Background:
    Given I am logged into "DECS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service QA" stage
    And I load and claim the current case

  @COMPWorkflow @COMPRegression
  Scenario: User can accept the response and send the case to Service Send stage
    When I accept the response
    Then the case should be moved to the "Service Send" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"

  @COMPWorkflow @COMPRegression
  Scenario: User can reject the response and send the case back to Service Draft stage
    When I reject the response
    Then the case should be moved to the "Service Draft" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"