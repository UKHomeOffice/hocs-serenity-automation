@COMPWorkstacks @COMP
Feature: COMP Workstacks

#     HOCS-2865, HOCS-3161
  @COMPRegression
  Scenario: COMP User sees the required information when viewing a workstack
    Given I am logged into "CS" as user "COMP_USER"
    And I click to view the "Complaint Registration" workstack
    Then the "Complaint Registration" workstack should contain only the expected columns

#     HOCS-3076 HOCS-3161
  @COMPRegression
  Scenario: User is able to see a yellow highlighted deadline on a COMP case that is close to its deadline date
    Given I am logged into "CS" as user "COMP_USER"
    When I create a single "COMP" case with the correspondence received date set 15 workdays ago
    And I click to view the case in the "Complaint Registration" workstack
    Then the case deadline should be highlighted yellow

#     HOCS-3076 HOCS-3161
  @COMPRegression
  Scenario: User is able to see a red highlighted deadline on an COMP case that is past its deadline date
    Given I am logged into "CS" as user "COMP_USER"
    When I create a single "COMP" case with the correspondence received date set 21 workdays ago
    And I click to view the case in the "Complaint Registration" workstack
    Then the case deadline should be highlighted red

  Scenario: Overdue Contribution Requests are highlighted in red in team workstacks
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Severity selection
    And I enter details on the Triage Capture Reason page
    And I click the "Continue" button
    And I add a "complainant" contribution with a due date in the past
    And I navigate to the "Dashboard"
    And I click to view the "CCT Stage 1 Triage Team" workstack
    Then the overdue contribution request should be highlighted in red

  Scenario: Contribution Request statuses are correctly displayed in team workstacks
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Severity selection
    And I enter details on the Triage Capture Reason page
    And I click the "Continue" button
    And I add a "complainant" contribution with a due date in the past
    And I add a "complainant" contribution request
    And I navigate to the "Dashboard"
    And I click to view the "CCT Stage 1 Triage Team" workstack
    Then the displayed contribution request status of the case should be correct

  @COMPRegression
  Scenario: IEDET User sees the required information when viewing a workstack
    Given I am logged into "CS" as user "IEDET_USER"
    And I create a "IEDET" case and move it to the "Triage" stage
    And I click to view the "IE Detention" workstack
    Then the "IE Detention" workstack should contain only the expected columns