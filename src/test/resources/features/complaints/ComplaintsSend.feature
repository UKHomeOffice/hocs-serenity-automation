@ComplaintsSend @Complaints
Feature: Complaints Send

#    HOCS-2722, HOCS-3076
  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can complete service send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Service Send" stage
    And I load the current case
    And I select a Case Outcome
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Service Send" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can complete Ex-Gratia send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Ex-Gratia Send" stage
    And I load the current case
    And I select a Case Outcome
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Ex-Gratia Send" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can complete Minor Misconduct send stage
    Given I am logged into "CS" as user "COMP_USER"
    When I create a "COMP" case and move it to the "Minor Misconduct Send" stage
    And I load the current case
    And I select a Case Outcome
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Minor Misconduct Send" stage

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can complete the Send stage for an IEDET case
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "Send" stage
    And I load the current case
    And I select a Case Outcome
    And I submit the Response details
    Then the case should be closed

  @ComplaintsPWorkflow @ComplaintsRegression
  Scenario: User can complete the Send stage for an SMC case
    Given I am logged into "CS" as user "SMC_USER"
    When I get a "SMC" case at the "Send" stage
    And I select a Case Outcome
    And I submit the SMC Send stage
    Then the case should be closed

  @ComplaintsWorkflow @ComplaintsRegression
  Scenario: User can complete the Send stage for a BF case
    Given I am logged into "CS" as user "BF_USER"
    When I get a "BF" case at the "Send" stage
    And I select a Case Outcome
    And I submit the Response details
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Send draft response" stage

  @Validation
  Scenario Outline: User tests the validation at the Service Send stage
    When I create a "COMP" case and move it to the "Service Send" stage
    And I load and claim the current case
    And I trigger the "<errorType>" error message at the "Service Send" stage
    Then the "<errorType>" error message is displayed at the "Service Send" stage
    Examples:
      | errorType                 |
      | CASE OUTCOME REQUIRED     |
      | RESPONSE CHANNEL REQUIRED |
      | DATE OF RESPONSE REQUIRED |