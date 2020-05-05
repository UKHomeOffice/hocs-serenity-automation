@Timeline
Feature: Timeline

  Background:
    Given I am user "AUTOMATION_USER"
    And I create a "MIN" case and move it to the "DATA INPUT" stage
    And I load and claim the current case
    And I select the Timeline tab

  @SmokeTests
  Scenario: User can add a case note to a case
    When I create a case note with random content
    Then a note should be created at the top of the timeline
    And it should have the same content
    And it should state that user "AUTOMATION_USER" created it

  @Validation
  Scenario: User must enter text when creating a case note
    When I attempt to add an empty case note
    Then an error message is displayed as I have not entered text content for the case note

  @SmokeTests
  Scenario: Notes are ordered by creation datetime
    When I create a case note with random content
    And I create another case note with random content
    Then the top note in the timeline should be the second note created
    And the one below it should be the first note created

  @SmokeTests
  Scenario: User can view logs for when a case has been allocated
    Then there should be a log showing the case was allocated to user "AUTOMATION_USER" at stage "Data Input"

  @SmokeTests
  Scenario: User can view logs for completing one stage and starting another
    And I complete the "Data Input" stage
    And I load the current case
    And I select the Timeline tab
    Then a log should be at the top of the timeline
    And a log should be visible for completing the "Data Input" stage
    And a log should be visible for starting the "Markup" stage

  @SmokeTests
  Scenario: User can add a case note and can then edit this case note
    And I create a case note with random content
    And I edit the top case note
    Then the case note should contain the edited content

  @SmokeTests
  Scenario: User can add a case note progress the case and the case note should stay in its place in the timeline
    And I create a case note with random content
    And I complete the Data Input Stage
    And I load and claim the current case
    And I select the Timeline tab
    And I edit the top case note
    Then the case note should appear between the "DATA INPUT" logs
