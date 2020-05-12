@POSignOff
Feature: Private Office Approval

  Background:
    Given I am user "AUTOMATION_USER"

  @Validation
  Scenario: User must select a radio button when asked whether they approve the Private Office response
    When I create a "MIN" case and move it to the "PRIVATE OFFICE APPROVAL" stage
    And I load and claim the current case
    And I click the "Continue" button
    Then an error message should be displayed as I have not selected whether I approve the response

  @Validation
  Scenario: If the user decides to change the case minister they must select an override team and enter their reasoning in the text box
    When I create a "MIN" case and move it to the "PRIVATE OFFICE APPROVAL" stage
    And I load and claim the current case
    And I click the "Finish" button on the "CHANGE MINISTER" page
    Then error messages should be displayed as I have not selected an override team or entered change reasoning

  @Validation
  Scenario: User must enter their feedback in the text box if they do not approve the Private Office response
    And I create a "MIN" case and move it to the "PRIVATE OFFICE APPROVAL" stage
    And I load and claim the current case
    And I click the "Finish" button on the "PO FEEDBACK RESPONSE" page
    Then an error message should be displayed as I have not entered feedback into the text box

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Private Office response stage
    And I create a "MIN" case and move it to the "PRIVATE OFFICE APPROVAL" stage
    And I load and claim the current case
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box

  @SmokeTests
  Scenario: User can change the minister for the case
    And I create a "MIN" case and move it to the "PRIVATE OFFICE APPROVAL" stage
    And I load and claim the current case
    When I select to change minister
    And I select "Home Secretary" as the new Private Office team
    And I enter "TEST OVERRIDE PO TEAM" as the reason for changing Private Office team
    When I click the "Finish" button
    And I select the "Private Office Approval" button of the accordion
    Then the information shown should match what I entered on the change Private Office Team page

  @DCUWorkflow @SmokeTests
  Scenario Outline: Case is returned to Initial Draft stage when rejected by Private Office Approval Team
    And I create a "<caseType>" case and move it to the "PRIVATE OFFICE APPROVAL" stage
    And I load and claim the current case
    And I reject the case at the "Private Office Approval" stage
    Then the case should be moved to the "Initial Draft" stage
    Examples:
      | caseType |
      | MIN  |
      | DTEN |

