@COMPDraft @COMP
Feature: COMP Draft

  Background:
    Given I am logged into "CS" as user "COMP_USER"

#   HOCS-3695
  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Service Send stage
    When I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case
    And I upload a "Draft" document
    And I select the "Response is ready to send" action at the Service Draft stage
    Then the case should be moved to the "Service Send" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Service Draft" stage

  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Ex-Gratia Send stage
    When I create a "COMP" case and move it to the "Ex-Gratia Response Draft" stage
    And I load and claim the current case
    And I upload a "Draft" document
    And I select the "Response is ready to send" action at the Service Draft stage
    Then the case should be moved to the "Ex-Gratia Send" stage
    And the summary should display the owning team as "Ex-Gratia"

  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Minor Misconduct Send stage
    When I create a "COMP" case and move it to the "Minor Misconduct Response Draft" stage
    And I load and claim the current case
    And I upload a "Draft" document
    And I select the "Response is ready to send" action at the Service Draft stage
    Then the case should be moved to the "Minor Misconduct Send" stage
    And the summary should display the owning team as "Minor Misconduct"

#    HOCS-3695
  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Service QA stage
    When I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case
    And I upload a "Draft" document
    And I select the "Send case to QA" action at the Service Draft stage
    Then the case should be moved to the "Service QA" stage
    And the summary should display the owning team as "CCT Stage 1 Response QA"
    And the read-only Case Details accordion should contain all case information entered during the "Service Draft" stage

  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Ex-Gratia QA stage
    When I create a "COMP" case and move it to the "Ex-Gratia Response Draft" stage
    And I load and claim the current case
    And I upload a "Draft" document
    And I select the "Send case to QA" action at the Service Draft stage
    Then the case should be moved to the "Ex-Gratia QA" stage
    And the summary should display the owning team as "Ex-Gratia"

  @COMPWorkflow @COMPRegression
  Scenario: User sends the case to Minor Misconduct QA stage
    When I create a "COMP" case and move it to the "Minor Misconduct Response Draft" stage
    And I load and claim the current case
    And I upload a "Draft" document
    And I select the "Send case to QA" action at the Service Draft stage
    Then the case should be moved to the "Minor Misconduct QA" stage
    And the summary should display the owning team as "Minor Misconduct"

  @COMPWorkflow @COMPRegression
  Scenario: User is able to escalate a case to WFM at Service Draft stage
    When I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case
    And I escalate the case to WFM at Service Draft stage
    Then the case should be moved to the "Service Escalated" stage
    And the summary should display the owning team as "CCT Stage 1 Escalated"
    And a escalation note should be visible showing the reason for escalation
    And the read-only Case Details accordion should contain all case information entered during the "Service Draft" stage

  @COMPWorkflow @COMPRegression
  Scenario: User is able to escalate a case to WFM at Ex-Gratia Response Draft stage
    When I create a "COMP" case and move it to the "Ex-Gratia Response Draft" stage
    And I load and claim the current case
    And I escalate the case to WFM at Service Draft stage
    Then the case should be moved to the "Ex-Gratia Escalate" stage
    And the summary should display the owning team as "Ex-Gratia"
    And a escalation note should be visible showing the reason for escalation

  @COMPWorkflow @COMPRegression
  Scenario: User is able to escalate a case to WFM at Minor Misconduct Response Draft stage
    When I create a "COMP" case and move it to the "Minor Misconduct Response Draft" stage
    And I load and claim the current case
    And I escalate the case to WFM at Service Draft stage
    Then the case should be moved to the "Minor Misconduct Escalate" stage
    And the summary should display the owning team as "Minor Misconduct"
    And a escalation note should be visible showing the reason for escalation

#    HOCS-3076
  @Validation
  Scenario: User must upload a document at Service Draft stage
    When I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case
    And I select the "Response is ready to send" action at the Service Draft stage
    Then an error message is displayed as I have not uploaded a document

  @Validation
  Scenario Outline: User tests the validation at the Service Draft stage
    When I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case
    When I trigger the "<errorType>" error message at the "Service Draft" stage
    Then the "<errorType>" error message is displayed at the "Service Draft" stage
    Examples:
      | errorType                        |
      | PRIMARY DRAFT DOCUMENT REQUIRED  |
      | ACTION REQUIRED                  |
      | ESCALATION REASON                |