Feature: User is able to add Standard Lines

  Background:
    Given I am user "EAMON"
    When I navigate to the "ADD STANDARD LINE" page


#  @AddStandardLine
#  Scenario: User is able to add a standard line
#    And I create a new standard line
#    Then

  @Validation
  Scenario: User must select an associated topic from the dropdown box when adding a new standard line
    And I click the add button on the create standard line screen
    Then an error message should be displayed as I have not selected an associated topic for the standard line

  @Validation
  Scenario: User must enter an expiration date when adding a new standard line
    And I click the add button on the create standard line screen
    Then an error message should be displayed as I have not entered an expiration date for the standard line

  @Validation
  Scenario: User must upload a document when adding a new standard line
    And I click the add button on the create standard line screen
    Then an error message should be displayed as I have not added a document for the standard line