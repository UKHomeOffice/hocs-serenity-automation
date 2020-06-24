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
      | UKVI         | Ministerial   | Triage |
      | BF           | Ministerial   | Triage |
      | IE           | Ministerial   | Triage |
      | EUSS         | Ministerial   | Triage |
      | HMPO         | Ministerial   | Triage |
      | Windrush     | Ministerial   | Triage |
      | Coronavirus  | Ministerial   | Triage |
      | UKVI         | Official   | Triage |
      | BF           | Official   | Triage |
      | IE           | Official   | Triage |
      | EUSS         | Official   | Triage |
      | HMPO         | Official   | Triage |
      | Windrush     | Official   | Triage |
      | Coronavirus  | Official   | Triage |

  @MPAMWorkflow
  Scenario Outline: User puts a case with specific Business Area and Reference Type on hold at Triage stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Triage case to "On Hold"
    Then the case should be moved to the "<stage> (On Hold)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage  |
      | UKVI         | Ministerial   | Triage |
      | BF           | Ministerial   | Triage |
      | IE           | Ministerial   | Triage |
      | EUSS         | Ministerial   | Triage |
      | HMPO         | Ministerial   | Triage |
      | Windrush     | Ministerial   | Triage |
      | Coronavirus  | Ministerial   | Triage |
      | UKVI         | Official   | Triage |
      | BF           | Official   | Triage |
      | IE           | Official   | Triage |
      | EUSS         | Official   | Triage |
      | HMPO         | Official   | Triage |
      | Windrush     | Official   | Triage |
      | Coronavirus  | Official   | Triage |


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
      | UKVI         | Ministerial   | Triage |
      | BF           | Ministerial   | Triage |
      | IE           | Ministerial   | Triage |
      | EUSS         | Ministerial   | Triage |
      | HMPO         | Ministerial   | Triage |
      | Windrush     | Ministerial   | Triage |
      | Coronavirus  | Ministerial   | Triage |
      | UKVI         | Official   | Triage |
      | BF           | Official   | Triage |
      | IE           | Official   | Triage |
      | EUSS         | Official   | Triage |
      | HMPO         | Official   | Triage |
      | Windrush     | Official   | Triage |
      | Coronavirus  | Official   | Triage |

  @MPAMWorkflow
  Scenario Outline: User escalates a case with specific Business Area and Reference Type at Triage stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Triage case to "Workflow Manager"
    Then the case should be moved to the "<stage> (Escalated)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage  |
      | UKVI         | Ministerial   | Triage |
      | BF           | Ministerial   | Triage |
      | IE           | Ministerial   | Triage |
      | EUSS         | Ministerial   | Triage |
      | HMPO         | Ministerial   | Triage |
      | Windrush     | Ministerial   | Triage |
      | Coronavirus  | Ministerial   | Triage |
      | UKVI         | Official   | Triage |
      | BF           | Official   | Triage |
      | IE           | Official   | Triage |
      | EUSS         | Official   | Triage |
      | HMPO         | Official   | Triage |
      | Windrush     | Official   | Triage |
      | Coronavirus  | Official   | Triage |

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
      | UKVI         | Ministerial   | Triage |
      | BF           | Ministerial   | Triage |
      | IE           | Ministerial   | Triage |
      | EUSS         | Ministerial   | Triage |
      | HMPO         | Ministerial   | Triage |
      | Windrush     | Ministerial   | Triage |
      | Coronavirus  | Ministerial   | Triage |
      | UKVI         | Official   | Triage |
      | BF           | Official   | Triage |
      | IE           | Official   | Triage |
      | EUSS         | Official   | Triage |
      | HMPO         | Official   | Triage |
      | Windrush     | Official   | Triage |
      | Coronavirus  | Official   | Triage |

  @MPAMWorkflow
  Scenario Outline: User moves a case with a specific Business Area and Reference Type to Draft stage
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | Ministerial   | Draft |
      | BF           | Ministerial   | Draft |
      | IE           | Ministerial   | Draft |
      | EUSS         | Ministerial   | Draft |
      | HMPO         | Ministerial   | Draft |
      | Windrush     | Ministerial   | Draft |
      | Coronavirus  | Ministerial   | Draft |
      | UKVI         | Official   | Draft |
      | BF           | Official   | Draft |
      | IE           | Official   | Draft |
      | EUSS         | Official   | Draft |
      | HMPO         | Official   | Draft |
      | Windrush     | Official   | Draft |
      | Coronavirus  | Official   | Draft |

  @MPAMWorkflow
  Scenario Outline: User puts a case with specific Business Area and Reference Type on hold at Draft stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Draft case to "On Hold"
    Then the case should be moved to the "<stage> (On Hold)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | Ministerial   | Draft |
      | BF           | Ministerial   | Draft |
      | IE           | Ministerial   | Draft |
      | EUSS         | Ministerial   | Draft |
      | HMPO         | Ministerial   | Draft |
      | Windrush     | Ministerial   | Draft |
      | Coronavirus  | Ministerial   | Draft |
      | UKVI         | Official   | Draft |
      | BF           | Official   | Draft |
      | IE           | Official   | Draft |
      | EUSS         | Official   | Draft |
      | HMPO         | Official   | Draft |
      | Windrush     | Official   | Draft |
      | Coronavirus  | Official   | Draft |

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
      | UKVI         | Ministerial   | Draft |
      | BF           | Ministerial   | Draft |
      | IE           | Ministerial   | Draft |
      | EUSS         | Ministerial   | Draft |
      | HMPO         | Ministerial   | Draft |
      | Windrush     | Ministerial   | Draft |
      | Coronavirus  | Ministerial   | Draft |
      | UKVI         | Official   | Draft |
      | BF           | Official   | Draft |
      | IE           | Official   | Draft |
      | EUSS         | Official   | Draft |
      | HMPO         | Official   | Draft |
      | Windrush     | Official   | Draft |
      | Coronavirus  | Official   | Draft |

  @MPAMWorkflow
  Scenario Outline: User escalates a case with specific Business Area and Reference Type at Draft stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Draft case to "Workflow Manager"
    Then the case should be moved to the "<stage> (Escalated)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | Ministerial   | Draft |
      | BF           | Ministerial   | Draft |
      | IE           | Ministerial   | Draft |
      | EUSS         | Ministerial   | Draft |
      | HMPO         | Ministerial   | Draft |
      | Windrush     | Ministerial   | Draft |
      | Coronavirus  | Ministerial   | Draft |
      | UKVI         | Official   | Draft |
      | BF           | Official   | Draft |
      | IE           | Official   | Draft |
      | EUSS         | Official   | Draft |
      | HMPO         | Official   | Draft |
      | Windrush     | Official   | Draft |
      | Coronavirus  | Official   | Draft |

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
      | UKVI         | Ministerial   | Draft |
      | BF           | Ministerial   | Draft |
      | IE           | Ministerial   | Draft |
      | EUSS         | Ministerial   | Draft |
      | HMPO         | Ministerial   | Draft |
      | Windrush     | Ministerial   | Draft |
      | Coronavirus  | Ministerial   | Draft |
      | UKVI         | Official   | Draft |
      | BF           | Official   | Draft |
      | IE           | Official   | Draft |
      | EUSS         | Official   | Draft |
      | HMPO         | Official   | Draft |
      | Windrush     | Official   | Draft |
      | Coronavirus  | Official   | Draft |

  @MPAMWorkflow
  Scenario Outline: User moves a case with a specific Business Area and Reference Type to QA stage
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | Ministerial   | QA    |
      | BF           | Ministerial   | QA    |
      | IE           | Ministerial   | QA    |
      | EUSS         | Ministerial   | QA    |
      | HMPO         | Ministerial   | QA    |
      | Windrush     | Ministerial   | QA    |
      | Coronavirus  | Ministerial   | QA    |
      | UKVI         | Official   | QA    |
      | BF           | Official   | QA    |
      | IE           | Official   | QA    |
      | EUSS         | Official   | QA    |
      | HMPO         | Official   | QA    |
      | Windrush     | Official   | QA    |
      | Coronavirus  | Official   | QA    |

  @MPAMWorkflow
  Scenario Outline: User puts a case with specific Business Area and Reference Type on hold at QA stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the "Put on hold" action at QA
    Then the case should be moved to the "<stage> (On Hold)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | Ministerial   | QA    |
      | BF           | Ministerial   | QA    |
      | IE           | Ministerial   | QA    |
      | EUSS         | Ministerial   | QA    |
      | HMPO         | Ministerial   | QA    |
      | Windrush     | Ministerial   | QA    |
      | Coronavirus  | Ministerial   | QA    |
      | UKVI         | Official   | QA    |
      | BF           | Official   | QA    |
      | IE           | Official   | QA    |
      | EUSS         | Official   | QA    |
      | HMPO         | Official   | QA    |
      | Windrush     | Official   | QA    |
      | Coronavirus  | Official   | QA    |

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
      | UKVI         | Ministerial   | QA    |
      | BF           | Ministerial   | QA    |
      | IE           | Ministerial   | QA    |
      | EUSS         | Ministerial   | QA    |
      | HMPO         | Ministerial   | QA    |
      | Windrush     | Ministerial   | QA    |
      | Coronavirus  | Ministerial   | QA    |
      | UKVI         | Official   | QA    |
      | BF           | Official   | QA    |
      | IE           | Official   | QA    |
      | EUSS         | Official   | QA    |
      | HMPO         | Official   | QA    |
      | Windrush     | Official   | QA    |
      | Coronavirus  | Official   | QA    |

  @MPAMWorkflow
  Scenario Outline: User escalates a case with specific Business Area and Reference Type at QA stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the "Escalate to Workflow Manager" action at QA
    Then the case should be moved to the "<stage> (Escalated)" stage
    And should be in the expected MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType | stage |
      | UKVI         | Ministerial   | QA    |
      | BF           | Ministerial   | QA    |
      | IE           | Ministerial   | QA    |
      | EUSS         | Ministerial   | QA    |
      | HMPO         | Ministerial   | QA    |
      | Windrush     | Ministerial   | QA    |
      | Coronavirus  | M:Ref   | QA    |
      | UKVI         | Official   | QA    |
      | BF           | Official   | QA    |
      | IE           | Official   | QA    |
      | EUSS         | Official   | QA    |
      | HMPO         | Official   | QA    |
      | Windrush     | Official   | QA    |
      | Coronavirus  | Official   | QA    |

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
      | Coronavirus  | M:Ref   | QA    |
      | UKVI         | Official   | QA    |
      | BF           | B:Ref   | QA    |
      | IE           | B:Ref   | QA    |
      | EUSS         | B:Ref   | QA    |
      | HMPO         | B:Ref   | QA    |
      | Windrush     | B:Ref   | QA    |
      | Coronavirus  | B:Ref   | QA    |

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
      | Coronavirus  | M:Ref   | Private Office    |
      | UKVI         | B:Ref   | Awaiting Dispatch |
      | BF           | B:Ref   | Awaiting Dispatch |
      | IE           | B:Ref   | Awaiting Dispatch |
      | EUSS         | B:Ref   | Awaiting Dispatch |
      | HMPO         | B:Ref   | Awaiting Dispatch |
      | Windrush     | B:Ref   | Awaiting Dispatch |
      | Coronavirus  | B:Ref   | Awaiting Dispatch |

  @MPAMWorkflow
  Scenario Outline: User closes a M:Ref case with specific Business Area and Reference Type
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
      | Coronavirus  | M:Ref   | Case Closed |

  @MPAMWorkflow
  Scenario Outline: User closes a B:Ref case with specific Business Area and Reference Type
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
      | Coronavirus  | B:Ref   | Case Closed |