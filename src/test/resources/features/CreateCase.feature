Feature: HOCS User is able to create a case

  Background:
    Given I am user "DANNY"

  @HOCS-341 @HOCS-491 @HOCS-236 @DCUMIN
  Scenario: I must select a type of correspondent
    When I do not select a type of correspondence when creating a case
    Then an error message is displayed

  @HOCS-341 @HOCS-491 @HOCS-236 @SmokeTests @CreateCases @DCUMIN
  Scenario Outline: I can create a case
    When I create a "<case>" case "<with / without>" a document
    Then A case is created successfully
    Examples:
      | case    | with / without |
      | DCU min | with           |
      | DCU min | without        |
#      | DCU TRO | with           |
#      | DCU TRO | without        |
#      | DCU TEN | with           |
#      | DCU TEN | without        |

  Scenario: I can bulk upload cases
    When I bulk create 40 "DCU MIN" cases
    Then A case is created successfully