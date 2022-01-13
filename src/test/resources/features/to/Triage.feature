@Triage @TO
Feature: Triage

  Background:
    Given I am logged into "CS" as user "TO_USER"
    And I get a "TO" case at "Triage" stage

#    Expected failure. Defect HOCS-4353 raised.
  @TOWorkflow @TORegression
  Scenario: As a Triage user, I want to be able to complete the Triage stage, so the case can progress to Drafting
    When I set an Enquiry Subject and Reason
    And I select a Business Unit Type and corresponding Business Unit
    And I confirm the case is ready to be drafted
    Then the case should be moved to the "Draft" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Triage" stage
    And the summary should contain the Enquiry Subject and Enquiry Reason

  @TORegression
  Scenario Outline: As a Triage user, I want to be able to add, complete or cancel contributions on the case, so that requests can be tracked
    And I add a "Case" contribution request
    When I "<action>" the contribution request
    Then the "Case" contribution request should be marked as "<action>"
    Examples:
      | action   |
      | Complete |
      | Cancel   |

  @TORegression
  Scenario: As a Triage user, I want to be able to tell if a contribution is overdue, so I can chase the relevant business area for it
    And I add a "case" contribution request with a due date in the past
    Then the "case" contribution request should be marked as "overdue"
    And the overdue contribution request should be highlighted

  @TORegression @Validation
  Scenario: As a Triage user, I want to be warned if I try to progress a case with open contributions, so cases are progressed prematurely
    And I add a "Case" contribution request
    And I confirm the case is ready to be drafted
    Then an error message is displayed as I have not completed or cancelled all open contribution requests

  Scenario: Validation

