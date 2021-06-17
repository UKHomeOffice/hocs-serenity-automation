@ServiceDraft @COMP
Feature: Service Draft

  Background:
    Given I am logged into "DECS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case

#   HOCS-3695
  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Service Send stage
    And I upload a "Draft" document
    And I select the "Response is ready to send" action at the Service Draft stage
    Then the case should be moved to the "Service Send" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"

#    HOCS-3695
  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Service QA stage
    And I upload a "Draft" document
    And I select the "Send case to QA" action at the Service Draft stage
    Then the case should be moved to the "Service QA" stage
    And the summary should display the owning team as "CCT Stage 1 Response QA"

  @COMPWorkflow @COMPRegression
  Scenario: User is able to escalate a case to WFM at Service Draft stage
    And I escalate the case to WFM at Service Draft stage
    Then the case should be moved to the "Service Escalated" stage
    And the summary should display the owning team as "CCT Stage 1 Escalated"
    And a escalation note should be visible showing the reason for escalation

#    HOCS-3076
  @Validation
  Scenario: User must upload a document at Service Draft stage
    And I select the "Response is ready to send" action at the Service Draft stage
    Then an error message is displayed as I have not uploaded a document