@AwaitingDispatchMinisterial @UKVI
Feature: Awaiting Dispatch (Ministerial)

  Background:
    Given I am logged into "CS" as user "UKVI_USER"
    And I create a "MPAM" case and move it to the "Awaiting Dispatch (Ministerial)" stage
    And I load and claim the current case

  @UKVIWorkflow @UKVIRegression1
  Scenario: As a Dispatch (ministerial) user, I want to record the dispatch details, so the case can be closed
    When I select a response channel
    And I enter a dispatched date
    And I confirm that the case has been dispatched
    Then the case should be closed

  @UKVIWorkflow @UKVIRegression1
  Scenario: As a Dispatch (local) user, I want to be able return a case to Private Office, in case there is a reason I cant complete the dispatch
    When I select to return the case to Private Office
    Then the case should be moved to the "Private Office" stage
    And the summary should display the owning team as "PO: UKVI/BF/IE Ministerial"

  @Validation
  Scenario Outline: User tests validation at the Awaiting Dispatch (Ministerial) stage
    And I trigger the "<errorType>" error message at the "Awaiting Dispatch (Ministerial)" stage
    Then the "<errorType>" error message is displayed at the "Awaiting Dispatch (Ministerial)" stage
    Examples:
      | errorType                 |
      | RESPONSE CHANNEL REQUIRED |
      | DISPATCHED DATE REQUIRED  |