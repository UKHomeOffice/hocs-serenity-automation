@EndToEnd
Feature: EndToEnd

  Background:
    Given I am user "AUTOMATION_USER"

  @Workflow
  Scenario Outline: User completes Case Creation stage for a case with specific Business Area and Reference Type
    When I create a UKVI case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    Examples:
      | businessArea | refType | stage       |
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

  @Workflow
  Scenario Outline: User completes Case Triage stage for a case with specific Business Area and Reference Type
    When I create a UKVI case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    Examples:
      | businessArea | refType | stage      |
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

  @Workflow
  Scenario Outline: User completes Case Draft stage for a case with specific Business Area and Reference Type
    When I create a UKVI case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    Examples:
      | businessArea | refType | stage   |
      | UKVI         | M:Ref   | QA |
      | BF           | M:Ref   | QA |
      | IE           | M:Ref   | QA |
      | EUSS         | M:Ref   | QA |
      | HMPO         | M:Ref   | QA |
      | Windrush     | M:Ref   | QA |
      | UKVI         | B:Ref   | QA |
      | BF           | B:Ref   | QA |
      | IE           | B:Ref   | QA |
      | EUSS         | B:Ref   | QA |
      | HMPO         | B:Ref   | QA |
      | Windrush     | B:Ref   | QA |

  @Workflow
  Scenario Outline: User completes Case QA stage for a case with specific Business Area and Reference Type
    When I create a UKVI case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    Examples:
      | businessArea | refType | stage               |
      | UKVI         | M:Ref   | Private Office |
      | BF           | M:Ref   | Private Office |
      | IE           | M:Ref   | Private Office |
      | EUSS         | M:Ref   | Private Office |
      | HMPO         | M:Ref   | Private Office |
      | Windrush     | M:Ref   | Private Office |
      | UKVI         | B:Ref   | Awaiting Dispatch       |
      | BF           | B:Ref   | Awaiting Dispatch       |
      | IE           | B:Ref   | Awaiting Dispatch       |
      | EUSS         | B:Ref   | Awaiting Dispatch       |
      | HMPO         | B:Ref   | Awaiting Dispatch       |
      | Windrush     | B:Ref   | Awaiting Dispatch       |

  Scenario Outline: User completes Case Private Office stage for a case with specific Business Area and Reference Type
    When I create a UKVI case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be closed
    Examples:
      | businessArea | refType | stage       |
      | UKVI         | M:Ref   | Case Closed |
      | BF           | M:Ref   | Case Closed |
      | IE           | M:Ref   | Case Closed |
      | EUSS         | M:Ref   | Case Closed |
      | HMPO         | M:Ref   | Case Closed |
      | Windrush     | M:Ref   | Case Closed |

  Scenario Outline: User completes Case Dispatch stage for a case with specific Business Area and Reference Type
    When I create a UKVI case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be closed
    Examples:
      | businessArea | refType | stage       |
      | UKVI         | B:Ref   | Case Closed |
      | BF           | B:Ref   | Case Closed |
      | IE           | B:Ref   | Case Closed |
      | EUSS         | B:Ref   | Case Closed |
      | HMPO         | B:Ref   | Case Closed |
      | Windrush     | B:Ref   | Case Closed |