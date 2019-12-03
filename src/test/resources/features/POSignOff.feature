Feature: User decides how cases should be handled at Private Office Sign Off stage

  Background:
    Given I log in as the designated user
    When I get a "DCU MIN" case at "PRIVATE OFFICE APPROVAL" stage

  @POSignOff @Validation
  Scenario: User must select a radio button when asked whether they approve the Private Office response
    And I click the "CONTINUE" button
    Then an error message should be displayed as I have not selected whether I approve the response

  @POSignOff @Validation @NotAvailableInDTEN
  Scenario: If the user decides to change the case minister they must select an override team and enter their reasoning in the text box
    And I click the "FINISH" button on the "CHANGE MINISTER" page
    Then error messages should be displayed as I have not selected an override team or entered change reasoning

  @POSignOff @Validation
  Scenario: User must enter their feedback in the text box if they do not approve the Private Office response
    And I click the "FINISH" button on the "PO FEEDBACK RESPONSE" page
    Then an error message should be displayed as I have not entered feedback into the text box

  @POSignOff @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Private Office response stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"

  @POSignOff
  Scenario:
    When I select to change minister
    And I select "Home Secretary" as the new Private Office team
    And I enter "TEST OVERRIDE PO TEAM" as the reason for changing Private Office team
    When I click the "Finish" button
    And I select the "Private Office Approval" button of the accordion
    Then the information shown should match what I entered on the change Private Office Team page

