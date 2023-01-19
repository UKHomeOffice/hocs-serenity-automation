@Registration @Complaints
Feature: Registration

#     UKVI COMPLAINTS

  # HOCS-2999, HOCS-2858, HOCS-2859, HOCS-2860, HOCS-2862, HOCS-2881, HOCS-2899, HOCS-2648
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can complete the Registration stage for a UKVI Service complaint case
    Given I am logged into "CS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I enter the Complainant Details
    And I select "Service" as the Complaint Type
    And I enter the complaint details on the Complaint Input page
    And I click the "Continue" button
    And I select a "Service" Complaint Category
    And I select a Owning CSU
    When I click the "Finish" button
    Then the case should be moved to the "UKVI Service Triage" stage
    And the summary should display the owning team as "CCT Stage 1 Triage Team"
    And the read-only Case Details accordion should contain all case information entered during the "UKVI Registration" stage

  # HOCS-3441, HOCS-3442
  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can complete the Registration stage for a UKVI Ex-Gratia complaint case
    Given I am logged into "CS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter the Complainant Details
    And I select "Ex-Gratia" as the Complaint Type
    And I enter the complaint details on the Complaint Input page
    And I click the "Finish" button
    Then the case should be moved to the "UKVI Ex-Gratia Triage" stage
    And the summary should display the owning team as "Ex-Gratia"
    And the read-only Case Details accordion should contain all case information entered during the "UKVI Registration" stage

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can complete the Registration stage for a UKVI Minor Misconduct complaint case
    Given I am logged into "CS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter the Complainant Details
    And I select "Minor misconduct" as the Complaint Type
    And I enter the complaint details on the Complaint Input page
    And I click the "Finish" button
    Then the case should be moved to the "UKVI Minor Misconduct Triage" stage
    And the summary should display the owning team as "Minor Misconduct"
    And the read-only Case Details accordion should contain all case information entered during the "UKVI Registration" stage

  # HOCS-2709, HOCS-2858
  @COMPRegression @UKVIComplaints
  Scenario: User must add a Complainant type correspondent to a UKVI Complaint case
    Given I am logged into "CS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Third Party Representative" correspondent
    When I confirm the primary correspondent
    Then the "Complaint Correspondents Invalid" page should be displayed

  @COMPRegression @UKVIComplaints
  Scenario: User can navigate to the relevant stage 1 UKVI complaint case from the summary of the stage 2 UKVI complaint case
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP2" case and move it to the "Stage 2 Registration" stage
    And I load the current case
    And I select the previous COMP case reference from the COMP2 case summary tab
    Then the previous COMP case is displayed

  @Validation @UKVIComplaints
  Scenario Outline: User tests the validation for a UKVI complaint case at the Registration stage
    Given I am logged into "CS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen
    When I trigger the "<errorType>" error message at the "Registration" stage
    Then the "<errorType>" error message is displayed at the "Registration" stage
    Examples:
      | errorType                       |
      | PRIMARY CORRESPONDENT REQUIRED  |
      | COMPLAINT TYPE REQUIRED         |
      | CHANNEL REQUIRED                |
      | SEVERITY REQUIRED               |
      | OWNING CSU REQUIRED             |
      | COMPLAINT TYPE OPTION REQUIRED  |


#     UKVI COMPLAINTS STAGE 2

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can complete the Registration stage for a UKVI Service stage 2 complaint case
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2" case and move it to the "Stage 2 Registration" stage
    And I load and claim the current case
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I enter the Complainant Details
    And I select "Service" as the Complaint Type
    And I enter the complaint details on the Complaint Input page
    And I click the "Continue" button
    And I select a "Service" Complaint Category
    And I select a Owning CSU
    When I click the "Finish" button
    Then the case should be moved to the "UKVI Stage 2 Service Triage" stage
    And the summary should display the owning team as "Stage 2 CCT Triage Team"
    And the read-only Case Details accordion should contain all case information entered during the "UKVI Stage 2 Registration" stage

    #   UKVI COMPLAINTS STAGE 2 Direct

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can create UKVI stage 2 complaint case directly
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2DIRECT" case and move it to the "Stage 2 Registration" stage
    And I click to view the case in the "Stage 2 Complaint Registration" workstack
    Then I filter the workstack using the current cases reference
    Then I click the link for the current case in the workstack
    And I load and claim the current case
    And I select Sopra Steria case option "Yes - it's a Sopra Steria case"
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter the Complainant Details
    And I select "Service" as the Complaint Type
    And I enter the complaint details on the Complaint Input page
    And I click the "Continue" button
    And I select a "Service" Complaint Category
    And I select a Owning CSU
    When I click the "Finish" button
    Then the case should be moved to the "UKVI Stage 2 Service Triage" stage
    And the summary should display the owning team as "Stage 2 CCT Triage Team"
    And the summary should contain details of the Complaint Origin
    And the read-only Case Details accordion should contain all case information entered during the "UKVI Stage 2 Registration" stage

  @ComplaintsWorkflow @COMPRegression @UKVIComplaints
  Scenario: User can create UKVI stage 2 complaint case directly amd close the case if they don't want to continue
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP2DIRECT" case and move it to the "Stage 2 Registration" stage
    And I click to view the case in the "Stage 2 Complaint Registration" workstack
    Then I filter the workstack using the current cases reference
    Then I click the link for the current case in the workstack
    Then I load and claim the current case
    And I select Sopra Steria case option "No - close the case"
    Then the case should be closed
    And the summary should contain details of the Complaint Origin


#     IEDET COMPLAINTS

  @ComplaintsWorkflow @IEDETRegression @IEDETComplaints
  Scenario: User can complete the Registration stage for an IEDET complaint case
    Given I am logged into "CS" as user "IEDET_USER"
    And I create a single "IEDET" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter the Complainant Details
    Then the case should be moved to the "IE Detention Triage" stage
    And the summary should display the owning team as "IE Detention"
    And the read-only Case Details accordion should contain all case information entered during the "IE Detention Registration" stage


#     BF COMPLAINTS

  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User is able to complete the Registration stage for a BF complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I create a single "BF" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter the Complainant Details
    And I select "Service" as the Complaint Type
    And I enter the complaint details on the Complaint Input page
    And I click the "Finish" button
    Then the case should be moved to the "Case Triage" stage
    And the summary should display the owning team as "Border Force"
    And the read-only Case Details accordion should contain all case information entered during the "Case Registration" stage

  # HOCS-4404, HOCS-4423, HOCS-4707
  @BFRegression @BFComplaints
  Scenario: User must add a Complainant type correspondent to a BF complaint case
    Given I am logged into "CS" as user "BF_USER"
    And I create a single "BF" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Third Party Representative" correspondent
    When I confirm the primary correspondent
    Then the "Complaint Correspondents Invalid" page should be displayed

#     BF STAGE 2 COMPLAINTS

  @ComplaintsWorkflow @BFRegression @BFComplaints
  Scenario: User is able to complete the Registration stage for a BF stage 2 complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I create a "BF2" case and move it to the "Registration" stage
    And I navigate to the "Dashboard" page
    And I load and claim the current case
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter the Complainant Details
    And I enter the complaint details on the Complaint Input page
    And I click the "Finish" button
    Then the case should be moved to the "Case Triage (Stage 2)" stage
    And the summary should display the owning team as "Border Force Complaints (Stage 2)"
    And the read-only Case Details accordion should contain all case information entered during the "Case Registration (Stage 2)" stage
    And the summary should display "Service" for "Complaint Type"

#     POGR Complaints

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to complete the Data Input stage for a POGR complaint case with HMPO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    And I get a "POGR" case at the "Data Input" stage
    When I select "HMPO" as the business area for the POGR case
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I enter details on the Data Input screen
    And I add an "Interim Letter" type document to the case
    And I enter the date that the Interim letter was sent
    Then the case should be moved to the "Investigation" stage
    And the summary should display the owning team as "HMPO Complaints"
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage

  @ComplaintsWorkflow @POGRRegression @POGRComplaints
  Scenario: User is able to complete the Data Input stage for a POGR complaint case with GRO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    And I get a "POGR" case at the "Data Input" stage
    When I select "GRO" as the business area for the POGR case
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I enter details on the Data Input screen
    And I add an "Interim Letter" type document to the case
    And I enter the date that the Interim letter was sent
    And I select the investigating team for the case
    Then the case should be moved to the "Investigation" stage
    And the POGR case should be assigned to the correct investigating team
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage

  @POGRRegression @POGRComplaints
  Scenario: User is not required to add a interim letter to a POGR complaint case with HMPO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    And I get a "POGR" case at the "Data Input" stage
    When I select "HMPO" as the business area for the POGR case
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I enter details on the Data Input screen
    And I chose not to upload an interim letter
    Then I am returned to the dashboard

  @POGRRegression @POGRComplaints
  Scenario: User is not required to add a interim letter to a POGR complaint case with GRO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    And I get a "POGR" case at the "Data Input" stage
    When I select "GRO" as the business area for the POGR case
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I enter details on the Data Input screen
    And I chose not to upload an interim letter
    Then the "Investigation - Team Allocation" page should be displayed


#     POGR Stage 2 Complaints

  @ComplaintsWorkflow @ComplaintsRegression2 @POGRComplaints
  Scenario: User is able to complete the Data Input stage for a POGR stage 2 complaint case with HMPO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "HMPO" as the Business Area at the "Data Input" stage
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I enter details on the Data Input screen
    And I add an "Interim Letter" type document to the case
    And I enter the date that the Interim letter was sent
    Then the case should be moved to the "Investigation" stage
    And the summary should display the owning team as "HMPO Complaints"
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage

  @ComplaintsWorkflow @ComplaintsRegression2 @POGRComplaints
  Scenario: User is able to complete the Data Input stage for a POGR stage 2 complaint case with GRO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "GRO" as the Business Area at the "Data Input" stage
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I enter details on the Data Input screen
    And I add an "Interim Letter" type document to the case
    And I enter the date that the Interim letter was sent
    And I select the investigating team for the case
    Then the case should be moved to the "Investigation" stage
    And the POGR case should be assigned to the correct investigating team
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage

  @POGRRegression @POGRComplaints
  Scenario: User is not required to add a interim letter to a POGR stage 2 complaint case with HMPO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "HMPO" as the Business Area at the "Data Input" stage
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I enter details on the Data Input screen
    And I chose not to upload an interim letter
    Then I am returned to the dashboard

  @POGRRegression @POGRComplaints
  Scenario: User is not required to add a interim letter to a POGR stage 2 complaint case with GRO as the business area
    Given I am logged into "CS" as user "POGR_USER"
    When I get a "POGR2" case with "GRO" as the Business Area at the "Data Input" stage
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I enter details on the Data Input screen
    And I chose not to upload an interim letter
    Then the "Investigation - Team Allocation" page should be displayed
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage


#  SMC workflow cancelled. Steps and code might be useful for future work implementing PSU specific sub-workflow into other complaints workflows

#     SMC COMPLAINTS

  @ComplaintsWorkflow @SMCComplaints
  Scenario: User is able to complete the Registration stage for an SMC complaint case
    Given I am logged into "CS" as user "SMC_USER"
    And I create a single "SMC" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter the Complainant Details
    And I enter the complaint details on the Complaint Input page
    And I click the "Continue" button
    And I select a "Serious" Complaint Category
    And I select a Owning CSU
    And I click the "Finish" button
    Then the case should be moved to the "Triage" stage
    And the summary should display the owning team as "Serious Misconduct"
    And the read-only Case Details accordion should contain all case information entered during the "Registration" stage