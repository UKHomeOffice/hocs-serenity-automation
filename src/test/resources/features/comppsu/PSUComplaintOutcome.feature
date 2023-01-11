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
        Then I select "Withdrawn" PSU Complaint Outcome
        Then the case should be closed
        And  the summary should contain details of the "Withdrawn" Complaint Outcome
        And  the read-only Case Details accordion should contain all case information entered during the "PSU Outcome" stage

@ComplaintsWorkflow @IEDETRegression @IEDETComplaint
Scenario: When a user selects Send back to IE Detention as Complaint Outcome the case should be moved to IEDET triage
        Given I am logged into "CS" as user "IEDET_USER"
        When I create a "IEDET" case and move it to the "PSU Complaint Outcome" stage
        Then I select "Not serious - send back to IE Detention" PSU Complaint Outcome
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
    Then I select "<complaintOutcome>" PSU Complaint Outcome
    Then I enter the Final response and Final date
    Then the case should be closed
    And  the summary should contain details of the "<complaintOutcome>" Complaint Outcome
    And  the read-only Case Details accordion should contain all case information entered during the "PSU Outcome" stage
    Examples:
        | complaintOutcome          |
        | Substantiated             |
        | Partially substantiated   |
        | Unsubstantiated           |

