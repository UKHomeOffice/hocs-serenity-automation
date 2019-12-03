Feature: If the response is rejected the case is returned to certain stages in the flow

  Background:
    Given I log in as the designated user

  @RejectFlow @QAResponse @Critical @Workflow @SmokeTests
  Scenario Outline: "<caseType>" Case returned to Markup stage when rejected by Initial Draft Team
    And I get a "<caseType>" case at "Initial Draft" stage
    And I reject the case at the "Initial Draft" stage
    Then the "<caseType>" case should be moved to the "Markup" stage
    Examples:
      | caseType |
      | DCU MIN  |
      | DCU TRO  |
      | DCU N10  |

  @RejectFlow @QAResponse @Critical @Workflow @SmokeTests
  Scenario Outline: "<caseType>" Case returned to Initial Draft stage when rejected by QA Response Team
    And I get a "<caseType>" case at "QA Response" stage
    And I reject the case at the "QA Response" stage
    Then the "<caseType>" case should be moved to the "Initial Draft" stage
    Examples:
      | caseType |
      | DCU MIN  |
      | DCU TRO  |
      | DCU N10  |

  @RejectFlow @QAResponse @Critical @Workflow @SmokeTests
  Scenario Outline: "<caseType>" Case returned to Initial Draft stage when rejected by QA Response Team
    And I get a "<caseType>" case at "QA Response" stage
    And I reject the case at the "QA Response" stage
    Then the "<caseType>" case should be moved to the "Initial Draft" stage
    Examples:
      | caseType |
      | DCU MIN  |
      | DCU TRO  |
      | DCU N10  |

  @RejectFlow @PrivateOffice @Critical @Workflow @SmokeTests
  Scenario Outline: "<caseType>" Case returned to Initial Draft stage when rejected by Private Office Approval Team
    And I get a "<caseType>" case at "Private Office Approval" stage
    And I reject the case at the "Private Office Approval" stage
    Then the "<caseType>" case should be moved to the "Initial Draft" stage
    Examples:
      | caseType |
      | DCU MIN  |
      | DCU N10  |

  @RejectFlow @MinisterSignOff @Critical @Workflow @SmokeTests
  Scenario Outline: "<caseType>" Case returned to Initial Draft stage when rejected by Private Office Approval Team
    And I get a "<caseType>" case at "Ministerial Sign Off" stage
    And I reject the case at the "Ministerial Sign Off" stage
    Then the "<caseType>" case should be moved to the "Initial Draft" stage
    Examples:
      | caseType |
      | DCU MIN  |

  @RejectFlow @Dispatch @Critical @Workflow @SmokeTests
  Scenario Outline: "<caseType>" Case returned to Initial Draft stage when rejected by Dispatch Team
    And I get a "<caseType>" case at "Dispatch" stage
    And I reject the case at the "Dispatch" stage
    Then the "<caseType>" case should be moved to the "Private Office Approval" stage
    Examples:
      | caseType |
      | DCU MIN  |
      | DCU N10  |
