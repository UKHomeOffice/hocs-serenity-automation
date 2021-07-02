@COMPWorkstacks @COMP
Feature: COMP Workstacks

  Background:
    Given I am logged into "DECS" as user "COMP_USER"

#     HOCS-2865, HOCS-3161
  @COMPRegression
  Scenario: COMP User sees the required information when viewing a workstack
    And I navigate to the "CCH CLOSED CASES" page
    Then the "CCH CLOSED CASES" workstack should contain only the expected columns

#     HOCS-3076 HOCS-3161
  @COMPRegression
  Scenario: User is able to see a yellow highlighted deadline on a COMP case that is close to its SLA
    When I create a single "COMP" case with the correspondence received date set 15 workdays ago
    And I view the case in the "Complaint Registration" workstack
    Then the case deadline should be highlighted yellow

#     HOCS-3076 HOCS-3161
  @COMPRegression
  Scenario: User is unable to see a red highlighted deadline on a COMP case that is past its deadline date
    When I create a single "COMP" case with the correspondence received date set 21 workdays ago
    And I view the case in the "Complaint Registration" workstack
    Then the case deadline should be highlighted red
    
  Scenario: Overdue Contribution Requests are highlighted in red in team workstacks
    And I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    When I accept the case at Service Triage stage
    And I click the "Continue" button
    And I enter details on the Triage Capture Reason page
    And I click the "Continue" button
    And I add a "complainant" contribution with a due date in the past
    And I navigate to the "Dashboard"
    And I view the case in the "CCT Stage 1 Triage Team" workstack
    Then the overdue contribution request should be highlighted in red