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