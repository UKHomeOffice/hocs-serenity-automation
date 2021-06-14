@CCH @COMP
Feature: CCH

  Background:
    Given I am logged into "DECS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "CCH" stage
    And I load and claim the current case

#    HOCS-2944
  @COMPWorkflow @COMPRegression
  Scenario: User can transfer the case to CCT
    When I select to Transfer the case to CCT
    Then the case should be moved to the "Service Triage" stage
    And the summary should display the owning team as "CCT Stage 1 Triage Team"

#    HOCS-3025
  @COMPWorkflow @COMPRegression
  Scenario: User can hard close a case at CCH stage
    When I select to complete the case
    And I enter a completion note
    And I confirm I want to close the case
    Then the case should be closed
    And a case closure note should be visible showing the reason for closure

