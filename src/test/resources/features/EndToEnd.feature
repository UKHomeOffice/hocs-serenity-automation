@EndToEnd
Feature: End To End

  Background:
    Given I am user "AUTOMATION_USER"

  @Workflow
  Scenario Outline: New case moves to Data Input stage
    When I create a single "<caseType>" case and return to the dashboard
    Then the case should be moved to the "DATA INPUT" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @Workflow
  Scenario Outline: New case moves to Markup stage
    When I create a single "<caseType>" case and return to the dashboard
    And I complete the Data Input Stage
    Then the case should be moved to the "MARKUP" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @Workflow
  Scenario Outline: New case moves to Initial Draft stage
    When I create a single "<caseType>" case and return to the dashboard
    And I complete the Data Input Stage
    And I complete the Markup stage
    Then the case should be moved to the "INITIAL DRAFT" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @Workflow
  Scenario Outline: New case moves to QA Response stage
    When I create a single "<caseType>" case and return to the dashboard
    And I complete the Data Input Stage
    And I complete the Markup stage
    And I complete the Initial Draft stage
    Then the case should be moved to the "QA RESPONSE" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @Workflow
  Scenario Outline: New case moves to Private Office stage
    When I create a single "<caseType>" case and return to the dashboard
    And I complete the Data Input Stage
    And I complete the Markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    Then the case should be moved to the "PRIVATE OFFICE APPROVAL" stage
    Examples:
      | caseType |
      | MIN      |
      | DTEN     |

  @Workflow
  Scenario Outline: New case moves to Ministerial Sign Off stage
    When I create a single "<caseType>" case and return to the dashboard
    And I complete the Data Input Stage
    And I complete the Markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    Then the case should be moved to the "MINISTERIAL SIGN OFF" stage
    Examples:
      | caseType |
      | MIN      |

  @Workflow
  Scenario Outline: New case moves to Dispatch stage
    When I create a single "<caseType>" case and return to the dashboard
    And I complete the Data Input Stage
    And I complete the Markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the Ministerial Sign Off stage
    Then the case should be moved to the "DISPATCH" stage
    Examples:
      | caseType |
      | MIN      |
      | TRO      |
      | DTEN     |

  @Workflow @SmokeTests
  Scenario: Dispatch a case with Copy to Number Ten selected
    Given I create a single "MIN" case and return to the dashboard
    And I complete the Data Input stage and send a copy to Number Ten
    And I complete the Markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the Ministerial Sign Off stage
    And I complete the dispatch stage
    Then the case should be moved to the "COPY TO NUMBER 10" stage

  @Workflow @SmokeTests
  Scenario: End to end flow with DCU MIN CaseType
    When I create a single "MIN" case and return to the dashboard
    And I complete the Data Input Stage
    And I complete the Markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the Ministerial Sign Off stage
    And I complete the dispatch stage
    Then the case is completed

  @Workflow @SmokeTests
  Scenario: End to end flow with DCU N10 CaseType
    When I create a single "DTEN" case and return to the dashboard
    And I complete the Data Input Stage
    And I complete the Markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the dispatch stage
    Then the case is completed

  @Workflow @SmokeTests
  Scenario: End to end flow with DCU TRO CaseType
    When I create a single "TRO" case and return to the dashboard
    And I complete the Data Input Stage
    And I complete the Markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the dispatch stage
    Then the case is completed

  @Workflow
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
