Feature: PSU End to End

  Scenario: User completes a IEDET PSU complaint case
    Given I am logged into "CS" as user "IEDET_USER"
    When I create a "IEDET" case and move it to the "PSU Case Closed" stage
    Then the case should be closed
