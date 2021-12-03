@ComplaintsWorkstacks @Complaints
Feature: Complaints Workstacks

#     HOCS-2865, HOCS-3161
  @ComplaintsRegression
  Scenario Outline: COMP User sees the required information when viewing a workstack
    Given I am logged into "CS" as user "COMP_USER"
    And I enter the "<workstack>" workstack
    Then the "<workstack>" workstack should contain only the expected columns
    Examples:
      | workstack              |
      | Complaint Registration |
      | Ex-Gratia              |
      | Minor Misconduct       |
      | CCT Triage             |

  @ComplaintsRegression
  Scenario: IEDET User sees the required information when viewing a workstack
    Given I am logged into "CS" as user "IEDET_USER"
    And I enter the "IE Detention" workstack
    Then the "IE Detention" workstack should contain only the expected columns

  @ComplaintsRegression
  Scenario: Serious Misconduct user sees the required information when viewing a workstack
    Given I am logged into "CS" as user "SMC_USER"
    And I enter the "Serious Misconduct" workstack
    Then the "Serious Misconduct" workstack should contain only the expected columns

  @ComplaintsRegression
  Scenario: User can see the required information when viewing the BF workstack
    Given I am logged into "CS" as user "BF_USER"
    And I enter a "Border Force" workstack
    Then the "Border Force" workstack should contain only the expected columns

#     HOCS-3076 HOCS-3161
  @ComplaintsRegression
  Scenario: User is able to see a yellow highlighted deadline on a COMP case that is close to its deadline date
    Given I am logged into "CS" as user "COMP_USER"
    When I create a single "COMP" case with the correspondence received date set 15 workdays ago
    And I click to view the case in the "Complaint Registration" workstack
    Then the case deadline should be highlighted yellow

#     HOCS-3076 HOCS-3161
  @ComplaintsRegression
  Scenario: User is able to see a red highlighted deadline on an COMP case that is past its deadline date
    Given I am logged into "CS" as user "COMP_USER"
    When I create a single "COMP" case with the correspondence received date set 21 workdays ago
    And I click to view the case in the "Complaint Registration" workstack
    Then the case deadline should be highlighted red

  @ComplaintsRegression
  Scenario: As a COMP user, when I view cases in a workstack, I want to be able to tell if a case has an overdue contribution request
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Severity selection
    And I enter details on the Triage Capture Reason page
    And I click the "Continue" button
    And I add a "complainant" contribution request with a due date in the past
    And I add a "complainant" contribution request with a due date in the future
    And I view the "CCT Stage 1 Triage Team" workstack
    Then the displayed contribution request status of the case should be correct
    And the overdue contribution request should be highlighted in red
    When I view the "My Cases" workstack
    Then I should be able to tell that the case has an overdue contribution

  @ComplaintsRegression
  Scenario: As a COMP user, when I view cases in a workstack, I want to be able to tell if a case has a due contribution request
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Severity selection
    And I enter details on the Triage Capture Reason page
    And I click the "Continue" button
    And I add a "complainant" contribution request with a due date in the future
    And I view the "CCT Stage 1 Triage Team" workstack
    Then the displayed contribution request status of the case should be correct
    And I view the "My Cases" workstack
    Then I should be able to tell when the contribution request is due
