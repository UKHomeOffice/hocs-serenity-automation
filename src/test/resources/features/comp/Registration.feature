@Registration  @COMP
Feature: Registration

  Background:
    Given I am logged into "DECS" as user "COMP_USER"
    And I create a single "COMP" case
    And I allocate the case to myself via the successful case creation screen

  @COMPWorkflow @COMPRegression
  Scenario: User can complete the Registration stage for a Service complaint
    And I add a "Complainant" correspondent
    And I click the "Continue" button
    And I enter the Complainant Details
    And I select "Service" as the Complaint Type
    And I enter the complaint details on the Complaint Input page
    And I click the "Continue" button
    And I select a "Service" Complaint Category
    And I select a Owning CSU
    When I click the "Continue" button
    Then the case should be moved to the "Service Triage" stage

  @COMPRegression
  Scenario: User must add a Complainant type correspondent
    And I add a "Third Party Representative" correspondent
    When I click the "Continue" button
    Then the "Complaint Correspondents Invalid" page should be displayed

  @Validation
  Scenario: User tests the validation at the Registration stage
    And I test the validation at the Complaints Registration stage