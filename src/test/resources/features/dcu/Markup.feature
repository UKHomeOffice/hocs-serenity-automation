@Markup @DCU
Feature: Markup

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: As a Markup user, I want to be able to send a MIN or DTEN case to the correct teams for a Policy response, so it can be progressed
    And I get a "<caseType>" case at the "Markup" stage
    When I select an initial decision of "Policy Response"
    And I select a Primary topic of "Animal alternatives (3Rs)"
    And I override the Initial Draft team to "Cyber Policy Unit"
    And I override the Private Office team to "Director General UKVI"
    And I confirm the Initial Draft and Private Office team
    Then the case should be moved to the "Initial Draft" stage
    And the summary should display the owning team as "Cyber Policy Unit"
    And the summary should display "Director General UKVI" for "Override Private Office Team"
    And the read-only Case Details accordion should contain all case information entered during the "Markup" stage
    Examples:
      | caseType |
      | MIN      |
      | DTEN     |

  @DCUWorkflow @DCURegression
  Scenario: As a Markup user, I want to be able to send a TRO case to the correct teams for a Policy response, so it can be progressed
    And I get a "MIN" case at the "Markup" stage
    When I select an initial decision of "Policy Response"
    And I select a Primary topic of "Animal alternatives (3Rs)"
    And I override the Initial Draft team to "Cyber Policy Unit"
    And I confirm the Initial Draft team
    Then the case should be moved to the "Initial Draft" stage
    And the summary should display the owning team as "Direct Communications Unit Central Drafting Team"
    And the read-only Case Details accordion should contain all case information entered during the "Markup" stage

  @DCUWorkflow @DCURegression
  Scenario Outline: As a Markup user, I want to be able to send a MIN or DTEN case to the correct teams for a FAQ response, so it can be progressed
    And I get a "<caseType>" case at the "Markup" stage
    When I select an initial decision of "FAQ Response"
    And I select a Primary topic of "Animal alternatives (3Rs)"
    And I override the Private Office team to "Director General UKVI"
    And I confirm the Private Office team
    Then the case should be moved to the "Initial Draft" stage
    And the summary should display the owning team as "Direct Communications Unit Central Drafting Team"
    And the read-only Case Details accordion should contain all case information entered during the "Markup" stage
    Examples:
      | caseType |
      | MIN      |
      | DTEN     |

  @DCUWorkflow @DCURegression
  Scenario: As a Markup user, I want to be able to move a TRO case to Initial Draft stage for a FAQ response, so it can be progressed
    And I get a "TRO" case at the "Markup" stage
    When I select an initial decision of "FAQ Response"
    And I select a Primary topic of "Animal alternatives (3Rs)"
    Then the case should be moved to the "Initial Draft" stage
    And the summary should display the owning team as "Direct Communications Unit Central Drafting Team"
    And the read-only Case Details accordion should contain all case information entered during the "Markup" stage

  @DCUWorkflow @DCURegression
  Scenario Outline: User selects Refer to OGD
    When I get a "<caseType>" case at the "Markup" stage
    And I select an initial decision of "Refer To OGD"
    And I submit a transfer destination and transfer reason
    Then the case should be moved to the "Transfer Confirmation" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @DCUWorkflow @DCURegression
  Scenario Outline: User selects no response needed
    When I get a "<caseType>" case at the "Markup" stage
    And I select an initial decision of "No Response Needed"
    And I submit a reason that no response is needed
    Then the case should be moved to the "No Response Needed Confirmation" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @DCUWorkflow @DCURegression
  Scenario Outline: Case is returned to Data Input stage when rejected at Markup stage
    When I get a "<caseType>" case at the "Markup" stage
    And I reject the case at the Markup stage
    Then the case should be moved to the "Data Input" stage
    And the summary should display the owning team as "<dataInputTeam>"
    And a note should be visible in the timeline showing the reason for rejection
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage
    Examples:
      | caseType | dataInputTeam                |
      | MIN      | Performance and Process Team |
      | TRO      | Performance and Process Team |
      | DTEN     | Transfers & No10 Team        |

  @Navigation
  Scenario Outline: Central Drafting Team user selects an initial decision of Policy Response or FAQ
    When I get a "DTEN" case at the "Markup" stage
    And I select an initial decision of "<radioButton>"
    And I click the Add a topic link
    Then a mandatory Topic free text field is displayed
    Examples:
      | radioButton     |
      | Policy Response |
      | FAQ Response    |

  @Navigation
  Scenario: User selects an initial decision of Refer to OGD
    When I get a "DTEN" case at the "Markup" stage
    And I select an initial decision of "Refer To OGD"
    Then the Other Government Department name free text field is displayed

  @Navigation
  Scenario: User selects an initial decision of No Response Needed
    When I get a "DTEN" case at the "Markup" stage
    And I select an initial decision of "No Response Needed"
    Then the No Response Needed casenote field is displayed

  @Navigation
  Scenario: User selects an initial decision of Reject to Data Input
    When I get a "DTEN" case at the "Markup" stage
    And I select an initial decision of "Reject To Data Input"
    Then the reason for rejection casenote field is displayed

  Scenario: User can select a topic for a Policy response
    When I get a "DTEN" case at the "Markup" stage
    And I select an initial decision of "Policy Response"
    And I add the topic "Animal alternatives (3Rs)"
    Then the topic should be added to the case

  Scenario: User can select a topic for a FAQ response
    When I get a "DTEN" case at the "Markup" stage
    And I select an initial decision of "FAQ Response"
    And I add the topic "Animal alternatives (3Rs)"
    Then the topic should be added to the case

  @Validation
  Scenario: User does not enter other government department in free text field
    When I get a "DTEN" case at the "Markup" stage
    And I select an initial decision of "Refer To OGD"
    But I do not enter a "Other Government Department"
    Then an error message is displayed

  @Validation
  Scenario: User does not enter reasons for no reply needed
    When I get a "DTEN" case at the "Markup" stage
    And I select an initial decision of "No Response Needed"
    But I do not enter a "reason for no response needed"
    Then an error message is displayed

  @Validation
  Scenario: User does not enter reason for rejecting case to Data Input
    When I get a "DTEN" case at the "Markup" stage
    And I select an initial decision of "Reject To Data Input"
    But I do not enter a "reason for rejecting to data input"
    Then an error message is displayed

  @Validation
  Scenario Outline: User tests the validation at the Markup stage
    When I get a "<caseType>" case at the "Markup" stage
    And I trigger the "<errorMessage>" error message at the "Markup" stage
    Then the "<errorMessage>" error message is displayed at the "Markup" stage
    Examples:
      | caseType | errorMessage                                  |
      | MIN      | Type of Response Required                     |
      | TRO      | Primary Topic Required                        |
      | DTEN     | Response Approval Required                    |
      | MIN      | Why is no Response Needed Required            |
      | TRO      | Where Should the Case be Transferred Required |
      | DTEN     | Reason for Transfer Required                  |