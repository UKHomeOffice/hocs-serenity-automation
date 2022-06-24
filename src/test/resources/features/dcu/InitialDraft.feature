@InitialDraft @DCU
Feature: Initial Draft

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: User responds by Phone and fills out call details to complete the case
    And I get a "<caseType>" case at the "Initial Draft" stage
    And I select a case "should" be answered by my team
    When I select to reply by "phone"
    And I complete the call details
    And I select "No" to choosing another Response Type
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Initial Draft" stage
    Examples:
      | caseType  |
      | MIN       |
      | TRO       |

  @DCUWorkflow @DCURegression
  Scenario Outline: A user selects not to offline QA a MIN or TRO case
    And I get a "<caseType>" case at the "Initial Draft" stage
    When I select a case "should" be answered by my team
    And I select to reply by "email"
    And I upload my Primary Draft document
    And I confirm the primary draft document
    And I select that the QA Process should be completed on DECS
    Then the case should be moved to the "QA Response" stage
    And the case should still be owned by the drafting team
    And the read-only Case Details accordion should contain all case information entered during the "Initial Draft" stage
    And the selected document should be tagged as the primary draft
    Examples:
      | caseType  |
      | MIN       |
      | TRO       |

  @DCUWorkflow @DCURegression
  Scenario: User selects not to offline QA a DTEN case
    And I get a "DTEN" case at the "Initial Draft" stage
    When I select a case "should" be answered by my team
    And I upload my Primary Draft document
    And I confirm the primary draft document
    And I select that the QA Process should be completed on DECS
    Then I am returned to the dashboard
    And the case should be moved to the "QA Response" stage
    And the case should still be owned by the drafting team
    And the read-only Case Details accordion should contain all case information entered during the "Initial Draft" stage
    And the selected document should be tagged as the primary draft

  @DCUWorkflow @DCURegression
  Scenario: User selects offline QA for a MIN case
    And I get a "MIN" case at the "INITIAL DRAFT" stage
    When I select a case "should" be answered by my team
    And I select to reply by "email"
    And I upload my Primary Draft document
    And I confirm the primary draft document
    And I select that the QA Process has been completed offline
    And I record who on my Team completed the offline QA Approval
    Then I am returned to the dashboard
    And the case should be moved to the "Private Office Approval" stage
    And the case should now be owned be the correct Private Office team
    And the read-only Case Details accordion should contain all case information entered during the "Initial Draft" stage
    And the selected document should be tagged as the primary draft

  @DCUWorkflow @DCURegression
  Scenario: User selects offline QA for a DTEN case
    And I get a "DTEN" case at the "INITIAL DRAFT" stage
    When I select a case "should" be answered by my team
    And I upload my Primary Draft document
    And I confirm the primary draft document
    And I select that the QA Process has been completed offline
    And I record who on my Team completed the offline QA Approval
    Then I am returned to the dashboard
    And the case should be moved to the "Private Office Approval" stage
    And the case should now be owned be the correct Private Office team
    And the read-only Case Details accordion should contain all case information entered during the "Initial Draft" stage
    And the selected document should be tagged as the primary draft

  @DCUWorkflow @DCURegression
  Scenario: User selects offline QA for a TRO case
    And I get a "TRO" case at the "INITIAL DRAFT" stage
    When I select a case "should" be answered by my team
    And I select to reply by "email"
    And I add a "DRAFT" type document to the case
    And I select the "draft" document as the primary draft
    And I confirm the primary draft document
    And I select that the QA Process has been completed offline
    And I record who on my Team completed the offline QA Approval
    Then I am returned to the dashboard
    And the case should be moved to the "Dispatch" stage
    And the case should still be owned by the drafting team
    And the read-only Case Details accordion should contain all case information entered during the "Initial Draft" stage
    And the selected document should be tagged as the primary draft

  @DCUWorkflow @DCURegression
  Scenario Outline: Case is returned to Markup stage when rejected at Initial Draft stage
    And I get a "<caseType>" case at the "Initial Draft" stage
    And I reject the case at the Initial Draft stage
    Then the case should be moved to the "Markup" stage
    And the summary should display the owning team as "<markupTeam>"
    And the read-only Case Details accordion should contain all case information entered during the "Initial Draft" stage
    Examples:
      | caseType | markupTeam                                       |
      | MIN      | Direct Communications Unit Central Drafting Team |
      | TRO      | Direct Communications Unit Central Drafting Team |
      | DTEN     | Transfers & No10 Team                            |

  @DCURegression
  Scenario: As a Initial Draft user, I want to see a symbol that tells me a case is a Home Secretary Reply case, so I can prioritise it
    And I get a "MIN" case at the "Markup" stage
    When I complete Markup with "Home Secretary" selected as the Private Office team
    Then the "Animals in Science Regulation Unit" workstack should display a HS symbol next to the case reference

  @Validation
  Scenario Outline: User tests the validation at the Initial Draft stage
    And I get a "<caseType>" case at the "INITIAL DRAFT" stage
    And I trigger the "<errorMessage>" error message at the "Initial Draft" stage
    Then the "<errorMessage>" error message is displayed at the "Initial Draft" stage
    Examples:
    | caseType  | errorMessage                                |
    | MIN       | CAN THIS BE ANSWERED BY YOUR TEAM REQUIRED  |
    | TRO       | RESPONSE CHANNEL REQUIRED                   |
    | DTEN      | PRIMARY DRAFT DOCUMENT REQUIRED             |
    | MIN       | QA THIS OFFLINE REQUIRED                    |
    | TRO       | WHO HAS DONE QA OFFLINE REQUIRED            |
