@ServiceDraft @COMP
Feature: Service Draft

  Background:
    Given I am logged into "DECS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case

#   HOCS-3695
  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Service Dispatch stage
    And I upload a "Draft" document
    And I select the the case is ready to send
    Then the case should be moved to the "Service Send" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"

#    HOCS-3695
  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Service QA stage
    And I upload a "Draft" document
    And I send the case to QA
    Then the case should be moved to the "Service QA" stage
    And the summary should display the owning team as "CCT Stage 1 Response QA"

#    HOCS-3076
  Scenario: User must upload a document at Service Draft stage
    And I select that the case is ready to send
    Then an error message is displayed as I have not uploaded a document