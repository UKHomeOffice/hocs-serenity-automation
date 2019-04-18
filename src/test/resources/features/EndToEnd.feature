Feature: HOCS is able to move cases through the entire flow

  Background:
    Given I am user "DANNY"

  @Workflow @SmokeTests @DroneTest
  Scenario Outline: Case moves to Data Input stage
    When I create a single case "<caseType>"
    Then the case should be moved to the "DATA INPUT" stage
    Examples:
      | caseType|
      | DCU MIN |
#      | DCU TRO |
#      | DCU N10 |

  @Workflow @SmokeTests @CaseBuilder
  Scenario Outline: Case moves to Markup stage
    When I create a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    Then the case should be moved to the "MARKUP" stage
    Examples:
      | caseType|
      | DCU MIN |
#      | DCU TRO |
#      | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Initial Draft stage
    When I create a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    Then the case should be moved to the "INITIAL DRAFT" stage
    Examples:
      | caseType|
      | DCU MIN |
 #     | DCU TRO |
  #    | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to QA Response stage
    When I create a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    Then the case should be moved to the "QA RESPONSE" stage
    Examples:
      | caseType|
      | DCU MIN |
  #    | DCU TRO |
  #    | DCU N10 |

  @Workflow @SmokeTests
  Scenario Outline: Case moves to Private Office stage
    When I create a single case "<caseType>"
    And the Data Input Stage is completed for "<caseType>" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    Then the case should be moved to the "PRIVATE OFFICE APPROVAL" stage
    Examples:
      | caseType|
      | DCU MIN |
 #     | DCU N10 |

  @Workflow @SmokeTests @DCUMIN
  Scenario Outline: Case moves to Minister Sign Off stage
    When I create a single case "<caseType>"
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
    When I create a single case "<caseType>"
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

  @EndToEnd @DCUMIN @Critical
  Scenario: End to End flow and send a copy to N10
    When I create a "DCU MIN" case with Copy to N10
    When I am at the "DISPATCH" stage
    And dispatch the case
#    And I complete the markup stage
#    And I complete the Initial Draft stage
#    And I complete the QA response stage
#    And I complete the Private Office stage
#    And I complete the minister sign off stage
#    And I complete the dispatch stage
    Then the case should be sent to the Copy to N10 stage in the Transfers and Number Ten team

  @EndToEnd @DCUMIN @Critical @SmokeTests
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

  @EndToEnd @Critical
  Scenario: End to end flow with DCU N10 CaseType
    When I create a single case "DCU N10"
    And the Data Input Stage is completed for "DCU N10" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the dispatch stage
    Then the case should no longer be visible in the teamqueue

  @EndToEnd @Critical
  Scenario: End to end flow with DCU TRO CaseType
    When I create a single case "DCU TRO"
    And the Data Input Stage is completed for "DCU TRO" caseType
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the dispatch stage
    Then the case should no longer be visible in the teamqueue






