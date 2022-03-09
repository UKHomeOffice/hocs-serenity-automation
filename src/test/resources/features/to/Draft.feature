@Draft @TO
Feature: Draft

  Background:
    Given I am logged into "CS" as user "TO_USER"
    And I get a "TO" case at the "Draft" stage

  @TOWorkflow @TORegression
  Scenario: As a Draft user, I want to be able to send a case to Dispatch, so the reply can be sent out
    When I add an "Initial Draft" type document to the case
    And I send the Treat Official case to Dispatch
    Then the case should be moved to the "Dispatch" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Draft" stage
    And the selected document should be tagged as the primary draft

  @TOWorkflow @TORegression
  Scenario: As a Draft user, I want to be able to send a case to QA, so the draft reply can be reviewed
    When I add an "Initial Draft" type document to the case
    And I send the Treat Official case to QA
    Then the case should be moved to the "QA" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Draft" stage
    And the selected document should be tagged as the primary draft

  @TOWorkflow @TORegression
  Scenario: As a Draft user, I want to be able to return a case to Triage, so corrections can be made
    When I select to return the TO case to Triage
    And I submit a reason to return the case to Triage
    Then the case should be moved to the "Triage" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Draft" stage
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case

  @TOWorkflow @TORegression
  Scenario: As a Draft user, I want to be able to put a case into a campaign, so it can be answered along with other cases from that campaign
    When I put the case into a "Campaign"
    Then the case should be moved to the "Campaign" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected campaign
    And the summary should contain the selected campaign

  @TOWorkflow @TORegression
  Scenario: As a Draft user, I want to be able to put a case onto a stop list, so we know not to continue case working the case
    When I place the case on a stop list
    Then the case should be moved to the "Stop List" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected stop list
    And the summary should contain the selected stop list

  @TORegression
  Scenario Outline: As a Draft user, I want to be able to change the business area of the case to UKVI, so that the case is moved to the CCH stage
    When I get a TO case with "<businessArea>" as the business area and move the case to the "Draft" stage
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
  Scenario Outline: As a Draft user, I want to be able to change the business area of the case, so that the correct team can casework it
    When I get a TO case with "<initialBusinessArea>" as the business area and move the case to the "Draft" stage
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
  Scenario: As a Draft user, I want to be able to save changes to the case, so corrections can be made
    When I open the "Case Details" accordion section
    And I change the Business Unit Type and Business Unit of the case
    And I save the changes
    Then the amended value for Business Unit Type and Business Unit should be saved to the case

  @TORegression
  Scenario: As a Draft user, I want to be able to close the case early, so transferred or duplicate cases can be removed from the teams workstack
    When I select to close the Treat Official case
    And I select a reason to close the case
    And I submit supporting details for the closure
    Then the case should be closed
    And the closure reason and details should be visible in the Case Details accordion
    And the closure reason and details should be visible in the Summary tab
    And a Case closure note should be visible in the timeline showing the submitted supporting details for closing the case


