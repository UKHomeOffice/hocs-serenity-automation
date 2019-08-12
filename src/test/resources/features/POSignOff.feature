Feature: User decides how cases should be handled at Private Office Sign Off stage

  Background: 
    Given I am user "EAMON"
    When I get a "DCU MIN" case at "PRIVATE OFFICE APPROVAL" stage

  @Navigation
  Scenario: Clicking the cancel button on the allocate case screen at the PO Sign Off stage should take the user back to the
  dashboard
    And I click the cancel button
    Then I should be taken to the homepage

  @Validation
  Scenario: User must select a radio button when asked whether they approve the Private Office response
    And I click the "CONTINUE" button
    Then an error message should be displayed as I have not selected whether I approve the response

  @Validation @NotAvailableInDTEN
  Scenario: If the user decides to change the case minister they must select an override team and enter their reasoning in the text box
    And I click the "FINISH" button on the "CHANGE MINISTER" page
    Then error messages should be displayed as I have not selected an override team or entered change reasoning

  @Validation
  Scenario: User must enter their feedback in the text box if they do not approve the Private Office response
    And I click the "FINISH" button on the "PO FEEDBACK RESPONSE" page
    Then an error message should be displayed as I have not entered feedback into the text box

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Private Office response stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"