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

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: User attempts to progress a UKVI PSU case without entering a an option in PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "PSU_Triage" stage
    Then When I attempt to continue without selecting a PSU Triage Option an error message is displayed

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: User attempts to progress a UKVI COMP2 PSU case without entering a an option in PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "PSU_Triage" stage
    Then When I attempt to continue without selecting a PSU Triage Option an error message is displayed

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: User attempts to progress a Direct UKVI COMP2 PSU case without entering a an option in PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2DIRECT" case and move it to the "PSU_Triage" stage
    Then When I attempt to continue without selecting a PSU Triage Option an error message is displayed


  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: User confirms the UKVI complaint category options in PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "PSU_TRIAGE" stage
    And I move to the review complaint categories screen and check the options there are working correctly
    Then the case should be moved to the "PSU Outcome" stage
    And the read-only Case Details accordion should contain all case information entered during the "PSU Triage" stage

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: User confirms the UKVI COMP2 complaint category options in PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "PSU_TRIAGE" stage
    And I move to the review complaint categories screen and check the options there are working correctly
    Then the case should be moved to the "PSU Outcome" stage
    And the read-only Case Details accordion should contain all case information entered during the "PSU Triage" stage

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: User confirms the UKVI COMP2Direct complaint category options in PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2DIRECT" case and move it to the "PSU_TRIAGE" stage
    And I move to the review complaint categories screen and check the options there are working correctly
    Then the case should be moved to the "PSU Outcome" stage
    And the read-only Case Details accordion should contain all case information entered during the "PSU Triage" stage

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: PSU User sends the case back to UKVI case from PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "PSU_Triage" stage
    Then I select "No - send back to UKVI" PSU Complaint Outcome
    And I click to view the case in the "Minor Misconduct" workstack
    And the case stage should be "UKVI Recategorise" and "Rejected by PSU" tag is appended to the case reference
    Then the case should be at the "UKVI Recategorise" stage
    And the "Serious misconduct" radio button should be unselected

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: PSU User sends the case back to UKVI COMP2 case from PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "PSU_Triage" stage
    Then I select "No - send back to UKVI" PSU Complaint Outcome
    And I click to view the case in the "Stage 2 Minor Misconduct" workstack
    And the case stage should be "UKVI Stage 2 Recategorise" and "Rejected by PSU" tag is appended to the case reference
    Then the case should be at the "UKVI Stage 2 Recategorise" stage
    And the "Serious misconduct" radio button should be unselected

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: PSU User sends the case back to UKVI COMP2 Direct case from PSU Triage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2DIRECT" case and move it to the "PSU_Triage" stage
    Then I select "No - send back to UKVI" PSU Complaint Outcome
    And I click to view the case in the "Stage 2 Minor Misconduct" workstack
    And the case stage should be "UKVI Stage 2 Recategorise" and "Rejected by PSU" tag is appended to the case reference
    Then the case should be at the "UKVI Stage 2 Recategorise" stage
    And the "Serious misconduct" radio button should be unselected

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: User chooses to send a UKVI PSU case to a team not on DECS
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "PSU_Triage" stage
    And I choose to send the case to a team not on DECS
    Then the case should be closed

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: User chooses to send a UKVI COMP2 PSU case to a team not on DECS
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "PSU_Triage" stage
    And I choose to send the case to a team not on DECS
    Then the case should be closed

  @ComplaintsWorkflow @COMPPSURegression @UKVIComplaints
  Scenario: User chooses to send a UKVI COMP2 Direct PSU case to a team not on DECS
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2DIRECT" case and move it to the "PSU_Triage" stage
    And I choose to send the case to a team not on DECS
    Then the case should be closed