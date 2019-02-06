Feature: HOCS is able to move cases through the entire flow

  Background:
    Given I am user "DANNY"

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Data Input stage
    When the current user creates a single case "<caseType>"
    Then the case should be moved to the "DATA INPUT" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |



  @Workflow @SmokeTests
  Scenario Outline: Case moves to Markup stage
    When the current user creates a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    Then the case should be moved to the "MARKUP" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Initial Draft stage
    When the current user creates a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    Then the case should be moved to the "INITIAL DRAFT" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to QA Response stage
    When the current user creates a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    Then the case should be moved to the "QA RESPONSE" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Private Office stage
    When the current user creates a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    Then the case should be moved to the "PRIVATE OFFICE" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU N10 |

  @Workflow @SmokeTests @DCUMIN
  Scenario Outline: Case moves to Minister Sign Off stage
    When the current user creates a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    Then the case should be moved to the "MINISTERIAL SIGN OFF" stage
    Examples:
      | caseType|
      | DCU MIN |

  @Workflow @SmokeTests @DCUMIN
  Scenario Outline: Case moves to Dispatch stage
    When the current user creates a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    Then the case should be moved to the "DISPATCH" stage
    Examples:
      | caseType|
      | DCU MIN |
   #   | DCU TRO |
   #   | DCU N10 |

  #Remember to do Copy to #10

  @EndToEnd @DCUMIN @Critical @SmokeTests
  Scenario: End to end flow with DCU MIN CaseType
    When the current user creates a single case "DCU MIN"
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
    When the current user creates a single case "DCU N10"
    And the Data Input Stage is completed for "DCU N10" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the dispatch stage
    Then the case should no longer be visible in the teamqueue

  @EndToEnd @Critical @SmokeTests
  Scenario: End to end flow with DCU TRO CaseType
    When the current user creates a single case "DCU TRO"
    And the Data Input Stage is completed for "DCU TRO" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the dispatch stage
    Then the case should no longer be visible in the teamqueue






