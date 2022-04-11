@Registration @Complaints
Feature: Registration

#     UKVI COMPLAINTS

  # HOCS-2999, HOCS-2858, HOCS-2859, HOCS-2860, HOCS-2862, HOCS-2881, HOCS-2899, HOCS-2648
  @ComplaintsWorkflow @ComplaintsRegression1 @UKVIComplaints
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
    Then the case should be moved to the "Service Triage" stage
    And the summary should display the owning team as "CCT Stage 1 Triage Team"
    And the read-only Case Details accordion should contain all case information entered during the "Registration" stage

  # HOCS-3441, HOCS-3442
  @ComplaintsWorkflow @ComplaintsRegression1 @UKVIComplaints
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
    Then the case should be moved to the "Ex-Gratia Triage" stage
    And the summary should display the owning team as "Ex-Gratia"
    And the read-only Case Details accordion should contain all case information entered during the "Registration" stage

  @ComplaintsWorkflow @ComplaintsRegression1 @UKVIComplaints
  Scenario: User can complete the Registration stage for a UKVI Minor Misconduct complaint case
    Given I am logged into "CS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter the Complainant Details
    And I select "Minor Misconduct" as the Complaint Type
    And I enter the complaint details on the Complaint Input page
    And I click the "Finish" button
    Then the case should be moved to the "Minor Misconduct Triage" stage
    And the summary should display the owning team as "Minor Misconduct"
    And the read-only Case Details accordion should contain all case information entered during the "Registration" stage

  # HOCS-2709, HOCS-2858
  @ComplaintsRegression1 @UKVIComplaints
  Scenario: User must add a Complainant type correspondent to a UKVI Complaint case
    Given I am logged into "CS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Third Party Representative" correspondent
    When I confirm the primary correspondent
    Then the "Complaint Correspondents Invalid" page should be displayed

  @ComplaintsRegression1 @UKVIComplaints
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

  @ComplaintsWorkflow @ComplaintsRegression1 @UKVIComplaints
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
    Then the case should be moved to the "Stage 2 Service Triage" stage
    And the summary should display the owning team as "Stage 2 CCT Triage Team"
    And the read-only Case Details accordion should contain all case information entered during the "Stage 2 Registration" stage


#     IEDET COMPLAINTS

  @ComplaintsWorkflow @ComplaintsRegression2 @IEDETComplaints
  Scenario: User can complete the Registration stage for an IEDET complaint case
    Given I am logged into "CS" as user "IEDET_USER"
    And I create a single "IEDET" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter the Complainant Details
    And I select "Ex-Gratia" as the Complaint Type
    And I enter the complaint details on the Complaint Input page
    And I click the "Continue" button
    And I select a "Service" Complaint Category
    And I select a Owning CSU
    And I click the "Finish" button
    Then the case should be moved to the "Triage" stage
    And the summary should display the owning team as "IE Detention"
    And the read-only Case Details accordion should contain all case information entered during the "Registration" stage


#     SMC COMPLAINTS

  @ComplaintsWorkflow @ComplaintsRegression2 @SMCComplaints
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


#     BF COMPLAINTS

  @ComplaintsWorkflow @ComplaintsRegression2 @BFComplaints
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
  @ComplaintsRegression2 @BFComplaints
  Scenario: User must add a Complainant type correspondent to a BF complaint case
    Given I am logged into "CS" as user "BF_USER"
    And I create a single "BF" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Third Party Representative" correspondent
    When I confirm the primary correspondent
    Then the "Complaint Correspondents Invalid" page should be displayed

#     BF STAGE 2 COMPLAINTS

  @ComplaintsWorkflow @ComplaintsRegression2 @BFComplaints
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
    And the summary should display the owning team as "Border Force (Stage 2)"
    And the read-only Case Details accordion should contain all case information entered during the "Case Registration (Stage 2)" stage
    And the summary should display "Service" for "Complaint Type"