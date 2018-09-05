Feature: HOCS User is able to create a case

  @HOCS-341 @HOCS-491 @HOCS-236 @critical
  Scenario Outline: I can create a case
    Given I am user "Dom"
    When I create a "<case>" case "<with / without>" a document
    Then A case is created successfully
    Examples:
      | case    | with / without |
      | DCU min | with           |
      | DCU min | without        |
      | DCU TRO | with           |
      | DCU TRO | without        |
      | DCU TEN | with           |
      | DCU TEN | without        |

  Scenario: I can bulk upload cases
    Given I am user "Dom"
    When I bulk create 40 "DCU MIN" cases
    Then A case is created successfully