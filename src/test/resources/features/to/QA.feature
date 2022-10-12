  @QA @TO
Feature: QA

  Background:
    Given I am logged into "CS" as user "TO_USER"

  @TOWorkflow @TORegression
  Scenario: As a QA user, I want to be able to approve the draft, so that the response can be dispatched
    When I get a "TO" case at the "QA" stage
    And I approve the draft response
    Then the case should be moved to the "Dispatch" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "QA" stage

  @TOWorkflow @TORegression
  Scenario: As a QA user, I want to be able to return a case to Triage, so corrections can be made
    When I get a "TO" case at the "QA" stage
    And I select to reject the case back to Triage stage
    And I submit a reason to reject the case
    Then the case should be moved to the "Triage" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "QA" stage
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case

  @TOWorkflow @TORegression
  Scenario: As a QA user, I want to be able to return a case to Draft, so corrections can be made
    When I get a "TO" case at the "QA" stage
    And I select to reject the case back to Draft stage
    And I submit a reason to reject the case
    Then the case should be moved to the "Draft" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "QA" stage
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case

  @TOWorkflow @TORegression
  Scenario: As a QA user, I want to be able to put a case into a campaign, so it can be answered along with other cases from that campaign
    When I get a "TO" case at the "QA" stage
    And I put the case into a "campaign"
    Then the case should be moved to the "Campaign" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected campaign
    And the summary should contain the selected campaign

  @TOWorkflow @TORegression
  Scenario: As a QA user, I want to be able to put a case onto a stop list, so we know not to continue case working the case
    When I get a "TO" case at the "QA" stage
    And I place the case on a stop list
    Then the case should be moved to the "Stop List" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected stop list
    And the summary should contain the selected stop list

  @TORegression
  Scenario: As a QA user, I want to be able to save changes to the case, so corrections can be made
    When I get a "TO" case at the "QA" stage
    And I open the "Case Details" accordion
    And I upload another "Initial Draft" document as a replacement
    When I open the "Case Details" accordion
    And I select the "replacement draft" document as the primary draft
    And I save the change of the primary draft
    And the replacement document should be tagged as the primary draft

  @TORegression
  Scenario: As a QA user, I want to be able to close the case early, so transferred or duplicate cases can be removed from the teams workstack
    When I get a "TO" case at the "QA" stage
    And I select to close the Treat Official case
    And I select a reason to close the case
    And I submit supporting details for the closure
    Then the case should be closed
    And the closure reason and details should be visible in the Case Details accordion
    And the closure reason and details should be visible in the Summary tab
    And a Case closure note should be visible in the timeline showing the submitted supporting details for closing the case

  @TORegression
  Scenario Outline: As a QA user, I want to be able to change the business area of the case to UKVI, so that the case is moved to the CCH stage
    When I get a TO case with "<businessArea>" as the business area and move the case to the "QA" stage
    And I load and claim the current case
    And I open the "Case Details" accordion
    And I change the Business Area of the TO case to "UKVI"
    Then the case should be moved to the "CCH Returns" stage
    And the summary should display the owning team as "Treat Official CCH"
    And a Case transfer reason note is visible in the timeline showing the reason for reallocation
    Examples:
      | businessArea  |
      | HMPO          |
      | BF            |

  @TORegression
  Scenario Outline: As a QA user, I want to be able to change the business area of the case, so that the correct team can casework it
    When I get a TO case with "<initialBusinessArea>" as the business area and move the case to the "QA" stage
    And I load and claim the current case
    And I open the "Case Details" accordion
    And I change the Business Area of the TO case to "<finalBusinessArea>"
    Then the case should be moved to the correct Treat Official team for the new business area
    And a Case transfer reason note is visible in the timeline showing the reason for reallocation
    Examples:
      | initialBusinessArea | finalBusinessArea |
      | UKVI                | HMPO              |
      | UKVI                | BF                |
      | HMPO                | BF                |
      | BF                  | HMPO              |
