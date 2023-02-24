Feature: PSU Triage

  #     IEDET COMPLAINTS

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario: User chooses to send a IEDET PSU case to a team not on DECS
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Triage" stage
    And I choose to send the case to a team not on DECS
    Then the case should be closed

  @ComplaintsWorkflow @Validation @IEDETComplaints
  Scenario: User attempts to progress a IEDET PSU case without entering a an option in PSU Triage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Triage" stage
    Then When I attempt to continue without selecting a PSU Triage Option an error message is displayed

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario: User chooses to send a IEDET PSU case back to IEDET
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Triage" stage
    And I choose to send the case back to IEDET
    Then the case should be moved to the "IE Detention Triage" stage
    And the "Serious misconduct" radio button should be unselected
    And I click to view the case in the "My cases" workstack
    Then a "Rejected by PSU" tag is appended to the case reference
    And I click to view the case in the "IE Detention" workstack
    Then a "Rejected by PSU" tag is appended to the case reference


  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario: User confirms the complaint category options in PSU Triage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Triage" stage
    And I move to the review complaint categories screen and check the options there are working correctly
    Then the case should be moved to the "PSU Outcome" stage

  @ComplaintsWorkflow @Validation @UKVIComplaints
  Scenario Outline: User attempts to progress a UKVI PSU case without entering an option in PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "PSU_Triage" stage
    Then When I attempt to continue without selecting a PSU Triage Option an error message is displayed

    Examples:
      | caseType    |
      | COMP        |
      | COMP2       |
    #  | COMP2DIRECT |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: User confirms the UKVI complaint category options in PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "PSU_Triage" stage
    And I move to the review complaint categories screen and check the options there are working correctly
    Then the case should be moved to the "PSU Outcome" stage
    And the read-only Case Details accordion should contain all case information entered during the "PSU Triage" stage

    Examples:
      | caseType    |
      | COMP        |
      | COMP2       |
    #  | COMP2DIRECT |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: PSU User sends the case back to UKVI case from PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "PSU_Triage" stage
    Then I select "No - send back to UKVI" at "PSU Complaint Outcome" page
    And I click to view the case in the "<workstack>" workstack
    And the case stage should be "<newCaseStage>" and "Rejected by PSU" tag is appended to the case reference
    Then the case should be at the "<newCaseStage>" stage
    And the "Serious misconduct" radio button should be unselected

    Examples:
      | caseType    | workstack                | newCaseStage              |
      | COMP        | Minor Misconduct         | UKVI Recategorise         |
      | COMP2       | Stage 2 Minor Misconduct | UKVI Stage 2 Recategorise |
    #  | COMP2DIRECT | Stage 2 Minor Misconduct | UKVI Stage 2 Recategorise |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: User chooses to send a UKVI PSU case to a team not on DECS
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "PSU_Triage" stage
    And I choose to send the case to a team not on DECS
    Then the case should be closed

    Examples:
      | caseType    |
      | COMP        |
      | COMP2       |
    #  | COMP2DIRECT |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: When a user selects a complaint type for a Recategorise case, it should be transferred to the correct team
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "<caseStage>" stage
    And I load and claim the current case
    Then I enter the PSU registration details and move to PSU Triage
    Then I select "No - send back to UKVI" at "PSU Complaint Outcome" page
    Then the case should be at the "<newCaseStage>" stage
    And I select a Complaint Type
    And the case should be assigned to the corresponding team and move to the corresponding stage
    Examples:
      | caseType | caseStage            | newCaseStage              |
      | COMP     | TRANSFER_PSU         | UKVI Recategorise         |
      | COMP2    | TRIAGE_PSU_ESCALATED | UKVI Stage 2 Recategorise |

  @ComplaintsWorkflow @BFPSURegression @BFComplaints
  Scenario Outline: User confirms the BF complaint category options in PSU Triage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "<caseType>" case and move it to the "PSU_Triage" stage
    And I move to the review complaint categories screen and check the options there are working correctly
    Then the case should be moved to the "PSU Outcome" stage
    And the read-only Case Details accordion should contain all case information entered during the "PSU Triage" stage

    Examples:
      | caseType |
      | BF       |
#      | BF2      |

  @ComplaintsWorkflow @BFPSURegression @BFComplaints
  Scenario Outline: PSU User sends the case back to BF case from PSU Triage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "<caseType>" case and move it to the "PSU_Triage" stage
    Then I select "No - send back to Border Force" at "PSU Complaint Outcome" page
    And I click the "Submit" button
    And I click to view the case in the "<workstack>" workstack
    And the case stage should be "<newCaseStage>" and "Rejected by PSU" tag is appended to the case reference
    Then the case should be at the "<newCaseStage>" stage
    And the "Serious misconduct" radio button should be unselected

    Examples:
      | caseType | workstack                         | newCaseStage            |
      | BF       | Border Force Complaints           | BF Recategorise         |
#      | BF2      | Border Force Complaints (Stage 2) | BF Stage 2 Recategorise |

  @ComplaintsWorkflow @BFPSURegression @BFComplaints
  Scenario Outline: User chooses to send a BF PSU case to a team not on DECS
    Given I am logged into "CS" as user "BF_USER"
    When I create a "<caseType>" case and move it to the "PSU_Triage" stage
    And I choose to send the case to a team not on DECS
    Then the case should be closed

    Examples:
      | caseType |
      | BF       |
#      | BF2      |
