@ServiceTriage @COMP
Feature: Service Triage

  Background:
    Given I am logged into "DECS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case

#   HOCS-2944, HOCS-2868
  @COMPWorkflow @COMPRegression
  Scenario: User can transfer the case to CCH
    When I select to Transfer the case to CCH
    And I enter a reason for transfer and continue
    Then the case should be moved to the "CCH" stage
    And the summary should display the owning team as "CCH Returned Cases"
    And a rejection note should be visible showing the reason for rejection

#    HOCS-2979, HOCS-3074, HOCS-2868, HOCS-2869, HOCS-3002, HOCS-2913
  @COMPWorkflow @COMPRegression
  Scenario: User completes the Service Triage stage
    And I accept the case at Service Triage stage
    And I click the "Continue" button
    And I enter details on the Triage Capture Reason page
    And I click the "Continue" button
    When I send the case to drafting
    Then the case should be moved to the "Service Draft" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"

#    HOCS-3028
  @COMPWorkflow @COMPRegression
  Scenario: User can escalate a case at Service Triage stage
      And I accept the case at Service Triage stage
      And I click the "Continue" button
      And I enter details on the Triage Capture Reason page
      And I click the "Continue" button
      When I escalate the case to WFM at Service Triage stage
      Then the case should be moved to the "Service Escalated" stage
      And the summary should display the owning team as "CCT Stage 1 Escalated"
      And a escalation note should be visible showing the reason for escalation

#    HCOS-3026
  @COMPWorkflow @COMPRegression
  Scenario: User can hard close a case at Service Triage stage
    And I accept the case at Service Triage stage
    And I click the "Continue" button
    And I enter details on the Triage Capture Reason page
    And I click the "Continue" button
    When I select to complete the case at Service Triage
    And I enter a completion note at Service Triage
    And I click the "Complete case" button
    And I confirm I want to close the case at Service Triage
    Then the case should be closed
    And a case closure note should be visible showing the reason for closure

#    HOCS-2870, HOCS-3096, HOCS-3022
  @COMPRegression
  Scenario Outline: User can add and complete or cancel contributions as part of Service Triage stage
    When I accept the case at Service Triage stage
    And I click the "Continue" button
    And I enter details on the Triage Capture Reason page
    And I click the "Continue" button
    And I add a "<contributionType>" contribution request
    And I "<action>" the contribution request
    Then the "<contributionType>" contribution request should be marked as "<action>"
    Examples:
      | contributionType | action   |
      | Complainant      | Complete |
      | Business         | Cancel   |
      | Complainant      | Complete |
      | Business         | Cancel   |

#    HOCS-3103
  @COMPRegression
  Scenario: User can tell if a contribution is overdue on the Triage Contributions page
    When I accept the case at Service Triage stage
    And I click the "Continue" button
    And I enter details on the Triage Capture Reason page
    And I click the "Continue" button
    And I add a "complainant" contribution with a due date in the past
    Then the "complainant" contribution request should be marked as "overdue"
    And the overdue contribution request should be highlighted

#    HOCS-2979
  @COMPRegression
  Scenario: User can select that a Letter of Authority is required for this complaint
    When I accept the case at Service Triage stage
    And I click the "Continue" button
    And I select that a Letter of Authority is required
    And I click the "Continue" button
    And I can mark that the LoA was received and enter the LoA date
    And I send the case to drafting
    And I load the current case
    Then the "Service Triage" COMP accordion in case details should display the correct information for "Has LoA Been Received"
    And the "Service Triage" COMP accordion in case details should display the correct information for "Date Of LoA"

  @Validation
  Scenario Outline: User tests the validation at the Service Triage stage
    When I trigger the "<errorType>" error message at the "Service Triage" stage
    Then the "<errorType>" error message is displayed at the "Service Triage" stage
    Examples:
      | errorType                                         |
      | CAN YOUR TEAM RESPOND TO COMPLAINT REQUIRED       |
      | REASON FOR TRANSFER REQUIRED                      |
      | TRANSFER TO REQUIRED                              |
      | BUSINESS AREA REQUIRED                            |
      | ENQUIRY REASON REQUIRED                           |
      | IS LETTER OF AUTHORITY REQUIRED RESPONSE REQUIRED |
      | ACTION REQUIRED                                   |
      | ESCALATION REASON REQUIRED                        |
      | COMPLETE CASE NOTE REQUIRED                       |
      | COMPLETE CASE PERMANENTLY RESPONSE REQUIRED       |