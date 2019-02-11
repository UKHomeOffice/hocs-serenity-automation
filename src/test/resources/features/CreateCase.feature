Feature: HOCS User is able to create a case

  Background:
    Given I am user "DANNY"
    When I am on the "CREATE SINGLE CASE" page

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

  @Validation
  Scenario: Create Single Case correspondence selection is validated
    And I do not select a type of correspondence when creating a case
    Then an error message should be displayed informing the user that case type is required

  @Validation
  Scenario: When creating a Single MIN Case date received is required
    And I do not entered date received in the text boxes
    Then an error message should be displayed when I create the case

  @Validation
  Scenario: When creating a Single MIN case a valid date must be entered
    And I create the case with an invalid date
    Then an error message should be displayed informing the user that the date is invalid

