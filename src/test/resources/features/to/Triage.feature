@Triage @TO
Feature: Triage

  Background:
    Given I am logged into "CS" as user "TO_USER"

#    Expected failure. Defect HOCS-4353 raised.
  @TOWorkflow @TORegression
  Scenario: As a Triage user, I want to be able to complete the Triage stage, so the case can progress to Drafting
    When I get a "TO" case at "Triage" stage
    And I set an Enquiry Subject and Reason
    And I select a Business Unit Type and corresponding Business Unit
    And I confirm the case is ready to be drafted
    Then the case should be moved to the "Draft" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Triage" stage
    And the summary should contain the Enquiry Subject, Enquiry Reason and Business Unit

  @TOWorkflow @TORegression
  Scenario: As a Triage user, I want to be able to put a case into a campaign, so it can be answered along with other cases from that campaign
    When I get a "TO" case at "Triage" stage
    And I set an Enquiry Subject and Reason
    And I select a Business Unit Type and corresponding Business Unit
    And I put the case into a "campaign"
    Then the case should be moved to the "Campaign" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected campaign
    And the summary should contain the selected campaign

  @TOWorkflow @TORegression
  Scenario: As a Triage user, I want to be able to put a case onto a stop list, so we know not to continue case working the case
    When I get a "TO" case at "Triage" stage
    And I set an Enquiry Subject and Reason
    And I select a Business Unit Type and corresponding Business Unit
    And I place the case on a stop list
    Then the case should be moved to the "Stop List" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected stop list
    And the summary should contain the selected stop list

  @TORegression
  Scenario Outline: As a Triage user, I want to be able to change the business area of the case to UKVI, so that the case is moved to the CCH stage
    When I get a TO case with "<businessArea>" as the business area and move the case to the "Triage" stage
    And I load and claim the current case
    And I open the "Case Details" accordion section
    And I change the Business Area of the TO case to "UKVI"
    Then the case should be moved to the "CCH Returns" stage
    And the summary should display the owning team as "Treat Official CCH"
    Examples:
    | businessArea  |
    | HMPO          |
    | BF            |

  @TORegression
  Scenario Outline: As a Triage user, I want to be able to change the business area of the case, so that the correct team can casework it
    When I get a TO case with "<initialBusinessArea>" as the business area and move the case to the "Triage" stage
    And I load and claim the current case
    And I open the "Case Details" accordion section
    And I change the Business Area of the TO case to "<finalBusinessArea>"
    Then the case should be moved to the correct Treat Official team for the new business area
    Examples:
    | initialBusinessArea | finalBusinessArea |
    | UKVI                | HMPO              |
    | UKVI                | BF                |
    | HMPO                | BF                |
    | BF                  | HMPO              |

  @TORegression
  Scenario Outline: As a Triage user, I want to be able to add, complete or cancel contributions on the case, so that requests can be tracked
    When I get a "TO" case at "Triage" stage
    And  I add a "Case" contribution request
    When I "<action>" the contribution request
    Then the "Case" contribution request should be marked as "<action>"
    Examples:
      | action   |
      | Complete |
      | Cancel   |

  @TORegression
  Scenario: As a Triage user, I want to be able to tell if a contribution is overdue, so I can chase the relevant business area for it
    When I get a "TO" case at "Triage" stage
    And I add a "case" contribution request with a due date in the past
    Then the "case" contribution request should be marked as "overdue"
    And the overdue contribution request should be highlighted

  @TORegression @Validation
  Scenario: As a Triage user, I want to be warned if I try to progress a case with open contributions, so cases are progressed prematurely
    When I get a "TO" case at "Triage" stage
    And I add a "Case" contribution request
    And I confirm the case is ready to be drafted
    Then an error message is displayed as I have not completed or cancelled all open contribution requests

  @TORegression
  Scenario: As a Triage user, I want to be able to save changes to the case, so corrections can be made
    When I get a "TO" case at "Triage" stage
    And I open the "Case Details" accordion section
    And I change the channel the correspondence was received by
    And I save the changes
    Then the amended value for Channel Received should be saved to the case

  @TORegression
  Scenario: As a Triage user, I want to be able to close the case early, so transferred or duplicate cases can be removed from the teams workstack
    When I get a "TO" case at "Triage" stage
    And I select to close the Treat Official case
    And I select a reason to close the case
    And I submit supporting details for the closure
    Then the case should be closed
    And the closure reason and details should be visible in the Case Details accordion
    And the closure reason and details should be visible in the Summary tab
    And a Case closure note should be visible in the timeline showing the submitted supporting details for closing the case


