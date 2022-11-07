Feature: PSU Triage

  #     IEDET COMPLAINTS

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario: User chooses to send a IEDET PSU case to a team not on DECS
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Triage" stage
    And I choose to send the case to a team not on DECS
    Then the case should be closed

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario: User attempts to progress a IEDET PSU case without entering a an option in PSU Triage
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Triage" stage
    Then When I attempt to continue without selecting a PSU Triage Option an error message is displayed

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario: User chooses to send a IEDET PSU case back to IEDET
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Triage" stage
    And I choose to send the case back to IEDET
    Then the case should be moved to the "Triage" stage