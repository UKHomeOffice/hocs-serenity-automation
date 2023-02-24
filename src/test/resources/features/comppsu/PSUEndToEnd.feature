Feature: PSU End to End

  Scenario: User completes a IEDET PSU complaint case
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Case Closed" stage
    Then the case should be closed

  Scenario Outline: User completes a BF PSU complaint case
    Given I am logged into "CS" as user "BF_USER"
    When I create a "<caseType>" case and move it to the "PSU_Closed" stage
    Then the case should be closed
    Examples:
      | caseType |
      | BF       |
#      | BF2      |
