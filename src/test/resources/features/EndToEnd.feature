Feature: HOCS is able to move cases through the entire flow

  Background:
    Given I am user "DANNY"

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Data Input stage
    When The current user creates a single case "<caseType>"
    Then The case should be moved to the "DATA INPUT" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |



  @Workflow @SmokeTests
  Scenario Outline: Case moves to Markup stage
    When The current user creates a single case "<caseType>"
    And The Data Input Stage is completed for "<caseType>" caseType
    Then The case should be moved to the "MARKUP" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Initial Draft stage
    When The current user creates a single case "<caseType>"
    And The Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    Then The case should be moved to the "INITIAL DRAFT" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to QA Response stage
    When The current user creates a single case "<caseType>"
    And The Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    Then The case should be moved to the "QA RESPONSE" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Private Office stage
    When The current user creates a single case "<caseType>"
    And The Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    Then The case should be moved to the "PRIVATE OFFICE" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU N10 |

  @Workflow @SmokeTests @DCUMIN
  Scenario Outline: Case moves to Minister Sign Off stage
    When The current user creates a single case "<caseType>"
    And The Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    Then The case should be moved to the "MINISTERIAL SIGN OFF" stage
    Examples:
      | caseType|
      | DCU MIN |

  @Workflow @SmokeTests @DCUMIN
  Scenario Outline: Case moves to Dispatch stage
    When The current user creates a single case "<caseType>"
    And The Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    Then The case should be moved to the "DISPATCH" stage
    Examples:
      | caseType|
      | DCU MIN |
   #   | DCU TRO |
   #   | DCU N10 |

  #Remember to do Copy to #10

  @EndToEnd @DCUMIN @Critical @SmokeTests
  Scenario: End to end flow with DCU MIN CaseType
    When The current user creates a single case "DCU MIN"
    And The Data Input Stage is completed for "DCU MIN" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    And I complete the dispatch stage
    Then The case should no longer be visible in the teamqueue

  @EndToEnd @Critical @SmokeTests
  Scenario: End to end flow with DCU N10 CaseType
    When The current user creates a single case "DCU N10"
    And The Data Input Stage is completed for "DCU N10" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the dispatch stage
    Then The case should no longer be visible in the teamqueue

  @EndToEnd @Critical @SmokeTests
  Scenario: End to end flow with DCU TRO CaseType
    When The current user creates a single case "DCU TRO"
    And The Data Input Stage is completed for "DCU TRO" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the dispatch stage
    Then The case should no longer be visible in the teamqueue






