@HomeSecretaryInterest @TO
Feature: Home Secretary Interest

  Background:
    Given I am logged into "CS" as user "TO_USER"

  @TOWorkflow @TORegression
  Scenario: As a Case Manager, I want a case with Home Secretary Interest to require additional approval, so that an incorrect reply is not dispatched
    And I get a Treat Official case at the "Draft" stage that has Home Secretary Interest
    When I add an "Initial Draft" type document to the case
    And I send the Treat Official case to Dispatch
    Then the case should be moved to the "Home Secretary Interest" stage

  @TOWorkflow @TORegression
  Scenario: As a Case Manager, I want a case with Home Secretary Interest to require additional approval, so that an incorrect reply is not dispatched
    And I get a Treat Official case at the "QA" stage that has Home Secretary Interest
    And I complete the "QA" stage
    Then the case should be moved to the "Home Secretary Interest" stage

  @TOWorkflow @TORegression
  Scenario: As a Home Secretary Interest user, I want to be able to approve the draft, so that the response can be dispatched
    And I get a "TO" case at the "Home Secretary Interest" stage
    When I approve the draft response
    Then the case should be moved to the "Dispatch" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Home Secretary Interest" stage

  @TOWorkflow @TORegression
  Scenario: As a Home Secretary Interest user, I want to be able to return a case to Triage, so corrections can be made
    And I get a "TO" case at the "Home Secretary Interest" stage
    When I select to reject the case back to Triage stage
    And I submit a reason to reject the case
    Then the case should be moved to the "Triage" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Home Secretary Interest" stage
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case

  @TOWorkflow @TORegression
  Scenario: As a Home Secretary Interest user, I want to be able to return a case to Draft, so corrections can be made
    And I get a "TO" case at the "Home Secretary Interest" stage
    When I select to reject the case back to Draft stage
    And I submit a reason to reject the case
    Then the case should be moved to the "Draft" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the read-only Case Details accordion should contain all case information entered during the "Home Secretary Interest" stage
    And a Rejection note should be visible in the timeline showing the submitted reason for the return of the case

  @TOWorkflow @TORegression
  Scenario: As a Home Secretary Interest user, I want to be able to put a case into a campaign, so it can be answered along with other cases from that campaign
    And I get a "TO" case at the "Home Secretary Interest" stage
    When I put the case into a "campaign"
    Then the case should be moved to the "Campaign" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected campaign
    And the summary should contain the selected campaign

  @TOWorkflow @TORegression
  Scenario: As a Home Secretary Interest user, I want to be able to put a case onto a stop list, so we know not to continue case working the case
    And I get a "TO" case at the "Home Secretary Interest" stage
    When I place the case on a stop list
    Then the case should be moved to the "Stop List" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected stop list
    And the summary should contain the selected stop list

  @TORegression
  Scenario: As a Home Secretary Interest user, I want to be able to save changes to the case, so corrections can be made
    And I get a "TO" case at the "Home Secretary Interest" stage
    When I open the "Case Details" accordion section
    And I upload another "Initial Draft" document as a replacement
    When I open the "Case Details" accordion section
    And I select the "replacement draft" document as the primary draft
    And I save the change of the primary draft
    And the replacement document should be tagged as the primary draft