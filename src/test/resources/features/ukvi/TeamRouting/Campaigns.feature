@Campaign @TeamRouting @UKVI @UKVIWorkflow
Feature: Campaigns

  Background:
    Given I am logged into "DECS" as user "UKVI_USER"

  Scenario Outline: User moves a case with a specific Business Area and Reference Type from Triage to Campaign and its appropriate team
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I move the case into a Campaign from the "<stage>" stage
    And I load the current case
    Then the case should be moved to the "Campaign" stage
    And the case should be in the correct MPAM "Campaign" team workstack
    Examples:
      | businessArea | refType       | stage  |
      | UKVI         | Ministerial   | Triage |
      | BF           | Ministerial   | Triage |
      | IE           | Ministerial   | Triage |
      | EUSS         | Ministerial   | Triage |
      | HMPO         | Ministerial   | Triage |
      | Windrush     | Ministerial   | Triage |
      | Coronavirus  | Ministerial   | Triage |
      | UKVI         | Official      | Triage |
      | BF           | Official      | Triage |
      | IE           | Official      | Triage |
      | EUSS         | Official      | Triage |
      | HMPO         | Official      | Triage |
      | Windrush     | Official      | Triage |
      | Coronavirus  | Official      | Triage |

  Scenario Outline: User moves a case with specific Business Area and Reference Type to Campaign from Triage (On Hold)
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<initial>" stage
    And I load and claim the current case
    And I send the Triage case to "On Hold"
    And I load and claim the current case
    And I move the case into a Campaign from the "Triage-On Hold" stage
    And I load the current case
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType       | initial | stage    |
      | UKVI         | Ministerial   | Triage  | Campaign |
      | BF           | Ministerial   | Triage  | Campaign |
      | IE           | Ministerial   | Triage  | Campaign |
      | EUSS         | Ministerial   | Triage  | Campaign |
      | HMPO         | Ministerial   | Triage  | Campaign |
      | Windrush     | Ministerial   | Triage  | Campaign |
      | Coronavirus  | Ministerial   | Triage  | Campaign |
      | UKVI         | Official      | Triage  | Campaign |
      | BF           | Official      | Triage  | Campaign |
      | IE           | Official      | Triage  | Campaign |
      | EUSS         | Official      | Triage  | Campaign |
      | HMPO         | Official      | Triage  | Campaign |
      | Windrush     | Official      | Triage  | Campaign |
      | Coronavirus  | Official      | Triage  | Campaign |

  Scenario Outline: User moves a case with specific Business Area and Reference Type to Campaign from Triage (Escalated)
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<initial>" stage
    And I load and claim the current case
    And I send the Triage case to "Workflow Manager"
    And I load and claim the current case
    And I move the case into a Campaign from the "Triage-Escalated" stage
    And I load the current case
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType       | initial | stage    |
      | UKVI         | Ministerial   | Triage  | Campaign |
      | BF           | Ministerial   | Triage  | Campaign |
      | IE           | Ministerial   | Triage  | Campaign |
      | EUSS         | Ministerial   | Triage  | Campaign |
      | HMPO         | Ministerial   | Triage  | Campaign |
      | Windrush     | Ministerial   | Triage  | Campaign |
      | Coronavirus  | Ministerial   | Triage  | Campaign |
      | UKVI         | Official      | Triage  | Campaign |
      | BF           | Official      | Triage  | Campaign |
      | IE           | Official      | Triage  | Campaign |
      | EUSS         | Official      | Triage  | Campaign |
      | HMPO         | Official      | Triage  | Campaign |
      | Windrush     | Official      | Triage  | Campaign |
      | Coronavirus  | Official      | Triage  | Campaign |

  Scenario Outline: User moves a case with specific Business Area and Reference Type to Campaign from Triage (Contribution Requested)
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<initial>" stage
    And I load and claim the current case
    And I send the Triage case to "Contributions Requested"
    And I load and claim the current case
    And I move the case into a Campaign from the "Triage-Contributions Requested" stage
    And I load the current case
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType       | initial | stage    |
      | UKVI         | Ministerial   | Triage  | Campaign |
      | BF           | Ministerial   | Triage  | Campaign |
      | IE           | Ministerial   | Triage  | Campaign |
      | EUSS         | Ministerial   | Triage  | Campaign |
      | HMPO         | Ministerial   | Triage  | Campaign |
      | Windrush     | Ministerial   | Triage  | Campaign |
      | Coronavirus  | Ministerial   | Triage  | Campaign |
      | UKVI         | Official      | Triage  | Campaign |
      | BF           | Official      | Triage  | Campaign |
      | IE           | Official      | Triage  | Campaign |
      | EUSS         | Official      | Triage  | Campaign |
      | HMPO         | Official      | Triage  | Campaign |
      | Windrush     | Official      | Triage  | Campaign |
      | Coronavirus  | Official      | Triage  | Campaign |

  Scenario Outline: User moves a case with a specific Business Area and Reference Type from Draft to Campaign and its appropriate team
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I move the case into a Campaign from the "<stage>" stage
    And I load the current case
    Then the case should be moved to the "Campaign" stage
    And the case should be in the correct MPAM "Campaign" team workstack
    Examples:
      | businessArea | refType       | stage |
      | UKVI         | Ministerial   | Draft |
      | BF           | Ministerial   | Draft |
      | IE           | Ministerial   | Draft |
      | EUSS         | Ministerial   | Draft |
      | HMPO         | Ministerial   | Draft |
      | Windrush     | Ministerial   | Draft |
      | Coronavirus  | Ministerial   | Draft |
      | UKVI         | Official      | Draft |
      | BF           | Official      | Draft |
      | IE           | Official      | Draft |
      | EUSS         | Official      | Draft |
      | HMPO         | Official      | Draft |
      | Windrush     | Official      | Draft |
      | Coronavirus  | Official      | Draft |

  Scenario Outline: User moves a case with specific Business Area and Reference Type to Campaign from Draft (On Hold)
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<initial>" stage
    And I load and claim the current case
    And I send the Draft case to "On Hold"
    And I load and claim the current case
    And I move the case into a Campaign from the "Draft-On Hold" stage
    And I load the current case
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType       | initial | stage    |
      | UKVI         | Ministerial   | Draft   | Campaign |
      | BF           | Ministerial   | Draft   | Campaign |
      | IE           | Ministerial   | Draft   | Campaign |
      | EUSS         | Ministerial   | Draft   | Campaign |
      | HMPO         | Ministerial   | Draft   | Campaign |
      | Windrush     | Ministerial   | Draft   | Campaign |
      | Coronavirus  | Ministerial   | Draft   | Campaign |
      | UKVI         | Official      | Draft   | Campaign |
      | BF           | Official      | Draft   | Campaign |
      | IE           | Official      | Draft   | Campaign |
      | EUSS         | Official      | Draft   | Campaign |
      | HMPO         | Official      | Draft   | Campaign |
      | Windrush     | Official      | Draft   | Campaign |
      | Coronavirus  | Official      | Draft   | Campaign |

  Scenario Outline: User moves a case with specific Business Area and Reference Type to Campaign from Draft (Escalated)
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<initial>" stage
    And I load and claim the current case
    And I send the Draft case to "Workflow Manager"
    And I load and claim the current case
    And I move the case into a Campaign from the "Draft-Escalated" stage
    And I load the current case
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType       | initial | stage    |
      | UKVI         | Ministerial   | Draft   | Campaign |
      | BF           | Ministerial   | Draft   | Campaign |
      | IE           | Ministerial   | Draft   | Campaign |
      | EUSS         | Ministerial   | Draft   | Campaign |
      | HMPO         | Ministerial   | Draft   | Campaign |
      | Windrush     | Ministerial   | Draft   | Campaign |
      | Coronavirus  | Ministerial   | Draft   | Campaign |
      | UKVI         | Official      | Draft   | Campaign |
      | BF           | Official      | Draft   | Campaign |
      | IE           | Official      | Draft   | Campaign |
      | EUSS         | Official      | Draft   | Campaign |
      | HMPO         | Official      | Draft   | Campaign |
      | Windrush     | Official      | Draft   | Campaign |
      | Coronavirus  | Official      | Draft   | Campaign |

  Scenario Outline: User moves a case with specific Business Area and Reference Type to Campaign from Draft (Contributions Requested)
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<initial>" stage
    And I load and claim the current case
    And I send the Draft case to "Contributions Requested"
    And I load and claim the current case
    And I move the case into a Campaign from the "Draft-Contributions Requested" stage
    And I load the current case
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType       | initial | stage    |
      | UKVI         | Ministerial   | Draft   | Campaign |
      | BF           | Ministerial   | Draft   | Campaign |
      | IE           | Ministerial   | Draft   | Campaign |
      | EUSS         | Ministerial   | Draft   | Campaign |
      | HMPO         | Ministerial   | Draft   | Campaign |
      | Windrush     | Ministerial   | Draft   | Campaign |
      | Coronavirus  | Ministerial   | Draft   | Campaign |
      | UKVI         | Official      | Draft   | Campaign |
      | BF           | Official      | Draft   | Campaign |
      | IE           | Official      | Draft   | Campaign |
      | EUSS         | Official      | Draft   | Campaign |
      | HMPO         | Official      | Draft   | Campaign |
      | Windrush     | Official      | Draft   | Campaign |
      | Coronavirus  | Official      | Draft   | Campaign |

  Scenario Outline: User moves a case with a specific Business Area and Reference Type from QA to Campaign and its appropriate team
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I move the case into a Campaign from the "<stage>" stage
    And I load the current case
    Then the case should be moved to the "Campaign" stage
    And the case should be in the correct MPAM "Campaign" team workstack
    Examples:
      | businessArea | refType       | stage |
      | UKVI         | Ministerial   | QA    |
      | BF           | Ministerial   | QA    |
      | IE           | Ministerial   | QA    |
      | EUSS         | Ministerial   | QA    |
      | HMPO         | Ministerial   | QA    |
      | Windrush     | Ministerial   | QA    |
      | Coronavirus  | Ministerial   | QA    |
      | UKVI         | Official      | QA    |
      | BF           | Official      | QA    |
      | IE           | Official      | QA    |
      | EUSS         | Official      | QA    |
      | HMPO         | Official      | QA    |
      | Windrush     | Official      | QA    |
      | Coronavirus  | Official      | QA    |

  Scenario Outline: User moves a case with specific Business Area and Reference Type to Campaign from QA (On Hold)
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<initial>" stage
    And I load and claim the current case
    And I select the "Put On Hold" action at QA
    And I load and claim the current case
    And I move the case into a Campaign from the "QA-On Hold" stage
    And I load the current case
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType       | initial | stage    |
      | UKVI         | Ministerial   | QA      | Campaign |
      | BF           | Ministerial   | QA      | Campaign |
      | IE           | Ministerial   | QA      | Campaign |
      | EUSS         | Ministerial   | QA      | Campaign |
      | HMPO         | Ministerial   | QA      | Campaign |
      | Windrush     | Ministerial   | QA      | Campaign |
      | Coronavirus  | Ministerial   | QA      | Campaign |
      | UKVI         | Official      | QA      | Campaign |
      | BF           | Official      | QA      | Campaign |
      | IE           | Official      | QA      | Campaign |
      | EUSS         | Official      | QA      | Campaign |
      | HMPO         | Official      | QA      | Campaign |
      | Windrush     | Official      | QA      | Campaign |
      | Coronavirus  | Official      | QA      | Campaign |

  Scenario Outline: User moves a case with specific Business Area and Reference Type to Campaign from QA (Escalated)
    And I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<initial>" stage
    And I load and claim the current case
    And I select the "Escalated to Workflow Manager" action at QA
    And I load and claim the current case
    And I move the case into a Campaign from the "QA-Escalated" stage
    And I load the current case
    Then the case should be moved to the "<stage>" stage
    And the case should be in the correct MPAM "<stage>" team workstack
    Examples:
      | businessArea | refType       | initial | stage    |
      | UKVI         | Ministerial   | QA      | Campaign |
      | BF           | Ministerial   | QA      | Campaign |
      | IE           | Ministerial   | QA      | Campaign |
      | EUSS         | Ministerial   | QA      | Campaign |
      | HMPO         | Ministerial   | QA      | Campaign |
      | Windrush     | Ministerial   | QA      | Campaign |
      | Coronavirus  | Ministerial   | QA      | Campaign |
      | UKVI         | Official      | QA      | Campaign |
      | BF           | Official      | QA      | Campaign |
      | IE           | Official      | QA      | Campaign |
      | EUSS         | Official      | QA      | Campaign |
      | HMPO         | Official      | QA      | Campaign |
      | Windrush     | Official      | QA      | Campaign |
      | Coronavirus  | Official      | QA      | Campaign |

  Scenario Outline: User moves a case with a specific Business Area and Reference Type from Dispatch stages to Campaign and its appropriate team
    When I create a MPAM case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    And I load and claim the current case
    And I move the case into a Campaign from the "<stage>" stage
    And I load the current case
    Then the case should be moved to the "Campaign" stage
    And the case should be in the correct MPAM "Campaign" team workstack
    Examples:
      | businessArea | refType       | stage             |
      | UKVI         | Ministerial   | Private Office    |
      | BF           | Ministerial   | Private Office    |
      | IE           | Ministerial   | Private Office    |
      | EUSS         | Ministerial   | Private Office    |
      | HMPO         | Ministerial   | Private Office    |
      | Windrush     | Ministerial   | Private Office    |
      | Coronavirus  | Ministerial   | Private Office    |
      | UKVI         | Official      | Awaiting Dispatch |
      | BF           | Official      | Awaiting Dispatch |
      | IE           | Official      | Awaiting Dispatch |
      | EUSS         | Official      | Awaiting Dispatch |
      | HMPO         | Official      | Awaiting Dispatch |
      | Windrush     | Official      | Awaiting Dispatch |
      | Coronavirus  | Official      | Awaiting Dispatch |

  Scenario Outline: User moves a case out of a Campaign
    When I create a "MPAM" case and move it to the "<initialStage>" stage
    And I load and claim the current case
    And I move the case into a Campaign from the "<initialStage>" stage
    And I load the current case
    And I move the case from Campaign to "<finalStage>"
    And I load the current case
    Then the case should be moved to the "<finalStage>" stage
    Examples:
    | initialStage  | finalStage  |
    | Triage        | Triage      |
    | Draft         | Triage      |
    | Draft         | Draft       |