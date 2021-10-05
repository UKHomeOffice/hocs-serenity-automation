@ContributionRequest @TeamRouting @UKVI @UKVIWorkflow
Feature: Contribution Request

  Background:
    Given I am logged into "CS" as user "MPAM_USER"

  Scenario Outline: User requests a contribution at Triage stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Triage case to "Contributions Requested"
    Then the case should be moved to the "<stage> (Contribution Requested)" stage
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

  Scenario Outline: User records that a contribution has been received and returns a case to the Triage stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Triage case to "Contributions Requested"
    And I load and claim the current case
    And I choose to "Complete" the contribution request at the multiple contribution stage
    When I select the "Contributions received" action at the contributions requested stage
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

  Scenario Outline: User requests a contribution at Draft stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Draft case to "Contributions Requested"
    Then the case should be moved to the "<stage> (Contribution Requested)" stage
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

  Scenario Outline: User records that a contribution has been received and returns a case to the Draft stage
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I send the Draft case to "Contributions Requested"
    And I load and claim the current case
    And I choose to "Complete" the contribution request at the multiple contribution stage
    When I select the "Contributions received" action at Draft (Contribution Requested) stage
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

  @UKVIRegression1
  Scenario: User requests contribution at Triage-Escalated stage
    And I create a MPAM case with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Triage" stage
    And I load and claim the current case
    When I send the Triage case to "Workflow Manager"
    And I load the current case
    And I select the "Contributions Requested" action at the Triage-Escalated stage
    Then the case should be moved to the "Triage - Escalated (Contribution Requested)" stage

  Scenario Outline: User requests a contribution at Triage-Escalated stage through other MPAM teams
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Triage case to "Workflow Manager"
    And I load the current case
    And I select the "Contributions Requested" action at the Triage-Escalated stage
    Then the case should be moved to the "<stage> - Escalated (Contribution Requested)" stage
    Examples:
      | businessArea | refType | stage  |
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

  @UKVIRegression1
  Scenario: User requests contribution at Draft-Escalated stage
    And I create a MPAM case with "UKVI" as the Business Area and "Ministerial" as the Reference Type and move it to the "Draft" stage
    And I load and claim the current case
    When I send the Draft case to "Workflow Manager"
    And I load the current case
    And I select the "Contributions Requested" action at the Draft-Escalated stage
    Then the case should be moved to the "Draft - Escalated (Contribution Requested)" stage

  Scenario Outline: User requests a contribution at Draft-Escalated stage through other MPAM teams
    And I create a MPAM case with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    When I send the Draft case to "Workflow Manager"
    And I load the current case
    And I select the "Contributions Requested" action at the Draft-Escalated stage
    Then the case should be moved to the "<stage> - Escalated (Contribution Requested)" stage
    Examples:
      | businessArea | refType       | stage |
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