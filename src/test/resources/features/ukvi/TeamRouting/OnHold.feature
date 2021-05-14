@OnHold @TeamRouting @UKVI @UKVIWorkflow
Feature: OnHold

  Background:
    Given I am logged into "DECS" as user "UKVI_USER"

  Scenario Outline: User puts a case with specific Business Area and Reference Type on hold at Triage stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Triage case to "On Hold"
    Then the case should be moved to the "<stage> (On Hold)" stage
    And the case should be in the correct MPAM "<stage>" team workstack
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

  Scenario Outline: User takes a case with specific Business Area and Reference Type off hold at Triage stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Triage case to "On Hold"
    And I load and claim the current case
    When I take the Triage (On Hold) case off hold
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
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

  Scenario Outline: User puts a case with specific Business Area and Reference Type on hold at Draft stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Draft case to "On Hold"
    Then the case should be moved to the "<stage> (On Hold)" stage
    And the case should be in the correct MPAM "<stage>" team workstack
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

  Scenario Outline: User takes a case with specific Business Area and Reference Type off hold at Draft stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Draft case to "On Hold"
    And I load and claim the current case
    When I take the Draft (On Hold) case off hold
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
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

  Scenario Outline: User puts a case with specific Business Area and Reference Type on hold at QA stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the "Put on hold" action at QA
    Then the case should be moved to the "<stage> (On Hold)" stage
    And the case should be in the correct MPAM "<stage>" team workstack
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

  Scenario Outline: User takes a case with specific Business Area and Reference Type off hold at QA stage
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the "Put on hold" action at QA
    And I load and claim the current case
    When I select the "Take off hold" action at the QA On Hold stage
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
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