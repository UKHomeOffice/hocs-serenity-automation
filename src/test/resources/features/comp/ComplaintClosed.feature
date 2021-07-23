@ComplaintClosed @COMP
Feature: Complaint Closed

  Background:
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Complaint Closed" stage
    And I load and claim the current case

#    HOCS-2716
  @COMPWorkflow @COMPRegression
  Scenario: User can re-open a case that has been through CCT Stage 1
    When I select the "Re-open the case" action at Complaint Closed
    Then the case should be moved to the "Service Triage" stage
    And the summary should display the owning team as "CCT Stage 2 Triage Team"
    And the read-only Case Details accordion should contain all case information entered during the "Complaint Closed" stage

#    HOCS-3025
  @COMPWorkflow @COMPRegression
  Scenario: User can hard close a case at Complaint Closed stage
    When I select the "Complete the case" action at Complaint Closed
    And I enter a completion note at Complaint Closed
    And I confirm I want to close the case at Complaint Closed
    Then the case should be closed
    And a case closure note should be visible showing the reason for closure
    And the read-only Case Details accordion should contain all case information entered during the "Complaint Closed" stage

  @Validation
  Scenario Outline: User tests the validation at Complaint Closed
    When I trigger the "<errorType>" error message at "Complaint Closed"
    Then the "<errorType>" error message is displayed at "Complaint Closed"
    Examples:
      | errorType                                   |
      | PROGRESS CASE REQUIRED                      |
      | COMPLETE CASE NOTE REQUIRED                 |
      | COMPLETE CASE PERMANENTLY RESPONSE REQUIRED |