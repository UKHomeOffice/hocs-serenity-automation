Feature: User decides how cases should be handled at Minister Sign Off stage
  
  Background: 
    Given I am user "EAMON"
    When I am at the "MINISTER SIGNOFF" stage

  @Validation
  Scenario: User must select a radio button when asked whether or not they approve the response at the Minister Sign Off stage
    And I click the continue button on the approve response screen
    Then an error message should be displayed as I have not selected a radio button on the approve response screen

  @Validation
  Scenario: User must enter feedback in a text box if they do not approve the Minister Sign Off response
    And I click the continue button on the minister sign off feedback response screen
    Then an error message should be displayed as I have not entered feedback in the text box