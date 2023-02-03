Feature: PSU Deadline

    #     IEDET COMPLAINTS

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario Outline: When IEDET user escalate to the case to PSU the SLA is amended to 60 working days at the PSU Outcome stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "<caseStage>" stage
    And I click to view the case in the "<workstack>" workstack
    Then the case deadline date displayed in the "<workstack>" is "<amountOfDays>" workdays for a "<currentStage>" stage
    Then the case deadline date displayed in the summary is correct for a "<caseStage>" case

    Examples:
      | caseStage            | amountOfDays  | workstack                | currentStage        |
      | PSU Registration     |  20           | PSU Complaints           | PSU Registration    |
      | PSU Triage           |  20           | PSU Complaints           | PSU Triage          |
      | PSU Complaint Outcome|  60           | PSU Complaints           | PSU Outcome         |

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaint
  Scenario: When a PSU user Send back the case to IE Detention the SLA should be updated 20 working days
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Complaint Outcome" stage
    And I click to view the case in the "PSU Complaints" workstack
    Then the case deadline date displayed in the "PSU Complaints" is "60" workdays for a "PSU Outcome" stage
    Then the case deadline date displayed in the summary is correct for a "PSU Complaint Outcome" case
    Then I select "Not serious - send back to IE Detention" at "PSU Complaint Outcome" page
    And I click to view the case in the "IE Detention" workstack
    Then the case deadline date displayed in the "IE Detention" is "20" workdays for a "IE Detention Triage" stage
    Then the case deadline date displayed in the summary is correct for a "IEDET" case

