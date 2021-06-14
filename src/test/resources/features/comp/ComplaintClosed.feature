@ComplaintClosed @COMP
Feature: Complaint Closed

  Background:
    Given I am logged into "DECS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Complaint Closed" stage
    And I load and claim the current case

#    HOCS-2716
  @COMPWorkflow @COMPRegression
  Scenario: User can re-open a case that has been through CCT Stage 1
    When I re-open the case
    Then the case should be moved to the "Service Triage" stage
    And the summary should display the owning team as "CCT Stage 2 Triage Team"

#    HOCS-3025
  @COMPWorkflow @COMPRegression
  Scenario: User can hard close a case at Complaint Closed stage
    When I select to complete the case
    And I enter a completion note
    And I confirm I want to close the case
    Then the case should be closed
    And a case closure note should be visible showing the reason for closure