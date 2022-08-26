@AwaitingDispatchLocal @MPAM
Feature: Awaiting Dispatch (Local)

  Background:
    Given I am logged into "CS" as user "MPAM_USER"
    And I create a "MPAM" case and move it to the "Awaiting Dispatch (Local)" stage
    And I load and claim the current case

  @MPAMWorkflow
  Scenario: As a Dispatch (local) user, I want to record the dispatch details, so the case can be closed
    When I select a response channel
    And I enter a dispatched date
    And I confirm that the case has been dispatched
    Then the case should be closed

  @MPAMWorkflow
  Scenario: As a Dispatch (local) user, I want to be able return a case to Private Office, in case there is a reason I cant complete the dispatch
    When I select to return the case to Private Office
    Then the case should be moved to the "Private Office" stage
    And the summary should display the owning team as "PO: UKVI/BF/IE Ministerial"

  @Validation
  Scenario Outline: User tests validation at the Awaiting Dispatch (Local) stage
    And I trigger the "<errorType>" error message at the "Awaiting Dispatch (Local)" stage
    Then the "<errorType>" error message is displayed at the "Awaiting Dispatch (Local)" stage
    Examples:
      | errorType                 |
      | RESPONSE CHANNEL REQUIRED |
      | DISPATCHED DATE REQUIRED  |