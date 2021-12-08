@CCH @Complaints
Feature: CCH

  Background:
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "CCH" stage
    And I load and claim the current case

#    HOCS-2944
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can transfer the case to CCT
    And I select the "Transfer to CCT" action at CCH
    Then the case should be moved to the "Service Triage" stage
    And the summary should display the owning team as "CCT Stage 1 Triage Team"
    And the summary should display "Service" for "Complaint Type"
    And the read-only Case Details accordion should contain all case information entered during the "CCH" stage

#    Expected failure. Defect HOCS-4238 raised.
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can transfer the case to Ex-Gratia
    And I select the "Transfer to Ex-Gratia" action at CCH
    Then the case should be moved to the "Ex-Gratia Triage" stage
    And the summary should display the owning team as "Ex-Gratia"
    And the summary should display "Ex-Gratia" for "Complaint Type"
    And the read-only Case Details accordion should contain all case information entered during the "CCH" stage

#    Expected failure. Defect HOCS-4238 raised.
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can transfer the case to Minor Misconduct
    And I select the "Transfer to Minor Misconduct" action at CCH
    Then the case should be moved to the "Minor Misconduct Triage" stage
    And the summary should display the owning team as "Minor Misconduct"
    And the summary should display "Minor Misconduct" for "Complaint Type"
    And the read-only Case Details accordion should contain all case information entered during the "CCH" stage

#    HOCS-3025
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can hard close a case at CCH stage
    And I select the "Complete the Case" action at CCH
    And I enter a completion note at CCH
    And I confirm I want to close the case at CCH
    Then the case should be closed
    And a Case closure note should be visible in the timeline showing the submitted reason for closing the case
    And the read-only Case Details accordion should contain all case information entered during the "CCH" stage

  @Validation
  Scenario Outline: User tests the validation at CCH
    And I trigger the "<errorType>" error message at "CCH"
    Then the "<errorType>" error message is displayed at "CCH"
    Examples:
      | errorType                                   |
      | TRANSFER TO REQUIRED                        |
      | COMPLETE CASE NOTE REQUIRED                 |
      | COMPLETE CASE PERMANENTLY RESPONSE REQUIRED |