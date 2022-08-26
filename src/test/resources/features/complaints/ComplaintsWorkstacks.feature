@ComplaintsWorkstacks @Complaints
Feature: Complaints Workstacks

#     UKVI COMPLAINTS

  # HOCS-2865, HOCS-3161
  @COMPRegression @UKVIComplaints
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

  @COMPRegression @UKVIComplaints
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

  @COMPRegression @UKVIComplaints
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

  @IEDETAndSMCRegression @IEDETComplaints
  Scenario: IEDET complaints user sees the required information when viewing a workstack
    Given I am logged into "CS" as user "IEDET_USER"
    And I enter the "IE Detention" workstack
    Then the "IE Detention" workstack should contain only the expected columns


#     SMC COMPLAINTS

  @IEDETAndSMCRegression @IEDETComplaints
  Scenario Outline: Serious Misconduct complaints user sees the required information when viewing a workstack
    Given I am logged into "CS" as user "SMC_USER"
    And I enter the "<workstack>" workstack
    Then the "<workstack>" workstack should contain only the expected columns
    Examples:
      | workstack                   |
      | Serious Misconduct          |
      | Serious Misconduct My Cases |

  @IEDETAndSMCRegression
  Scenario Outline: SMC User is able to see the deadline of a case close to its deadline highlighted in yellow
    Given I am logged into "CS" as user "SMC_USER"
    When I create an SMC case received <amountOfDays> workdays in the past and move it to the "<stage>" stage
    And I click to view the case in the "<workstack>" workstack
    Then the case deadline should be highlighted "yellow"
    Examples:
      | amountOfDays | stage        | workstack          |
      | 55           | Registration | SMC Registration   |
      | 55           | Triage       | Serious Misconduct |

  @IEDETAndSMCRegression
  Scenario Outline: SMC User is able to see an overdue case deadline highlighted in red
    Given I am logged into "CS" as user "SMC_USER"
    When I create an SMC case received <amountOfDays> workdays in the past and move it to the "<stage>" stage
    And I click to view the case in the "<workstack>" workstack
    Then the case deadline should be highlighted "red"
    Examples:
      | amountOfDays | stage        | workstack          |
      | 61           | Registration | SMC Registration   |
      | 61           | Triage       | Serious Misconduct |


#     BF COMPLAINTS

  @BFRegression @BFComplaints
  Scenario: Border Force complaints user can see the required information when viewing the Border Force workstack
    Given I am logged into "CS" as user "BF_USER"
    And I enter a "Border Force" workstack
    Then the "Border Force" workstack should contain only the expected columns


#     BF STAGE 2 COMPLAINTS

  @BFRegression @BFComplaints
  Scenario: Border Force complaints user can see the required information when viewing the Border Force (Stage 2) workstack
    Given I am logged into "CS" as user "BF_USER"
    And I enter a "Border Force (Stage 2)" workstack
    Then the "Border Force (Stage 2)" workstack should contain only the expected columns


#     POGR COMPLAINTS

  @POGRRegression @POGRComplaints
  Scenario Outline: HMPO/GRO complaints user sees the required information when viewing a workstack
    Given I am logged into "CS" as user "POGR_USER"
    And I enter the "<workstack>" workstack
    Then the "<workstack>" workstack should contain only the expected columns
    Examples:
      | workstack         |
      | POGR Registration |
      | POGR My Cases     |


#     POGR STAGE 2 COMPLAINTS

  @POGRRegression @POGRComplaints
  Scenario: HMPO/GRO complaints user sees the required information when viewing a Stage 2 workstack
    Given I am logged into "CS" as user "POGR_USER"
    And I enter the "POGR Registration (Stage 2)" workstack
    Then the "POGR Registration (Stage 2)" workstack should contain only the expected columns


#     ALL COMPLAINTS

  # HOCS-3076 HOCS-3161 HOCS-4006
  @CSRegression
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
      | BF       | BF_USER    | 15           | Border Force                   |
      | BF2      | BF_USER    | 15           | Border Force (Stage 2)         |

  # HOCS-3076 HOCS-3161 HOCS-4006
  @CSRegression
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
      | BF       | BF_USER    | 21           | Border Force                   |
      | BF2      | BF_USER    | 21           | Border Force (Stage 2)         |
      | POGR     | POGR_USER  | 11           | HMPO/GRO Registration          |
      | POGR2    | POGR_USER  | 11           | HMPO/GRO Registration (Stage 2)|