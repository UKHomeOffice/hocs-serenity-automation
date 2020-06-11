@MPAMEndToEnd
Feature: EndToEnd

  Background:
    Given I log in to DECS

  @MPAMWorkflow
  Scenario Outline: User moves a case with a specific Business Area and Reference Type to Triage stage
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage  |
      | UKVI         | M:Ref   | Triage |
      | BF           | M:Ref   | Triage |
      | IE           | M:Ref   | Triage |
      | EUSS         | M:Ref   | Triage |
      | HMPO         | M:Ref   | Triage |
      | Windrush     | M:Ref   | Triage |
      | UKVI         | B:Ref   | Triage |
      | BF           | B:Ref   | Triage |
      | IE           | B:Ref   | Triage |
      | EUSS         | B:Ref   | Triage |
      | HMPO         | B:Ref   | Triage |
      | Windrush     | B:Ref   | Triage |

  @MPAMWorkflow
  Scenario Outline: User puts a case with specific Business Area and Reference Type on hold at Triage stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Triage case to "On Hold"
    Then the case should be moved to the "<stage> (On Hold)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage  |
      | UKVI         | M:Ref   | Triage |
      | BF           | M:Ref   | Triage |
      | IE           | M:Ref   | Triage |
      | EUSS         | M:Ref   | Triage |
      | HMPO         | M:Ref   | Triage |
      | Windrush     | M:Ref   | Triage |
      | UKVI         | B:Ref   | Triage |
      | BF           | B:Ref   | Triage |
      | IE           | B:Ref   | Triage |
      | EUSS         | B:Ref   | Triage |
      | HMPO         | B:Ref   | Triage |
      | Windrush     | B:Ref   | Triage |

  @MPAMWorkflow
  Scenario Outline: User takes a case with specific Business Area and Reference Type off hold at Triage stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Triage case to "On Hold"
    And I load and claim the current case
    When I take the Triage (On Hold) case off hold
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage  |
      | UKVI         | M:Ref   | Triage |
      | BF           | M:Ref   | Triage |
      | IE           | M:Ref   | Triage |
      | EUSS         | M:Ref   | Triage |
      | HMPO         | M:Ref   | Triage |
      | Windrush     | M:Ref   | Triage |
      | UKVI         | B:Ref   | Triage |
      | BF           | B:Ref   | Triage |
      | IE           | B:Ref   | Triage |
      | EUSS         | B:Ref   | Triage |
      | HMPO         | B:Ref   | Triage |
      | Windrush     | B:Ref   | Triage |

  @MPAMWorkflow
  Scenario Outline: User escalates a case with specific Business Area and Reference Type at Triage stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Triage case to "Workflow Manager"
    Then the case should be moved to the "<stage> (Escalated)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage  |
      | UKVI         | M:Ref   | Triage |
      | BF           | M:Ref   | Triage |
      | IE           | M:Ref   | Triage |
      | EUSS         | M:Ref   | Triage |
      | HMPO         | M:Ref   | Triage |
      | Windrush     | M:Ref   | Triage |
      | UKVI         | B:Ref   | Triage |
      | BF           | B:Ref   | Triage |
      | IE           | B:Ref   | Triage |
      | EUSS         | B:Ref   | Triage |
      | HMPO         | B:Ref   | Triage |
      | Windrush     | B:Ref   | Triage |

  @MPAMWorkflow
  Scenario Outline: User de-escalates a case with specific Business Area and Reference Type at Triage stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Triage case to "Workflow Manager"
    And I load and claim the current case
    When I de-escalate the Triage (Escalated) case
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage  |
      | UKVI         | M:Ref   | Triage |
      | BF           | M:Ref   | Triage |
      | IE           | M:Ref   | Triage |
      | EUSS         | M:Ref   | Triage |
      | HMPO         | M:Ref   | Triage |
      | Windrush     | M:Ref   | Triage |
      | UKVI         | B:Ref   | Triage |
      | BF           | B:Ref   | Triage |
      | IE           | B:Ref   | Triage |
      | EUSS         | B:Ref   | Triage |
      | HMPO         | B:Ref   | Triage |
      | Windrush     | B:Ref   | Triage |

  @MPAMWorkflow
  Scenario Outline: User moves a case with a specific Business Area and Reference Type to Draft stage
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | M:Ref   | Draft |
      | BF           | M:Ref   | Draft |
      | IE           | M:Ref   | Draft |
      | EUSS         | M:Ref   | Draft |
      | HMPO         | M:Ref   | Draft |
      | Windrush     | M:Ref   | Draft |
      | UKVI         | B:Ref   | Draft |
      | BF           | B:Ref   | Draft |
      | IE           | B:Ref   | Draft |
      | EUSS         | B:Ref   | Draft |
      | HMPO         | B:Ref   | Draft |
      | Windrush     | B:Ref   | Draft |

  @MPAMWorkflow
  Scenario Outline: User puts a case with specific Business Area and Reference Type on hold at Draft stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Draft case to "On Hold"
    Then the case should be moved to the "<stage> (On Hold)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | M:Ref   | Draft |
      | BF           | M:Ref   | Draft |
      | IE           | M:Ref   | Draft |
      | EUSS         | M:Ref   | Draft |
      | HMPO         | M:Ref   | Draft |
      | Windrush     | M:Ref   | Draft |
      | UKVI         | B:Ref   | Draft |
      | BF           | B:Ref   | Draft |
      | IE           | B:Ref   | Draft |
      | EUSS         | B:Ref   | Draft |
      | HMPO         | B:Ref   | Draft |
      | Windrush     | B:Ref   | Draft |

  @MPAMWorkflow
  Scenario Outline: User takes a case with specific Business Area and Reference Type off hold at Draft stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Draft case to "On Hold"
    And I load and claim the current case
    When I take the Draft (On Hold) case off hold
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | M:Ref   | Draft |
      | BF           | M:Ref   | Draft |
      | IE           | M:Ref   | Draft |
      | EUSS         | M:Ref   | Draft |
      | HMPO         | M:Ref   | Draft |
      | Windrush     | M:Ref   | Draft |
      | UKVI         | B:Ref   | Draft |
      | BF           | B:Ref   | Draft |
      | IE           | B:Ref   | Draft |
      | EUSS         | B:Ref   | Draft |
      | HMPO         | B:Ref   | Draft |
      | Windrush     | B:Ref   | Draft |

  @MPAMWorkflow
  Scenario Outline: User escalates a case with specific Business Area and Reference Type at Draft stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Draft case to "Workflow Manager"
    Then the case should be moved to the "<stage> (Escalated)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | M:Ref   | Draft |
      | BF           | M:Ref   | Draft |
      | IE           | M:Ref   | Draft |
      | EUSS         | M:Ref   | Draft |
      | HMPO         | M:Ref   | Draft |
      | Windrush     | M:Ref   | Draft |
      | UKVI         | B:Ref   | Draft |
      | BF           | B:Ref   | Draft |
      | IE           | B:Ref   | Draft |
      | EUSS         | B:Ref   | Draft |
      | HMPO         | B:Ref   | Draft |
      | Windrush     | B:Ref   | Draft |

  @MPAMWorkflow
  Scenario Outline: User de-escalates a case with specific Business Area and Reference Type at Draft stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Draft case to "Workflow Manager"
    And I load and claim the current case
    When I de-escalate the Draft (Escalated) case
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | M:Ref   | Draft |
      | BF           | M:Ref   | Draft |
      | IE           | M:Ref   | Draft |
      | EUSS         | M:Ref   | Draft |
      | HMPO         | M:Ref   | Draft |
      | Windrush     | M:Ref   | Draft |
      | UKVI         | B:Ref   | Draft |
      | BF           | B:Ref   | Draft |
      | IE           | B:Ref   | Draft |
      | EUSS         | B:Ref   | Draft |
      | HMPO         | B:Ref   | Draft |
      | Windrush     | B:Ref   | Draft |

  @MPAMWorkflow
  Scenario Outline: User moves a case with a specific Business Area and Reference Type to QA stage
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | M:Ref   | QA    |
      | BF           | M:Ref   | QA    |
      | IE           | M:Ref   | QA    |
      | EUSS         | M:Ref   | QA    |
      | HMPO         | M:Ref   | QA    |
      | Windrush     | M:Ref   | QA    |
      | UKVI         | B:Ref   | QA    |
      | BF           | B:Ref   | QA    |
      | IE           | B:Ref   | QA    |
      | EUSS         | B:Ref   | QA    |
      | HMPO         | B:Ref   | QA    |
      | Windrush     | B:Ref   | QA    |

  @MPAMWorkflow
  Scenario Outline: User puts a case with specific Business Area and Reference Type on hold at QA stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the "Put on hold" action at QA
    Then the case should be moved to the "<stage> (On Hold)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | M:Ref   | QA    |
      | BF           | M:Ref   | QA    |
      | IE           | M:Ref   | QA    |
      | EUSS         | M:Ref   | QA    |
      | HMPO         | M:Ref   | QA    |
      | Windrush     | M:Ref   | QA    |
      | UKVI         | B:Ref   | QA    |
      | BF           | B:Ref   | QA    |
      | IE           | B:Ref   | QA    |
      | EUSS         | B:Ref   | QA    |
      | HMPO         | B:Ref   | QA    |
      | Windrush     | B:Ref   | QA    |

  @MPAMWorkflow
  Scenario Outline: User takes a case with specific Business Area and Reference Type off hold at QA stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the "Put on hold" action at QA
    And I load and claim the current case
    When I select the "Take off hold" action at the QA On Hold stage
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | M:Ref   | QA    |
      | BF           | M:Ref   | QA    |
      | IE           | M:Ref   | QA    |
      | EUSS         | M:Ref   | QA    |
      | HMPO         | M:Ref   | QA    |
      | Windrush     | M:Ref   | QA    |
      | UKVI         | B:Ref   | QA    |
      | BF           | B:Ref   | QA    |
      | IE           | B:Ref   | QA    |
      | EUSS         | B:Ref   | QA    |
      | HMPO         | B:Ref   | QA    |
      | Windrush     | B:Ref   | QA    |

  @MPAMWorkflow
  Scenario Outline: User escalates a case with specific Business Area and Reference Type at QA stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the "Escalate to Workflow Manager" action at QA
    Then the case should be moved to the "<stage> (Escalated)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | M:Ref   | QA    |
      | BF           | M:Ref   | QA    |
      | IE           | M:Ref   | QA    |
      | EUSS         | M:Ref   | QA    |
      | HMPO         | M:Ref   | QA    |
      | Windrush     | M:Ref   | QA    |
      | UKVI         | B:Ref   | QA    |
      | BF           | B:Ref   | QA    |
      | IE           | B:Ref   | QA    |
      | EUSS         | B:Ref   | QA    |
      | HMPO         | B:Ref   | QA    |
      | Windrush     | B:Ref   | QA    |

  @MPAMWorkflow
  Scenario Outline: User de-escalates a case with specific Business Area and Reference Type at QA stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the "Escalate to Workflow Manager" action at QA
    And I load and claim the current case
    When I select the "Escalation Complete" action at the QA Escalated stage
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | M:Ref   | QA    |
      | BF           | M:Ref   | QA    |
      | IE           | M:Ref   | QA    |
      | EUSS         | M:Ref   | QA    |
      | HMPO         | M:Ref   | QA    |
      | Windrush     | M:Ref   | QA    |
      | UKVI         | B:Ref   | QA    |
      | BF           | B:Ref   | QA    |
      | IE           | B:Ref   | QA    |
      | EUSS         | B:Ref   | QA    |
      | HMPO         | B:Ref   | QA    |
      | Windrush     | B:Ref   | QA    |

  @MPAMWorkflow
  Scenario Outline: User moves a case with a specific Business Area and Reference Type to its appropriate dispatch stage
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage             |
      | UKVI         | M:Ref   | Private Office    |
      | BF           | M:Ref   | Private Office    |
      | IE           | M:Ref   | Private Office    |
      | EUSS         | M:Ref   | Private Office    |
      | HMPO         | M:Ref   | Private Office    |
      | Windrush     | M:Ref   | Private Office    |
      | UKVI         | B:Ref   | Awaiting Dispatch |
      | BF           | B:Ref   | Awaiting Dispatch |
      | IE           | B:Ref   | Awaiting Dispatch |
      | EUSS         | B:Ref   | Awaiting Dispatch |
      | HMPO         | B:Ref   | Awaiting Dispatch |
      | Windrush     | B:Ref   | Awaiting Dispatch |

  @MPAMWorkflow
  Scenario Outline: User closes a case with specific Business Area and Reference Type
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be closed
    Examples:
      | businessArea | refType | stage       |
      | UKVI         | M:Ref   | Case Closed |
      | BF           | M:Ref   | Case Closed |
      | IE           | M:Ref   | Case Closed |
      | EUSS         | M:Ref   | Case Closed |
      | HMPO         | M:Ref   | Case Closed |
      | Windrush     | M:Ref   | Case Closed |

  @MPAMWorkflow
  Scenario Outline: User closes a case with specific Business Area and Reference Type
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be closed
    Examples:
      | businessArea | refType | stage       |
      | UKVI         | B:Ref   | Case Closed |
      | BF           | B:Ref   | Case Closed |
      | IE           | B:Ref   | Case Closed |
      | EUSS         | B:Ref   | Case Closed |
      | HMPO         | B:Ref   | Case Closed |
      | Windrush     | B:Ref   | Case Closed |