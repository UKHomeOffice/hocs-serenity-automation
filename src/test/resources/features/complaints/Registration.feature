@Registration @COMP
Feature: Registration

#   HOCS-2999, HOCS-2858, HOCS-2859, HOCS-2860, HOCS-2862, HOCS-2881, HOCS-2899, HOCS-2648
  @COMPWorkflow @COMPRegression
  Scenario: User can complete the Registration stage for a Service complaint
    Given I am logged into "CS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Complainant" correspondent
    And I click the "Continue" button
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

#   HOCS-2709, HOCS-2858
  @COMPRegression
  Scenario: User must add a Complainant type correspondent
    Given I am logged into "CS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen
    And I add a "Third Party Representative" correspondent
    When I click the "Continue" button
    Then the "Complaint Correspondents Invalid" page should be displayed

  #HOCS-3441, HOCS-3442
  Scenario: User can complete the Registration stage for a Ex-Gratia complaint
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

  Scenario: User can complete the Registration stage for a Minor Misconduct complaint
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

  @COMPWorkflow @COMPRegression
  Scenario: User can complete the Registration stage for an IEDET case
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

  Scenario: User can navigate to a COMP case using the case reference displayed in COMP2 summary
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP2" case and move it to the "Stage 2 Registration" stage
    And I load the current case
    And I select the previous COMP case reference from the COMP2 case summary tab
    Then the previous COMP case is displayed

  @Validation
  Scenario Outline: User tests the validation at the Registration stage
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