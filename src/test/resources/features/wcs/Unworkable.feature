@Unworkable @WCS
Feature: Unworkable

#  HOCS-3233 and HOCS-3234
  @WCSRegression
  Scenario: As a WCS user, I want the ability to mark a case as 'Unworkable', so that I can focus on workable cases
    Given I am logged into "WCS" as user "WCS_USER"
    And I record the number of cases currently in the "WCS Triage Team" workstack, and how many of those are unallocated
    And I create a single "WCS" claim
    When I mark the case as unworkable
    And I send the unworkable case to Triage stage
    Then the number of cases in the "WCS Triage Team" workstack should not have increased
    And the number of unassigned cases in the "WCS Triage Team" workstack should not have increased
    And I shouldn't be able to see the case in the "WCS Triage Team" workstack


