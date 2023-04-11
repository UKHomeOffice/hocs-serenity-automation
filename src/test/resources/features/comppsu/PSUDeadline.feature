Feature: PSU Deadline

    #     IEDET COMPLAINTS

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario Outline: When IEDET user escalate to the case to PSU the SLA is amended to 60 working days at the PSU Outcome stage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "<caseStage>" stage
    And I click to view the case in the "<workstack>" workstack
    Then the case deadline date displayed in the "<workstack>" is "<amountOfDays>" workdays for a "<currentStage>" stage
    Then the case deadline date displayed in the summary is correct for a "<caseStage>" case
    When I logout of the application

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
    When I logout of the application
    When I enter the login credentials of another user "IEDET_USER" and click the login button
    And I click to view the case in the "IE Detention" workstack
    Then the case deadline date displayed in the "IE Detention" is "20" workdays for a "IE Detention Triage" stage
    Then the case deadline date displayed in the summary is correct for a "IEDET" case

    #     UKVI COMPLAINTS

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: When a UKVI user escalates the case to PSU the SLA is amended to 60 working days at the PSU Outcome stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "<caseStage>" stage
    And I click to view the case in the "<workstack>" workstack
    Then the case deadline date displayed in the "<workstack>" is "<amountOfDays>" workdays for a "<currentStage>" stage
    Then the case deadline date displayed in the summary is correct for a "<currentStage>" case
    When I logout of the application

    Examples:
      | caseStage            | amountOfDays  | workstack                | currentStage        |
      | PSU_Registration     |  20           | PSU Complaints           | PSU Registration    |
      | PSU_Triage           |  20           | PSU Complaints           | PSU Triage          |
      | PSU_Complaint_Outcome|  60           | PSU Complaints           | PSU Outcome         |

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: When a UKVI PSU user sends the case back to UKVI the SLA should be updated to 20 working days
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "PSU_Complaint_Outcome" stage
    And I click to view the case in the "PSU Complaints" workstack
    Then the case deadline date displayed in the "PSU Complaints" is "60" workdays for a "PSU Outcome" stage
    Then the case deadline date displayed in the summary is correct for a "PSU Complaint Outcome" case
    Then I select "Not serious - send back to UKVI" at "Complaint Outcome" page
    When I logout of the application
    When I enter the login credentials of another user "COMP_USER" and click the login button
    And I click to view the case in the "Minor Misconduct" workstack
    Then the case deadline date displayed in the "Minor Misconduct" is "20" workdays for a "UKVI Recategorise" stage

     #     BF COMPLAINTS

  @ComplaintsWorkflow @BFPSURegression @BFComplaints
  Scenario Outline: When a BF user escalates the case to PSU the SLA is amended to 60 working days at the PSU Outcome stage
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case and move it to the "<caseStage>" stage
    And I click to view the case in the "<workstack>" workstack
    Then the case deadline date displayed in the "<workstack>" is "<amountOfDays>" workdays for a "<currentStage>" stage
    Then the case deadline date displayed in the summary is correct for a "<currentStage>" case
    When I logout of the application

    Examples:
      | caseStage            | amountOfDays  | workstack                | currentStage        |
      | PSU_Registration     |  20           | PSU Complaints           | PSU Registration    |
      | PSU_Triage           |  20           | PSU Complaints           | PSU Triage          |
      | PSU_Complaint_Outcome|  60           | PSU Complaints           | PSU Outcome         |

  @ComplaintsWorkflow @BFPSURegression @BFComplaints
  Scenario: When a BF PSU user sends the case back to BF the SLA should be updated to 20 working days
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF" case and move it to the "PSU_Complaint_Outcome" stage
    And I click to view the case in the "PSU Complaints" workstack
    Then the case deadline date displayed in the "PSU Complaints" is "60" workdays for a "PSU Outcome" stage
    Then the case deadline date displayed in the summary is correct for a "PSU Complaint Outcome" case
    Then I select "Not serious - send back to Border Force" at "Complaint Outcome" page
    When I logout of the application
    When I enter the login credentials of another user "BF_USER" and click the login button
    And I click to view the case in the "Border Force Complaints" workstack
    Then the case deadline date displayed in the "Border Force Complaints" is "20" workdays for a "BF Recategorise" stage

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: Complaints user is able to see a yellow highlighted deadline on a complaint case that is close to its deadline date
    Given I am logged into "CS" as user "<user>"
    When I create a single "<caseType>" case with the correspondence received date set <amountOfDays> workdays ago
    And I move the case from "<currentStage>" stage to "<targetStage>" stage
    And I click to view the case in the "PSU Complaints" workstack
    Then the case deadline should be highlighted "yellow"
    When I logout of the application
    When I enter the login credentials of another user "PSU_USER" and click the login button
    And I load and claim the current case
    And I click to view the case in the "PSU Complaints" workstack
    Then the case deadline should be highlighted "yellow"
    And I logout of the application
    Examples:
      | caseType | user       | amountOfDays | currentStage              | targetStage      |
      | COMP     | COMP_USER  | 15           | Registration              | PSU_Registration |
      | COMP2    | COMP_USER  | 15           | STAGE_2_REGISTRATION      | PSU_Registration |
      | IEDET    | IEDET_USER | 15           | IE DETENTION REGISTRATION | PSU Registration |
      | BF       | BF_USER    | 15           | Registration              | PSU_Registration |

  @ComplaintsWorkflow @BFPSURegression @BFComplaints
  Scenario: BF2 user is able to see a yellow highlighted deadline on a complaint case that is close to its deadline date
    Given I am logged into "CS" as user "BF_USER"
    When I create a single "BF2" case with the correspondence received date set 15 workdays ago
    And I move the case from "Registration" stage to "TRANSFER_PSU" stage
    And I click to view the case in the "PSU Complaints" workstack
    Then the case deadline should be highlighted "yellow"
    When I logout of the application
    When I enter the login credentials of another user "PSU_USER" and click the login button
    And I load and claim the current case
    And I click to view the case in the "PSU Complaints" workstack
    Then the case deadline should be highlighted "yellow"
    And I logout of the application

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario Outline: Complaints user is able to see a red highlighted deadline on an complaint case that is past its deadline date
    Given I am logged into "CS" as user "<user>"
    When I create a single "<caseType>" case with the correspondence received date set <amountOfDays> workdays ago
    And I move the case from "<currentStage>" stage to "<targetStage>" stage
    And I click to view the case in the "PSU Complaints" workstack
    Then the case deadline should be highlighted "red"
    When I logout of the application
    When I enter the login credentials of another user "PSU_USER" and click the login button
    And I load and claim the current case
    And I click to view the case in the "PSU Complaints" workstack
    Then the case deadline should be highlighted "red"
    And I logout of the application
    Examples:
      | caseType | user       | amountOfDays | currentStage              | targetStage      |
      | COMP     | COMP_USER  | 21           | Registration              | PSU_Registration |
      | COMP2    | COMP_USER  | 21           | STAGE_2_REGISTRATION      | PSU_Registration |
      | IEDET    | IEDET_USER | 21           | IE DETENTION REGISTRATION | PSU Registration |
      | BF       | BF_USER    | 21           | Registration              | PSU_Registration |

  @ComplaintsWorkflow @BFPSURegression @BFComplaints
  Scenario: BF2 user is able to see a red highlighted deadline on an complaint case that is past its deadline date
    Given I am logged into "CS" as user "BF_USER"
    When I create a single "BF2" case with the correspondence received date set 21 workdays ago
    And I move the case from "Registration" stage to "TRANSFER_PSU" stage
    And I click to view the case in the "PSU Complaints" workstack
    Then the case deadline should be highlighted "red"
    When I logout of the application
    When I enter the login credentials of another user "PSU_USER" and click the login button
    And I load and claim the current case
    And I click to view the case in the "PSU Complaints" workstack
    Then the case deadline should be highlighted "red"
    And I logout of the application




