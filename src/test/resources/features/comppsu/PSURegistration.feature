Feature: PSU Registration

    #     IEDET COMPLAINTS and UKVI COMPLAINTS

  @ComplaintsWorkflow @Validation @IEDETComplaints
  Scenario: User attempts to progress a IEDET PSU case without entering a PSU reference
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Registration" stage
    Then When I attempt to continue without selecting a PSU Reference an error message is displayed

  @ComplaintsWorkflow @Validation @UKVIComplaints
  Scenario Outline: User attempts to progress a UKVI PSU case without entering a PSU reference
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "<caseStage>" stage
    Then When I attempt to continue without selecting a PSU Reference an error message is displayed

    Examples:
      | caseStage        | caseType    |
      | PSU_Registration | COMP        |
      | PSU_Registration | COMP2       |
    #  | PSU_Registration | COMP2DIRECT |


  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: User can transfer a UKVI complaints case to PSU at the Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "Transfer_PSU" stage
    And I click to view the case in the "PSU Complaints" workstack
    Then the created case should be visible in the workstack
    Then the case should be at the "PSU Registration" stage

    Examples:
      | caseType    |
      | COMP        |
      | COMP2       |


  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: User can escalate a UKVI complaint case to PSU at Triage stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "TRIAGE_PSU_ESCALATED" stage
    And I load and claim the current case
    And I click to view the case in the "PSU Complaints" workstack
    Then the created case should be visible in the workstack
    Then the case should be at the "PSU Registration" stage

    Examples:
      | caseType    |
      | COMP        |
      | COMP2       |


  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: User can escalate a UKVI complaint case to PSU at Escalated stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "ESCALATED_PSU" stage
    And I click to view the case in the "PSU Complaints" workstack
    Then the created case should be visible in the workstack
    Then the case should be at the "PSU Registration" stage

    Examples:
      | caseType    |
      | COMP        |
      | COMP2       |


  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: User can escalate a UKVI complaint case to PSU at QA stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "QA_ESCALATED_PSU" stage
    And I click to view the case in the "PSU Complaints" workstack
    Then the created case should be visible in the workstack
    Then the case should be at the "PSU Registration" stage

    Examples:
      | caseType    |
      | COMP        |
      | COMP2       |

  @ComplaintsWorkflow @BFPSURegression @BFComplaints
  Scenario Outline: User can transfer a BF complaints case to PSU at the Registration stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "<caseType>" case and move it to the "PSU_Registration" stage
    Then the case should be at the "PSU Registration" stage
    Examples:
      | caseType |
      | BF       |
#      | BF2      |

