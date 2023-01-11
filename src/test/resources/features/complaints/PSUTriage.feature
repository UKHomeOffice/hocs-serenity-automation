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

