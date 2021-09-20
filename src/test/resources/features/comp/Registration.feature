@Registration @COMP
Feature: Registration

  Background:
    Given I am logged into "CS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen

#   HOCS-2999, HOCS-2858, HOCS-2859, HOCS-2860, HOCS-2862, HOCS-2881, HOCS-2899, HOCS-2648
  @COMPWorkflow @COMPRegression
  Scenario: User can complete the Registration stage for a Service complaint
    And I add a "Complainant" correspondent
    And I confirm the primary correspondent
    And I enter the Complainant Details
    And I select "Service" as the Complaint Type
    And I enter the complaint details on the Complaint Input page
    And I select a "Service" Complaint Category
    And I select a Owning CSU
    When I click the "Finish" button
    Then the case should be moved to the "Service Triage" stage
    And the summary should display the owning team as "CCT Stage 1 Triage Team"
    And the read-only Case Details accordion should contain all case information entered during the "Registration" stage

#   HOCS-2709, HOCS-2858
  @COMPRegression
  Scenario: User must add a Complainant type correspondent
    And I add a "Third Party Representative" correspondent
    When I confirm the primary correspondent
    Then the "Complaint Correspondents Invalid" page should be displayed

  @Validation
  Scenario Outline: User tests the validation at the Registration stage
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