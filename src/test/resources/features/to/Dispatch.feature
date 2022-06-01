@Dispatch @TO
Feature: Dispatch

  Background:
    Given I am logged into "CS" as user "TO_USER"
    And I get a "TO" case at the "Dispatch" stage

  @TOWorkflow @TORegression
  Scenario: As a Disptach user, I want to be able to enter the details of dispatch, so the case can be closed
    When I add a "Final Response" type document to the case
    And I enter the details of the dispatch
    And I confirm the case is completed
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Dispatch" stage

  @TORegression
  Scenario: As a Dispatch user, I want to be able to save changes to the case, so corrections can be made
    When I open the "Case Details" accordion section
    And I change the channel the correspondence was received by
    And I save the changes
    Then the amended value for Channel Received should be saved to the case

  @TOWorkflow @TORegression
  Scenario: As a Dispatch user, I want to be able to put a case into a campaign, so it can be answered along with other cases from that campaign
    When I put the case into a "campaign"
    Then the case should be moved to the "Campaign" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected campaign
    And the summary should contain the selected campaign

  @TOWorkflow @TORegression
  Scenario: As a Dispatch user, I want to be able to put a case onto a stop list, so we know not to continue case working the case
    When I place the case on a stop list
    Then the case should be moved to the "Stop List" stage
    And the case should still be owned by the correct Treat Official team for the selected business area
    And the Case Details accordion should contain the selected stop list
    And the summary should contain the selected stop list
