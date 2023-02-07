Feature: PSU Complaints Outcome

@Validation
Scenario: When a user attempts to progress a IEDET PSU case without entering a PSU Complaint outcome
        Given I am logged into "CS" as user "IEDET_USER"
        When I create a "IEDET" case and move it to the "PSU Complaint Outcome" stage
        Then I attempt to continue without selecting a Complaint Outcome an error message is displayed

@ComplaintsWorkflow @IEDETRegression @IEDETComplaint
Scenario: When a user selects Withdraw as Complaint Outcome the case should be closed
        Given I am logged into "CS" as user "IEDET_USER"
        When I create a "IEDET" case and move it to the "PSU Complaint Outcome" stage
        Then I select "Withdrawn" at "PSU Complaint Outcome" page
        Then the case should be closed
        And  the summary should contain details of the "Withdrawn" Complaint Outcome
        And  the read-only Case Details accordion should contain all case information entered during the "PSU Outcome" stage

@ComplaintsWorkflow @IEDETRegression @IEDETComplaint
Scenario: When a user selects Send back to IE Detention as Complaint Outcome the case should be moved to IEDET triage
        Given I am logged into "CS" as user "IEDET_USER"
        When I create a "IEDET" case and move it to the "PSU Complaint Outcome" stage
        Then I select "Not serious - send back to IE Detention" at "PSU Complaint Outcome" page
        Then the case should be at the "IE Detention Triage" stage
        And the "Serious misconduct" radio button should be unselected
        And I click to view the case in the "My cases" workstack
        Then a "Rejected by PSU" tag is appended to the case reference
        And I click to view the case in the "IE Detention" workstack
        Then a "Rejected by PSU" tag is appended to the case reference

@ComplaintsWorkflow @IEDETRegression @IEDETComplaint
Scenario Outline: When a user selects one of the Complaint Outcome and enters the details in the final response page then the case should be closed
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Complaint Outcome" stage
    Then I select "<complaintOutcome>" at "PSU Complaint Outcome" page
    Then I enter the Final response and Final date
    Then the case should be closed
    And  the summary should contain details of the "<complaintOutcome>" Complaint Outcome
    And  the read-only Case Details accordion should contain all case information entered during the "PSU Outcome" stage
    Examples:
        | complaintOutcome          |
        | Substantiated             |
        | Partially substantiated   |
        | Unsubstantiated           |

  @Validation
  Scenario Outline: When a user attempts to progress a UKVI PSU case without entering a PSU Complaint outcome
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "PSU_COMPLAINT_OUTCOME" stage
    Then I attempt to continue without selecting a Complaint Outcome an error message is displayed

    Examples:
      | caseType    |
      | COMP        |
      | COMP2       |
      | COMP2DIRECT |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: When a PSU user selects Withdraw as UKVI PSU Complaint Outcome the case should be closed
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "PSU_COMPLAINT_OUTCOME" stage
    Then I select "Withdrawn" at "PSU Complaint Outcome" page
    Then the case should be closed
    And  the summary should contain details of the "Withdrawn" Complaint Outcome
    And  the read-only Case Details accordion should contain all case information entered during the "PSU Outcome" stage

    Examples:
      | caseType    |
      | COMP        |
      | COMP2       |
      | COMP2DIRECT |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: When a PSU user selects Send back to UKVI as a Complaint Outcome the case should be moved to Minor Misconduct
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "PSU_COMPLAINT_OUTCOME" stage
    Then I select "Not serious - send back to UKVI" at "PSU_COMPLAINT_OUTCOME" page
    Then the case should be at the "<newCaseStage>" stage
    And the "Serious misconduct" radio button should be unselected
    And I click to view the case in the "<workstack>" workstack
    Then a "Rejected by PSU" tag is appended to the case reference

    Examples:
      | caseType    | workstack                | newCaseStage              |
      | COMP        | Minor Misconduct         | UKVI Recategorise         |
      | COMP2       | Stage 2 Minor Misconduct | UKVI Stage 2 Recategorise |
      | COMP2DIRECT | Stage 2 Minor Misconduct | UKVI Stage 2 Recategorise |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: When a UKVI PSU user selects the Complaint Outcome and enters the final response then the case should be closed
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "PSU_COMPLAINT_OUTCOME" stage
    Then I select Complaint Type at PSU Complaint Outcome Page page
    Then I enter the Final response and Final date
    Then the case should be closed
    And  the summary should contain details of the Complaint Outcome selection
    And  the read-only Case Details accordion should contain all case information entered during the "PSU Outcome" stage

    Examples:
      | caseType    |
      | COMP        |
      | COMP2       |
      | COMP2DIRECT |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: When a user selects a complaint type for a Recategorise case, it should be transferred to the correct team
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "<caseStage>" stage
    And I load and claim the current case
    Then I enter the PSU registration details and move to PSU Triage
    And I move it to the PSU Outcome stage
    Then I select "Not serious - send back to UKVI" at "PSU_COMPLAINT_OUTCOME" page
    Then the case should be at the "<newCaseStage>" stage
    And I select a Complaint Type
    And the case should be assigned to the corresponding team and move to the corresponding stage

    Examples:
      | caseType | caseStage        | newCaseStage |
      | COMP     | ESCALATED_PSU    | UKVI Recategorise |
      | COMP2    | QA_ESCALATED_PSU | UKVI Stage 2 Recategorise |
