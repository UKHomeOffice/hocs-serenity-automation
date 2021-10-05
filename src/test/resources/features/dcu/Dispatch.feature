@Dispatch @DCU
Feature: Dispatch

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: User dispatches a case
    And I get a "<caseType>" case at the "Dispatch" stage
    When I submit that I am able to dispatch the case
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Dispatch" stage
    Examples:
    | caseType  |
    | MIN       |
    | TRO       |
    | DTEN      |

  @DCUWorkflow @DCURegression
  Scenario: User dispatches a MIN case that should be copied to Number 10
    And I get a MIN case at the Dispatch stage that should be copied to Number 10
    When I submit that I am able to dispatch the case
    Then the case should be moved to the "Copy To Number 10" stage
    And the summary should display the owning team as "Transfers & No10 Team"
    And the read-only Case Details accordion should contain all case information entered during the "Dispatch" stage

  @DCUWorkflow @DCURegression
  Scenario Outline: User is unable to dispatch a MIN or DTEN case
    And I get a "<caseType>" case at the "Dispatch" stage
    And I select that I am unable to dispatch the case
    And I submit a reason why I am unable to dispatch the case
    Then the case should be moved to the "Private Office Approval" stage
    And the case should be returned to the Private Office team
    And the read-only Case Details accordion should contain all case information entered during the "Dispatch" stage
    And a note should be visible in the timeline showing the reason for rejection
    Examples:
      | caseType |
      | MIN      |
      | DTEN     |

  @DCUWorkflow @DCURegression
  Scenario: User is unable to dispatch a TRO case
    And I get a "TRO" case at the "Dispatch" stage
    And I select that I am unable to dispatch the case
    And I submit a reason why I am unable to dispatch the case
    Then the case should be moved to the "Initial Draft" stage
    And the case should be returned to the drafting team
    And the read-only Case Details accordion should contain all case information entered during the "Dispatch" stage
    And a note should be visible in the timeline showing the reason for rejection

  @Validation
  Scenario Outline: User tests the validation at the Dispatch stage
    When I create a "<caseType>" case and move it to the "Dispatch" stage
    And I load and claim the current case
    And I trigger the "<errorMessage>" error message at the "Dispatch" stage
    Then the "<errorMessage>" error message is displayed at the "Dispatch" stage
    Examples:
    | caseType  | errorMessage                |
    | MIN       | DISPATCH RESPONSE REQUIRED  |
    | TRO       | REJECTION NOTE REQUIRED     |