Feature: HOCS is able to move cases through the entire flow

  Background:
    Given I am user "DANNY"

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Data Input stage
    When I create a single case "<caseType>"
    Then The case is moved to the "DATA INPUT" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |



  @Workflow @SmokeTests
  Scenario Outline: Case moves to Markup stage
    When I create a single case "<caseType>"
    And I complete the Data Input stage
    Then The case is moved to the "MARKUP" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Initial Draft stage
    When I create a single case "<caseType>"
    And I complete the Data Input stage
    And I complete the markup stage
    Then The case is moved to the "INITIAL DRAFT" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to QA Response stage
    When I create a single case "<caseType>"
    And I complete the Data Input stage
    And I complete the markup stage
    And Initial draft stage "<string>"
    Then The case is moved to the "QA RESPONSE" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Private Office stage
    When I create a single case "<caseType>"
    And I complete the Data Input stage
    And I complete the markup stage
    And Initial draft stage "<string>"
    And I complete the QA response stage
    Then The case is moved to the "PRIVATE OFFICE" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Minister Sign Off stage
    When I create a single case "<caseType>"
    And I complete the Data Input stage
    And I complete the markup stage
    And Initial draft stage "<string>"
    And I complete the QA response stage
    And I complete the Private Office stage
    Then The case is moved to the "MINISTER SIGN OFF" stage
    Examples:
      | caseType|
      | DCU MIN |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Dispatch stage
    When I create a single case "<caseType>"
    And I complete the Data Input stage
    And I complete the markup stage
    And Initial draft stage "<string>"
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    Then The case is moved to the "DISPATCH" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

#  @Workflow @CopyToN10
#  Scenario: Case moves to Copy To Number Ten stage
#    When I create a single case "caseType"
#    Then The case is moved to the "DATA INPUT" stage



  @EndToEnd @DCUMIN @Critical @SmokeTests
  Scenario: End to end flow with DCU MIN CaseType
    When I create a single case "DCU MIN"
    And I complete the Data Input stage
    And I complete the markup stage
    And Initial draft stage "DCU MIN"
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    And I complete the dispatch stage
    Then The case should no longer be visible in the teamqueue

  @EndToEnd @Critical @SmokeTests
  Scenario: End to end flow with DCU N10 CaseType
    When I create a single case "DCU N10"
    And I complete the Data Input stage
    And I complete the markup stage
    And Initial draft stage "DCU N10"
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the dispatch stage
    Then The case should no longer be visible in the teamqueue

  @EndToEnd @Critical @SmokeTests
  Scenario: End to end flow with DCU TRO CaseType
    When I create a single case "DCU TRO"
    And I complete the Data Input stage
    And I complete the markup stage
    And Initial draft stage "DCU TRO"
    And I complete the QA response stage
    And I complete the dispatch stage
    Then The case should no longer be visible in the teamqueue


