@COMPSend @COMP
Feature: COMP Send

  Background:
    Given I am logged into "CS" as user "COMP_USER"

#    HOCS-2722, HOCS-3076
  @COMPWorkflow @COMPRegression
  Scenario: User can complete service send stage
    When I create a "COMP" case and move it to the "Service Send" stage
    And I load and claim the current case
    And I select a Case Outcome at the "Service" Send stage
    Then the case should be moved to the "Complaint Closed" stage
    And the summary should display the owning team as "CCH Closed Cases"
    And the read-only Case Details accordion should contain all case information entered during the "Service Send" stage

  @COMPWorkflow @COMPRegression
  Scenario: User can complete Ex-Gratia send stage
    When I create a "COMP" case and move it to the "Ex-Gratia Send" stage
    And I load and claim the current case
    And I select a Case Outcome at the "Ex-Gratia" Send stage
    Then the case should be moved to the "Complaint Closed" stage
    And the summary should display the owning team as "CCH Closed Cases"

  @COMPWorkflow @COMPRegression
  Scenario: User can complete Minor Misconduct send stage
    When I create a "COMP" case and move it to the "Minor Misconduct Send" stage
    And I load and claim the current case
    And I select a Case Outcome at the "Minor Misconduct" Send stage
    Then the case should be moved to the "Complaint Closed" stage
    And the summary should display the owning team as "CCH Closed Cases"

  @Validation
  Scenario Outline: User tests the validation at the Service Send stage
    When I create a "COMP" case and move it to the "Service Send" stage
    And I load and claim the current case
    And I trigger the "<errorType>" error message at the "Service Send" stage
    Then the "<errorType>" error message is displayed at the "Service Send" stage
    Examples:
      | errorType              |
      | CASE OUTCOME REQUIRED  |