Feature: HOCS is able to move cases through the entire flow

  Background:
    Given I log in as the designated user

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Data Input stage
    When I create a single case "<caseType>"
    Then the "<caseType>" case should be moved to the "DATA INPUT" stage
    Examples:
      | caseType|
      | DCU MIN  |
      | DCU TRO  |
      | DCU N10 |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Markup stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    Then the "<caseType>" case should be moved to the "MARKUP" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Initial Draft stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    And I complete the markup stage
    Then the "<caseType>" case should be moved to the "INITIAL DRAFT" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to QA Response stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    And I complete the markup stage
    And I complete the Initial Draft stage for "<caseType>" case type
    Then the "<caseType>" case should be moved to the "QA RESPONSE" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Private Office stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    And I complete the markup stage
    And I complete the Initial Draft stage for "<caseType>" case type
    And I complete the QA response stage
    Then the "<caseType>" case should be moved to the "PRIVATE OFFICE APPROVAL" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU N10 |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Minister Sign Off stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage for "<caseType>"
    Then the "<caseType>" case should be moved to the "MINISTERIAL SIGN OFF" stage
    Examples:
      | caseType|
      | DCU MIN |

  @EndToEnd @Workflow @SmokeTests
  Scenario Outline: New case moves to Dispatch stage
    When I create a single case "<caseType>"
    And I complete the Data Input Stage for "<caseType>" case type
    And I complete the markup stage
    And I complete the Initial Draft stage for "<caseType>" case type
    And I complete the QA response stage
    And I complete the Private Office stage for "<caseType>"
    And I complete the minister sign off stage for "<caseType>"
    Then the "<caseType>" case should be moved to the "DISPATCH" stage
    Examples:
      | caseType|
      | DCU MIN |
      | DCU TRO |
      | DCU N10 |

  @EndToEnd @Workflow @SmokeTests
  Scenario: Dispatch a case with Copy to Number Ten selected
    Given I create a single case "DCU MIN"
    And I complete the Data Input stage and send a copy to Number Ten
    And I complete the markup stage
    And I complete the Initial Draft stage for "DCU MIN" case type
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    And I complete the dispatch stage
    Then the "DCU MIN" case should be moved to the "COPY TO NUMBER 10" stage

  @EndToEnd @EndToEnd @SmokeTests
  Scenario: End to end flow with DCU MIN CaseType
    When I create a single case "DCU MIN"
    And I complete the Data Input Stage for "DCU MIN" case type
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the minister sign off stage
    And I complete the dispatch stage
    Then the case is completed

  @EndToEnd @SmokeTests
  Scenario: End to end flow with DCU N10 CaseType
    When I create a single case "DCU N10"
    And I complete the Data Input Stage for "DCU N10" case type
    And I complete the markup stage
    And I complete the Initial Draft stage
    And I complete the QA response stage
    And I complete the Private Office stage
    And I complete the dispatch stage
    Then the case is completed

  @EndToEnd @SmokeTests
  Scenario: End to end flow with DCU TRO CaseType
    When I create a single case "DCU TRO"
    And I complete the Data Input Stage for "DCU TRO" case type
    And I complete the markup stage
    And I complete the Initial Draft stage for "DCU TRO" case type
    And I complete the QA response stage
    And I complete the dispatch stage
    Then the case is completed






