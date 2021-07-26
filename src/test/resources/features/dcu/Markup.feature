@Markup @DCU
Feature: Markup

  Background:
    Given I am logged into "CS" as user "DCU_USER"


  Scenario Outline: Central Drafting Team user selects an initial decision of Policy Response or FAQ
    When I create a "DTEN" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I select an initial decision of "<radioButton>"
    And I click the "Continue" button
    And I click the Add a topic link
    Then a mandatory Topic free text field is displayed
    Examples:
      | radioButton     |
      | Policy Response |
      | FAQ             |


  Scenario: User selects an initial decision of Refer to OGD
    When I create a "DTEN" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I select an initial decision of "REFER TO OGD"
    And I click the "Continue" button
    Then the Other Government Department name free text field is displayed

  @DCUWorkflow @DCURegression
  Scenario Outline: User selects Refer to OGD
    When I create a "<caseType>" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I select an initial decision of "REFER TO OGD"
    And I click the "Continue" button
    And I enter a transfer destination and transfer reason
    And I click the "Finish" button
    Then the case should be moved to the "Transfer Confirmation" stage
    Examples:
    | caseType  |
    | MIN       |
    | TRO       |
    | DTEN      |


  Scenario: User selects an initial decision of No Response Needed
    When I create a "DTEN" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I select an initial decision of "NO RESPONSE NEEDED"
    And I click the "Continue" button
    Then the No Response Needed casenote field is displayed

  @DCUWorkflow @DCURegression
  Scenario Outline: User selects no response needed
    When I create a "<caseType>" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I select an initial decision of "NO RESPONSE NEEDED"
    And I click the "Continue" button
    And I enter a reason that no response is needed
    And I click the "Finish" button
    Then the case should be moved to the "No Response Needed Confirmation" stage
    Examples:
      | caseType  |
      | MIN       |
      | TRO       |
      | DTEN      |

  @Validation
  Scenario: User does not enter other government department in free text field
    When I create a "DTEN" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I select an initial decision of "Refer to OGD"
    And I click the "Continue" button
    But I do not enter a "Other Government Department"
    Then an error message is displayed


  Scenario: User selects an initial decision of Reject to Data Input
    When I create a "DTEN" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I select an initial decision of "REJECT TO DATA INPUT"
    And I click the "Continue" button
    Then the reason for rejection casenote field is displayed

  @Validation
  Scenario: User does not enter reasons for no reply needed
    When I create a "DTEN" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I select an initial decision of "NO RESPONSE NEEDED"
    And I click the "Continue" button
    But I do not enter a "REASON FOR NO RESPONSE NEEDED"
    Then an error message is displayed

  @Validation
  Scenario: User does not enter reason for rejecting case to Data Input
    When I create a "DTEN" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I select an initial decision of "REJECT TO DATA INPUT"
    And I click the "Continue" button
    But I do not enter a "REASON FOR REJECTING TO DATA INPUT"
    Then an error message is displayed


  Scenario: User selects topic
    When I create a "DTEN" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I select an initial decision of "POLICY RESPONSE"
    And I click the "Continue" button
    And I add the topic "Animal alternatives (3Rs)"
    Then the topic should be added to the case


  Scenario: User can select a topic for a FAQ response
    When I create a "DTEN" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I select an initial decision of "FAQ"
    And I click the "Continue" button
    And I add the topic "Animal alternatives (3Rs)"
    Then the topic should be added to the case

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Markup stage
    When I create a "DTEN" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box

  @DCUWorkflow @DCURegression
  Scenario Outline: Case is returned to Data Input stage when rejected at Markup stage
    When I create a "<caseType>" case and move it to the "MARKUP" stage
    And I load and claim the current case
    And I reject the case at the "MARKUP" stage
    Then the case should be moved to the "DATA INPUT" stage
    Examples:
    | caseType  |
    | MIN       |
    | TRO       |
    | DTEN      |

  @Validation
  Scenario Outline: User tests the validation at the Markup stage
    When I create a "<caseType>" case and move it to the "Markup" stage
    And I load and claim the current case
    And I trigger the "<errorMessage>" error message at the "Markup" stage
    Then the "<errorMessage>" error message is displayed at the "Markup" stage
    Examples:
      | caseType  | errorMessage                                  |
      | MIN       | Type of Response Required                     |
      | TRO       | Primary Topic Required                        |
      | DTEN      | Response Approval Required                    |
      | MIN       | Why is no Response Needed Required            |
      | TRO       | Where Should the Case be Transferred Required |
      | DTEN      | Reason for Transfer Required                  |