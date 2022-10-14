@Escalated @TeamRouting @MPAM @MPAMWorkflow
Feature: Escalated

  Background:
    Given I am logged into "CS" as user "MPAM_USER"

  Scenario Outline: User escalates a case with specific Business Area and Reference Type at Triage stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Triage case to "Workflow Manager"
    Then the case should be moved to the "<stage> (Escalated)" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage  |
      | UKVI                   | Ministerial | Triage |
      | BF                     | Ministerial | Triage |
      | IE                     | Ministerial | Triage |
      | EUSS                   | Ministerial | Triage |
      | HMPO                   | Ministerial | Triage |
      | Windrush               | Ministerial | Triage |
      | Coronavirus (COVID-19) | Ministerial | Triage |
      | UKVI                   | Official    | Triage |
      | BF                     | Official    | Triage |
      | IE                     | Official    | Triage |
      | EUSS                   | Official    | Triage |
      | HMPO                   | Official    | Triage |
      | Windrush               | Official    | Triage |
      | Coronavirus (COVID-19) | Official    | Triage |

  Scenario Outline: User escalates a case with specific Business Area and Reference Type at Triage (Contribution Requested) stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Triage case to "Contributions Requested"
    And I load and claim the current case
    When I select the "Escalate to Workflow Manager" action at the contributions requested stage
    Then the case should be moved to the "<stage> - Escalated (Contribution Requested)" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage  |
      | UKVI                   | Ministerial | Triage |
      | BF                     | Ministerial | Triage |
      | IE                     | Ministerial | Triage |
      | EUSS                   | Ministerial | Triage |
      | HMPO                   | Ministerial | Triage |
      | Windrush               | Ministerial | Triage |
      | Coronavirus (COVID-19) | Ministerial | Triage |
      | UKVI                   | Official    | Triage |
      | BF                     | Official    | Triage |
      | IE                     | Official    | Triage |
      | EUSS                   | Official    | Triage |
      | HMPO                   | Official    | Triage |
      | Windrush               | Official    | Triage |
      | Coronavirus (COVID-19) | Official    | Triage |

  Scenario Outline: User de-escalates a case with specific Business Area and Reference Type at Triage stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Triage case to "Workflow Manager"
    And I load and claim the current case
    When I select the "De-Escalate" action at the Triage-Escalated stage
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage  |
      | UKVI                   | Ministerial | Triage |
      | BF                     | Ministerial | Triage |
      | IE                     | Ministerial | Triage |
      | EUSS                   | Ministerial | Triage |
      | HMPO                   | Ministerial | Triage |
      | Windrush               | Ministerial | Triage |
      | Coronavirus (COVID-19) | Ministerial | Triage |
      | UKVI                   | Official    | Triage |
      | BF                     | Official    | Triage |
      | IE                     | Official    | Triage |
      | EUSS                   | Official    | Triage |
      | HMPO                   | Official    | Triage |
      | Windrush               | Official    | Triage |
      | Coronavirus (COVID-19) | Official    | Triage |

  Scenario Outline: User escalates a case with specific Business Area and Reference Type at Draft stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Draft case to "Workflow Manager"
    Then the case should be moved to the "<stage> (Escalated)" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage |
      | UKVI                   | Ministerial | Draft |
      | BF                     | Ministerial | Draft |
      | IE                     | Ministerial | Draft |
      | EUSS                   | Ministerial | Draft |
      | HMPO                   | Ministerial | Draft |
      | Windrush               | Ministerial | Draft |
      | Coronavirus (COVID-19) | Ministerial | Draft |
      | UKVI                   | Official    | Draft |
      | BF                     | Official    | Draft |
      | IE                     | Official    | Draft |
      | EUSS                   | Official    | Draft |
      | HMPO                   | Official    | Draft |
      | Windrush               | Official    | Draft |
      | Coronavirus (COVID-19) | Official    | Draft |

  Scenario Outline: User escalates a case with specific Business Area and Reference Type at Draft (Contribution Requested) stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Draft case to "Contributions Requested"
    And I load and claim the current case
    When I select the "Escalate to Workflow Manager" action at Draft (Contribution Requested) stage
    Then the case should be moved to the "<stage> - Escalated (Contribution Requested)" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage |
      | UKVI                   | Ministerial | Draft |
      | BF                     | Ministerial | Draft |
      | IE                     | Ministerial | Draft |
      | EUSS                   | Ministerial | Draft |
      | HMPO                   | Ministerial | Draft |
      | Windrush               | Ministerial | Draft |
      | Coronavirus (COVID-19) | Ministerial | Draft |
      | UKVI                   | Official    | Draft |
      | BF                     | Official    | Draft |
      | IE                     | Official    | Draft |
      | EUSS                   | Official    | Draft |
      | HMPO                   | Official    | Draft |
      | Windrush               | Official    | Draft |
      | Coronavirus (COVID-19) | Official    | Draft |

  Scenario Outline: User de-escalates a case with specific Business Area and Reference Type at Draft stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Draft case to "Workflow Manager"
    And I load and claim the current case
    When I select the "De-Escalate" action at the Draft-Escalated stage
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage |
      | UKVI                   | Ministerial | Draft |
      | BF                     | Ministerial | Draft |
      | IE                     | Ministerial | Draft |
      | EUSS                   | Ministerial | Draft |
      | HMPO                   | Ministerial | Draft |
      | Windrush               | Ministerial | Draft |
      | Coronavirus (COVID-19) | Ministerial | Draft |
      | UKVI                   | Official    | Draft |
      | BF                     | Official    | Draft |
      | IE                     | Official    | Draft |
      | EUSS                   | Official    | Draft |
      | HMPO                   | Official    | Draft |
      | Windrush               | Official    | Draft |
      | Coronavirus (COVID-19) | Official    | Draft |

  Scenario Outline: User escalates a case with specific Business Area and Reference Type at QA stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the "Escalate to Workflow Manager" action at QA
    Then the case should be moved to the "<stage> (Escalated)" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage |
      | UKVI                   | Ministerial | QA    |
      | BF                     | Ministerial | QA    |
      | IE                     | Ministerial | QA    |
      | EUSS                   | Ministerial | QA    |
      | HMPO                   | Ministerial | QA    |
      | Windrush               | Ministerial | QA    |
      | Coronavirus (COVID-19) | Ministerial | QA    |
      | UKVI                   | Official    | QA    |
      | BF                     | Official    | QA    |
      | IE                     | Official    | QA    |
      | EUSS                   | Official    | QA    |
      | HMPO                   | Official    | QA    |
      | Windrush               | Official    | QA    |
      | Coronavirus (COVID-19) | Official    | QA    |

  Scenario Outline: User de-escalates a case with specific Business Area and Reference Type at QA stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I select the "Escalate to Workflow Manager" action at QA
    And I load and claim the current case
    When I select the "Escalation Complete" action at the QA Escalated stage
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea           | refType     | stage |
      | UKVI                   | Ministerial | QA    |
      | BF                     | Ministerial | QA    |
      | IE                     | Ministerial | QA    |
      | EUSS                   | Ministerial | QA    |
      | HMPO                   | Ministerial | QA    |
      | Windrush               | Ministerial | QA    |
      | Coronavirus (COVID-19) | Ministerial | QA    |
      | UKVI                   | Official    | QA    |
      | BF                     | Official    | QA    |
      | IE                     | Official    | QA    |
      | EUSS                   | Official    | QA    |
      | HMPO                   | Official    | QA    |
      | Windrush               | Official    | QA    |
      | Coronavirus (COVID-19) | Official    | QA    |