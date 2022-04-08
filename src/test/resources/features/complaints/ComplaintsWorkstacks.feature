@ComplaintsWorkstacks @Complaints
Feature: Complaints Workstacks

#     UKVI COMPLAINTS

  # HOCS-2865, HOCS-3161
  @ComplaintsRegression1 @UKVIComplaints
  Scenario Outline: UKVI complaints user sees the required information when viewing a workstack
    Given I am logged into "CS" as user "COMP_USER"
    And I enter the "<workstack>" workstack
    Then the "<workstack>" workstack should contain only the expected columns
    Examples:
      | workstack              |
      | Complaint Registration |
      | Ex-Gratia              |
      | Minor Misconduct       |
      | CCT Triage             |

  @ComplaintsRegression1 @UKVIComplaints
  Scenario: As a UKVI complaints user, when I view cases in a workstack, I want to be able to tell if a case has an overdue contribution request
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I add a "complainant" contribution request with a due date in the past
    And I add a "complainant" contribution request with a due date in the future
    And I view the "CCT Stage 1 Triage Team" workstack
    Then the displayed contribution request status of the case should be correct
    And the overdue contribution request should be highlighted in red
    When I view the "My Cases" workstack
    Then I should be able to tell that the case has an overdue contribution

  @ComplaintsRegression1 @UKVIComplaints
  Scenario: As a UKVI complaints user, when I view cases in a workstack, I want to be able to tell if a case has a due contribution request
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Triage" stage
    And I load and claim the current case
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    And I submit details on the Triage Capture Reason page
    And I add a "complainant" contribution request with a due date in the future
    And I view the "CCT Stage 1 Triage Team" workstack
    Then the displayed contribution request status of the case should be correct
    And I view the "My Cases" workstack
    Then I should be able to tell when the contribution request is due


#     IEDET COMPLAINTS

  @ComplaintsRegression2 @IEDETComplaints
  Scenario: IEDET complaints user sees the required information when viewing a workstack
    Given I am logged into "CS" as user "IEDET_USER"
    And I enter the "IE Detention" workstack
    Then the "IE Detention" workstack should contain only the expected columns


#     SMC COMPLAINTS

  @ComplaintsRegression2 @IEDETComplaints
  Scenario Outline: Serious Misconduct complaints user sees the required information when viewing a workstack
    Given I am logged into "CS" as user "SMC_USER"
    And I enter the "<workstack>" workstack
    Then the "<workstack>" workstack should contain only the expected columns
    Examples:
      | workstack                   |
      | Serious Misconduct          |
      | Serious Misconduct My Cases |


#     BF COMPLAINTS

  @ComplaintsRegression2 @BFComplaints
  Scenario: Border Force complaints user can see the required information when viewing the Border Force workstack
    Given I am logged into "CS" as user "BF_USER"
    And I enter a "Border Force" workstack
    Then the "Border Force" workstack should contain only the expected columns


#     BF STAGE 2 COMPLAINTS

  @ComplaintsRegression2 @BFComplaints
  Scenario: Border Force complaints user can see the required information when viewing the Border Force (Stage 2) workstack
    Given I am logged into "CS" as user "BF_USER"
    And I enter a "Border Force (Stage 2)" workstack
    Then the "Border Force (Stage 2)" workstack should contain only the expected columns


#     ALL COMPLAINTS

  # HOCS-3076 HOCS-3161 HOCS-4006
  @ComplaintsRegression2
  Scenario Outline: Complaints user is able to see a yellow highlighted deadline on a complaint case that is close to its deadline date
    Given I am logged into "CS" as user "<user>"
    When I create a single "<caseType>" case with the correspondence received date set <amountOfDays> workdays ago
    And I click to view the case in the "<workstack>" workstack
    Then the case deadline should be highlighted "yellow"
    Examples:
      | caseType | user       | amountOfDays | workstack                      |
      | COMP     | COMP_USER  | 15           | Complaint Registration         |
      | COMP2    | COMP_USER  | 15           | Stage 2 Complaint Registration |
      | IEDET    | IEDET_USER | 15           | IE Detention                   |
      | SMC      | SMC_USER   | 55           | Serious Misconduct             |
      | BF       | BF_USER    | 15           | Border Force                   |
      | BF2      | BF_USER    | 15           | Border Force (Stage 2)         |

  # HOCS-3076 HOCS-3161 HOCS-4006
  @ComplaintsRegression2
  Scenario Outline: Complaints user is able to see a red highlighted deadline on an complaint case that is past its deadline date
    Given I am logged into "CS" as user "<user>"
    When I create a single "<caseType>" case with the correspondence received date set <amountOfDays> workdays ago
    And I click to view the case in the "<workstack>" workstack
    Then the case deadline should be highlighted "red"
    Examples:
      | caseType | user       | amountOfDays | workstack                      |
      | COMP     | COMP_USER  | 21           | Complaint Registration         |
      | COMP2    | COMP_USER  | 21           | Stage 2 Complaint Registration |
      | IEDET    | IEDET_USER | 21           | IE Detention                   |
      | SMC      | SMC_USER   | 61           | Serious Misconduct             |
      | BF       | BF_USER    | 21           | Border Force                   |
      | BF2      | BF_USER    | 21           | Border Force (Stage 2)         |

