@ServiceEscalated @COMP
Feature: Service Escalated

  Background:
    Given I am logged into "CS" as user "COMP_USER"
    And I create a "COMP" case and move it to the "Service Escalated" stage
    And I load and claim the current case

#    HOCS-3076, HOCS-3028
  @COMPWorkflow @COMPRegression
  Scenario: User can return the case to Service Triage stage
    And I select to return the case to Triage
    Then the case should be moved to the "Service Triage" stage
    And the summary should display the owning team as "CCT Stage 1 Triage Team"
    And the read-only Case Details accordion should contain all case information entered during the "Service Escalated" stage

#    HOCS-3076, HOCS-3028
  @COMPWorkflow @COMPRegression
  Scenario: User can send the case to Service Draft stage
    And I select to send the case to drafting
    Then the case should be moved to the "Service Draft" stage
    And the summary should display the owning team as "CCT Stage 1 Response Team"
    And the read-only Case Details accordion should contain all case information entered during the "Service Escalated" stage

#    HOCS-2870, HOCS-3096
  @COMPRegression
  Scenario Outline: User can add and complete or cancel contributions as part of Service Escalated stage
    And I add a "<contributionType>" contribution request
    And I "<action>" the contribution request
    Then the "<contributionType>" contribution request should be marked as "<action>"
    Examples:
      | contributionType | action   |
      | Complainant      | Complete |
      | Business         | Cancel   |
      | Complainant      | Complete |
      | Business         | Cancel   |

  @Validation
  Scenario Outline: User tests the validation at the Service Escalated stage
    When I trigger the "<errorType>" error message at the "Service Escalated" stage
    Then the "<errorType>" error message is displayed at the "Service Escalated" stage
    Examples:
      | errorType       |
      | Action Required |