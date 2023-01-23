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
    When I create a "<caseType>" case and move it to the "<caseStage>" stage
    Then I attempt to continue without selecting a Complaint Outcome an error message is displayed

    Examples:
      | caseStage             | caseType    |
      | PSU_COMPLAINT_OUTCOME | COMP        |
      | PSU_COMPLAINT_OUTCOME | COMP2       |
      | PSU_COMPLAINT_OUTCOME | COMP2DIRECT |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: When a PSU user selects Withdraw as UKVI PSU Complaint Outcome the case should be closed
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "<caseStage>" stage
    Then I select "Withdrawn" at "PSU Complaint Outcome" page
    Then the case should be closed
    And  the read-only Case Details accordion should contain all case information entered during the "PSU Outcome" stage

    Examples:
      | caseStage             | caseType    |
      | PSU_COMPLAINT_OUTCOME | COMP        |
      | PSU_COMPLAINT_OUTCOME | COMP2       |
      | PSU_COMPLAINT_OUTCOME | COMP2DIRECT |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: When a PSU user selects Send back to UKVI as a Complaint Outcome the case should be moved to Minor Misconduct
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "<caseStage>" stage
    Then I select "Not serious - send back to UKVI" at "PSU Complaint Outcome" page
    Then the case should be at the "<newCaseStage>" stage
    And the "Serious misconduct" radio button should be unselected
    And I click to view the case in the "<workstack>" workstack
    Then a "Rejected by PSU" tag is appended to the case reference

    Examples:
      | caseStage             | caseType    | workstack                | newCaseStage              |
      | PSU_COMPLAINT_OUTCOME | COMP        | Minor Misconduct         | UKVI Recategorise         |
      | PSU_COMPLAINT_OUTCOME | COMP2       | Stage 2 Minor Misconduct | UKVI Stage 2 Recategorise |
      | PSU_COMPLAINT_OUTCOME | COMP2DIRECT | Stage 2 Minor Misconduct | UKVI Stage 2 Recategorise |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: When a UKVI PSU user selects the Complaint Outcome and enters the final response then the case should be closed
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "<caseType>" case and move it to the "<caseStage>" stage
    Then I select "<complaintOutcome>" at "PSU Complaint Outcome" page
    Then I enter the Final response and Final date
    Then the case should be closed
    And  the read-only Case Details accordion should contain all case information entered during the "PSU Outcome" stage

    Examples:
      | complaintOutcome        | caseStage             | caseType    |
      | Substantiated           | PSU_COMPLAINT_OUTCOME | COMP        |
      | Substantiated           | PSU_COMPLAINT_OUTCOME | COMP2       |
      | Substantiated           | PSU_COMPLAINT_OUTCOME | COMP2DIRECT |
      | Partially substantiated | PSU_COMPLAINT_OUTCOME | COMP        |
      | Partially substantiated | PSU_COMPLAINT_OUTCOME | COMP2       |
      | Partially substantiated | PSU_COMPLAINT_OUTCOME | COMP2DIRECT |
      | Unsubstantiated         | PSU_COMPLAINT_OUTCOME | COMP        |
      | Unsubstantiated         | PSU_COMPLAINT_OUTCOME | COMP2       |
      | Unsubstantiated         | PSU_COMPLAINT_OUTCOME | COMP2DIRECT |
