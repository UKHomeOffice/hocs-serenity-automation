Feature: HOCS is able to move cases through the entire flow

  Background:
    Given I log in as the designated user

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Data Input stage
    When I create a single case "<caseType>"
    Then the "<caseType>" case should be moved to the "DATA INPUT" stage
    Examples:
      | caseType|
      | DCU MIN  |
      | DCU TRO  |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Markup stage
    When I create a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    Then the "<caseType>" case should be moved to the "MARKUP" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Initial Draft stage
    When I create a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    Then the "<caseType>" case should be moved to the "INITIAL DRAFT" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to QA Response stage
    When I create a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage for "<caseType>"
    Then the "<caseType>" case should be moved to the "QA RESPONSE" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Private Office stage
    When I create a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage for "<caseType>"
    And I complete the QA response stage
    Then the "<caseType>" case should be moved to the "PRIVATE OFFICE APPROVAL" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Minister Sign Off stage
    When I create a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage for "<caseType>"
    Then the "<caseType>" case should be moved to the "MINISTERIAL SIGN OFF" stage
    Examples:
      | caseType|
      | DCU MIN |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Dispatch stage
    When I create a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage for "<caseType>"
    And I complete the QA response stage
    And I complete the Private Office stage for "<caseType>"
    And I complete the minister sign off stage for "<caseType>"
    Then the "<caseType>" case should be moved to the "DISPATCH" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @EndToEnd @Critical @SmokeTests
  Scenario: End to end flow with DCU MIN CaseType
    When I create a single case "DCU MIN"
    And the Data Input Stage is completed for "DCU MIN" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    And I complete the dispatch stage
    Then the case should no longer be visible in the teamqueue

  @EndToEnd @Critical @SmokeTests
  Scenario: End to end flow with DCU N10 CaseType
    When I create a single case "DCU N10"
    And the Data Input Stage is completed for "DCU N10" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the dispatch stage
    Then the case should no longer be visible in the teamqueue

  @EndToEnd @Critical @SmokeTests
  Scenario: End to end flow with DCU TRO CaseType
    When I create a single case "DCU TRO"
    And the Data Input Stage is completed for "DCU TRO" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage for "DCU TRO"
    And I complete the QA response stage
    And I complete the dispatch stage
    Then the case should no longer be visible in the "DCU TRO" teamqueue

  @Workflow @SmokeTests @Dispatch
  Scenario: Dispatch a case with Copy to Number Ten selected
    Given I create a single case "DCU MIN"
    And I complete the Data Input stage and send a copy to Number Ten
    And I complete the markup stage
    And I complete the Initial Draft stage for "DCU MIN"
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    And I complete the dispatch stage
    Then the "DCU MIN" case should be moved to the "COPY TO NUMBER 10" stage






