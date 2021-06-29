@POSignOff @DCU
Feature: Private Office Approval

  Background:
    Given I am logged into "DECS" as user "DCU_USER"

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Private Office response stage
    And I create a "MIN" case and move it to the "PRIVATE OFFICE APPROVAL" stage
    And I load and claim the current case
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box

  @DCURegression
  Scenario: User can change the minister for the case
    And I create a "MIN" case and move it to the "PRIVATE OFFICE APPROVAL" stage
    And I load and claim the current case
    When I select to change minister
    And I select "Home Secretary" as the new Private Office team
    And I enter "TEST OVERRIDE PO TEAM" as the reason for changing Private Office team
    When I click the "Finish" button
    And I select the "Private Office Approval" button of the accordion
    Then the information shown should match what I entered on the change Private Office Team page

  @DCUWorkflow @DCURegression
  Scenario Outline: User can return a case to the Initial Draft stage
    And I create a "<caseType>" case and move it to the "PRIVATE OFFICE APPROVAL" stage
    And I load and claim the current case
    And I reject the case at the "Private Office Approval" stage
    Then the case should be moved to the "Initial Draft" stage
    Examples:
      | caseType |
      | MIN  |
      | DTEN |

  Scenario: User can override the Primary topic at the Private Office stage
    And I create a "MIN" case and move it to the "Private Office Approval" stage
    And I load and claim the current case
    And I override the Primary Topic of the case at the Private Office stage to "Breeding of research animals"
    And I navigate to the "Dashboard" page
    And I load and claim the current case
    Then the "Primary Topic" of the case should be updated to "Breeding of research animals" in the summary tab
    And the "Team" of the case should be updated to "Minister for Lords" in the summary tab
    And the reason for changing the primary topic of the case should be added as a case note in the timeline

  Scenario: User overrides the PO team at Markup and moves the case to the PO stage
    And I create a "MIN" case and move it to the "Markup" stage
    And I load and claim the current case
    And I assign the Topic "Animal Alternatives (3Rs)"
    And I override the "Private Office" team to "Home Secretary"
    And I load and claim the current case
    Then the "Team" of the case should be updated to "Home Secretary" in the summary tab
    And the "Override Private Office Team" of the case should be updated to "Home Secretary" in the summary tab

  @Validation
  Scenario Outline: User tests the validation at the Private Office Approval stage
    When I create a "<caseType>" case and move it to the "Private Office Approval" stage
    And I load and claim the current case
    And I trigger the "<errorMessage>" error message at the "Private Office Approval" stage
    Then the "<errorMessage>" error message is displayed at the "Private Office Approval" stage
    Examples:
      | caseType  | errorMessage                          |
      | DTEN      | Response Approval Required            |
      | DTEN      | Rejection Note Required               |
      | MIN       | Override Private Office Team Required |
      | MIN       | Reason for Change Minister Required   |
      | MIN       | Reason for Topic Change Required      |