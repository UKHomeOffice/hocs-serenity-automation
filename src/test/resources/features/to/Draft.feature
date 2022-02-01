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
    When I return the case to Triage
    Then the case should be moved to the "Triage" stage
    And the case should still be owned by the correct Treat Official team for the selected business area

  @TOWorkflow @TORegression
  Scenario: As a Draft user, I want to be able to put a case into a campaign, so it can be answered along with other cases from that campaign
    When I add an "Initial Draft" type document to the case
    And I put the case into a "Campaign"
    Then the case should be moved to the "Campaign" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected campaign
    And the summary should contain the selected campaign

  @TOWorkflow @TORegression
  Scenario: As a Draft user, I want to be able to put a case onto a stop list, so we know not to continue case working the case
    When I add an "Initial Draft" type document to the case
    And I place the case on a stop list
    Then the case should be moved to the "Stop List" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected stop list
    And the summary should contain the selected stop list

  @TORegression
  Scenario:  As a Draft user, I want to be able to change the business area of the case, so that the correct team can casework it
    When I open the "Case Details" accordion section
    And I change the Business Area of the case
    Then the case should be moved to the correct Treat Official team for the new business area

  @TORegression
  Scenario: As a Draft user, I want to be able to save changes to the case, so corrections can be made
    When I open the "Case Details" accordion section
    And I change the Business Unit Type and Business Unit of the case
    And I save the changes
    Then the amended value for Business Unit Type and Business Unit should be saved to the case


