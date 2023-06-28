@DCUEndToEnd  @DCU
Feature: DCU End To End

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow
  Scenario Outline: New case moves to Data Input stage
    When I get a new "<caseType>" case
    Then the case should be at the "Data Input" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @DCUWorkflow
  Scenario Outline: New case moves to Markup stage
    When I get a "<caseType>" case at the "Data Input" stage
    And I complete the "Data Input" stage
    Then the case should be moved to the "Markup" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @DCUWorkflow
  Scenario Outline: New case moves to Initial Draft stage
    When I get a "<caseType>" case at the "Markup" stage
    And I complete the "Markup" stage
    Then the case should be moved to the "Initial Draft" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @DCUWorkflow
  Scenario Outline: New case moves to QA Response stage
    When I get a "<caseType>" case at the "Initial Draft" stage
    And I complete the "Initial Draft" stage
    Then the case should be moved to the "QA Response" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @DCUWorkflow
  Scenario Outline: MIN or DTEN case moves to Private Office stage
    When I get a "<caseType>" case at the "QA response" stage
    And I complete the "QA response" stage
    Then the case should be moved to the "Private Office Approval" stage
    Examples:
      | caseType |
      | MIN      |
      | DTEN     |

  @DCUWorkflow
  Scenario: MIN case moves to Ministerial Sign Off stage
    When I get a "<caseType>" case at the "Private Office Approval" stage
    And I complete the "Private Office Approval" stage
    Then the case should be moved to the "Ministerial Sign Off" stage


  @DCUWorkflow
  Scenario: MIN case moves to Dispatch stage
    When I get a "MIN" case at the "Ministerial Sign Off" stage
    And I complete the "Ministerial Sign Off" stage
    Then the case should be moved to the "Dispatch" stage

  @DCUWorkflow
  Scenario: DTEN case moves to Dispatch stage
    When I get a "DTEN" case at the "Private Office Approval" stage
    And I complete the "Private Office Approval" stage
    Then the case should be moved to the "Dispatch" stage

  @DCUWorkflow
  Scenario: TRO case moves to Dispatch stage
    When I get a "TRO" case at the "QA response" stage
    And I complete the "QA response" stage
    Then the case should be moved to the "Dispatch" stage

  @DCUWorkflow @DCURegression @SmokeTests @E2ETests
  Scenario: End to end flow with DCU MIN CaseType
    When I get a new "MIN" case
    And I complete the "Data Input" stage
    And I complete the "Markup" stage
    And I complete the "Initial Draft" stage
    And I complete the "QA response" stage
    And I complete the "Private Office Approval" stage
    And I complete the "Ministerial Sign Off" stage
    And I complete the "Dispatch" stage
    Then the case should be closed

  #HOCS-6617
  @E2ETests
  Scenario: Robust End to end flow with DCU MIN CaseType
    When I get a new "MIN" case
    And I choose not to wipe the record data until the end
    And I select "Yes" for Home Secretary interest and complete the data input stage
    And I complete the "Markup" stage
    And I complete the "Initial Draft" stage
    And I complete the "QA response" stage
    And I complete the "Private Office Approval" stage
    And I complete the "Ministerial Sign Off" stage
    And I complete the "Dispatch" stage
    And the case should be closed
    And all case data should be visible in the read-only Case Details accordion
    And the Home Secretary interest decision should match the one displayed in the summary tab
    And the summary should display the correct Private Office team
    And the summary should display "Animal alternatives (3Rs)" for "Primary topic"
    Then I wipe the record data


  @DCUWorkflow @DCURegression @SmokeTests @E2ETests
  Scenario: End to end flow with DCU N10 CaseType
    When I get a new "DTEN" case
    And I complete the "Data Input" stage
    And I complete the "Markup" stage
    And I complete the "Initial Draft" stage
    And I complete the "QA response" stage
    And I complete the "Private Office Approval" stage
    And I complete the "Dispatch" stage
    Then the case should be closed

  #HOCS-6617
  @E2ETests
  Scenario: Robust End to end flow with DCU N10 CaseType
    When I get a new "DTEN" case
    And I choose not to wipe the record data until the end
    And I select "Yes" for Home Secretary interest and complete the data input stage
    And I complete the "Markup" stage
    And I complete the "Initial Draft" stage
    And I complete the "QA response" stage
    And I complete the "Private Office Approval" stage
    And I complete the "Dispatch" stage
    Then the case should be closed
    And all case data should be visible in the read-only Case Details accordion
    And the summary should display the correct Private Office team
    And the summary should display "Animal alternatives (3Rs)" for "Primary topic"
    And I wipe the record data

  @DCUWorkflow @DCURegression @SmokeTests @E2ETests
  Scenario: End to end flow with DCU TRO CaseType
    When I get a new "TRO" case
    And I complete the "Data Input" stage
    And I complete the "Markup" stage
    And I complete the "Initial Draft" stage
    And I complete the "QA response" stage
    And I complete the "Dispatch" stage
    Then the case should be closed

  #HOCS-6617
  @E2ETests
  Scenario: Robust End to end flow with DCU TRO CaseType
    When I get a new "TRO" case
    And I choose not to wipe the record data until the end
    And I complete the "Data Input" stage
    And I complete the "Markup" stage
    And I complete the "Initial Draft" stage
    And I complete the "QA response" stage
    And I complete the "Dispatch" stage
    Then the case should be closed
    And all case data should be visible in the read-only Case Details accordion
    And the summary should display "Animal alternatives (3Rs)" for "Primary topic"
    And I wipe the record data

  @DCUWorkflow
  Scenario Outline: User creates a case of each type and progresses the case through the workflow
    And I create a "<caseType>" case and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    Examples:
      | caseType | stage                   |
      | MIN      | Data Input              |
      | TRO      | Data Input              |
      | DTEN     | Data Input              |
      | MIN      | Markup                  |
      | TRO      | Markup                  |
      | DTEN     | Markup                  |
      | MIN      | Initial Draft           |
      | TRO      | Initial Draft           |
      | DTEN     | Initial Draft           |
      | MIN      | QA Response             |
      | TRO      | QA Response             |
      | DTEN     | QA Response             |
      | MIN      | Private Office Approval |
      | DTEN     | Private Office Approval |
      | MIN      | Ministerial Sign off    |
      | MIN      | Dispatch                |
      | TRO      | Dispatch                |
      | DTEN     | Dispatch                |
