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
      | UKVI         | M:Ref   | Case Triage |
      | BF           | M:Ref   | Case Triage |
      | IE           | M:Ref   | Case Triage |
      | EUSS         | M:Ref   | Case Triage |
      | HMPO         | M:Ref   | Case Triage |
      | Windrush     | M:Ref   | Case Triage |
      | UKVI         | B:Ref   | Case Triage |
      | BF           | B:Ref   | Case Triage |
      | IE           | B:Ref   | Case Triage |
      | EUSS         | B:Ref   | Case Triage |
      | HMPO         | B:Ref   | Case Triage |
      | Windrush     | B:Ref   | Case Triage |

  @Workflow
  Scenario Outline: User completes Case Triage stage for a case with specific Business Area and Reference Type
    When I create a UKVI case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    Examples:
      | businessArea | refType | stage      |
      | UKVI         | M:Ref   | Case Draft |
      | BF           | M:Ref   | Case Draft |
      | IE           | M:Ref   | Case Draft |
      | EUSS         | M:Ref   | Case Draft |
      | HMPO         | M:Ref   | Case Draft |
      | Windrush     | M:Ref   | Case Draft |
      | UKVI         | B:Ref   | Case Draft |
      | BF           | B:Ref   | Case Draft |
      | IE           | B:Ref   | Case Draft |
      | EUSS         | B:Ref   | Case Draft |
      | HMPO         | B:Ref   | Case Draft |
      | Windrush     | B:Ref   | Case Draft |

  @Workflow
  Scenario Outline: User completes Case Draft stage for a case with specific Business Area and Reference Type
    When I create a UKVI case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    Examples:
      | businessArea | refType | stage   |
      | UKVI         | M:Ref   | Case QA |
      | BF           | M:Ref   | Case QA |
      | IE           | M:Ref   | Case QA |
      | EUSS         | M:Ref   | Case QA |
      | HMPO         | M:Ref   | Case QA |
      | Windrush     | M:Ref   | Case QA |
      | UKVI         | B:Ref   | Case QA |
      | BF           | B:Ref   | Case QA |
      | IE           | B:Ref   | Case QA |
      | EUSS         | B:Ref   | Case QA |
      | HMPO         | B:Ref   | Case QA |
      | Windrush     | B:Ref   | Case QA |

  @Workflow
  Scenario Outline: User completes Case QA stage for a case with specific Business Area and Reference Type
    When I create a UKVI case  with "<businessArea>" as the Business Area and "<refType>" as the Reference Type and move it to the "<stage>" stage
    Then the case should be moved to the "<stage>" stage
    Examples:
      | businessArea | refType | stage               |
      | UKVI         | M:Ref   | Case Private Office |
      | BF           | M:Ref   | Case Private Office |
      | IE           | M:Ref   | Case Private Office |
      | EUSS         | M:Ref   | Case Private Office |
      | HMPO         | M:Ref   | Case Private Office |
      | Windrush     | M:Ref   | Case Private Office |
      | UKVI         | B:Ref   | Case Dispatch       |
      | BF           | B:Ref   | Case Dispatch       |
      | IE           | B:Ref   | Case Dispatch       |
      | EUSS         | B:Ref   | Case Dispatch       |
      | HMPO         | B:Ref   | Case Dispatch       |
      | Windrush     | B:Ref   | Case Dispatch       |

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