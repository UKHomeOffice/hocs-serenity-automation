Feature: User can add notes and view logs in the timeline

  Background:
    Given I am user "Eamon"
    And I get a "MIN" case at "DATA INPUT" stage
    And I select the Timeline tab

  @Timeline @SmokeTests
  Scenario: User can add a case note to a case
    When I create a case note with random content
    Then a note should be created at the top of the timeline
    And it should have the same content
    And it should state that user "EAMON" created it

  @Timeline @Validation
  Scenario: User must enter text when creating a case note
    When I attempt to add an empty case note
    Then an error message is displayed as I have not entered text content for the case note

  @Timeline @SmokeTests
  Scenario: Notes are ordered by creation datetime
    When I create a case note with random content
    And I create another case note with random content
    Then the top note in the timeline should be the second note created
    And the one below it should be the first note created

  @Timeline @SmokeTests
  Scenario: User can view logs for when a case has been allocated
    Then there should be a log showing the case was allocated to user "EAMON" at stage "Data Input"

  @Timeline @SmokeTests
  Scenario: User can view logs for completing one stage and starting another
    And I complete the Data Input stage of the displayed case
    And I load the current case
    And I select the Timeline tab
    Then a log should be at the top of the timeline
    And a log should be visible for completing the "Data Input" stage
    And a log should be visible for starting the "Markup" stage
