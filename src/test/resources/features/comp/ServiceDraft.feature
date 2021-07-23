@ServiceDraft @COMP
Feature: Service D raft

  Background:
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case

#   HOCS-3695
  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Service Send stage
    And I upload a "Draft" document
    And I select the "Response is ready to send" action at the Service Draft stage
    Then the case should be moved to the "Service Send" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Service Draft" stage

#    HOCS-3695
  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Service QA stage
    And I upload a "Draft" document
    And I select the "Send case to QA" action at the Service Draft stage
    Then the case should be moved to the "Service QA" stage
    And the summary should display the owning team as "CCT Stage 1 Response QA"
    And the read-only Case Details accordion should contain all case information entered during the "Service Draft" stage

  @COMPWorkflow @COMPRegression
  Scenario: User is able to escalate a case to WFM at Service Draft stage
    And I escalate the case to WFM at Service Draft stage
    Then the case should be moved to the "Service Escalated" stage
    And the summary should display the owning team as "CCT Stage 1 Escalated"
    And a escalation note should be visible showing the reason for escalation
    And the read-only Case Details accordion should contain all case information entered during the "Service Draft" stage


#    HOCS-3076
  @Validation
  Scenario: User must upload a document at Service Draft stage
    And I select the "Response is ready to send" action at the Service Draft stage
    Then an error message is displayed as I have not uploaded a document

  @Validation
  Scenario Outline: User tests the validation at the Service Draft stage
    When I trigger the "<errorType>" error message at the "Service Draft" stage
    Then the "<errorType>" error message is displayed at the "Service Draft" stage
    Examples:
      | errorType                        |
      | PRIMARY DRAFT DOCUMENT REQUIRED  |
      | ACTION REQUIRED                  |
      | ESCALATION REASON                |