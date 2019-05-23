Feature: HOCS User is able to create a case

  Background:
    Given I am user "EAMON"
    When I am on the "CREATE SINGLE CASE" page

  @HOCS-341 @HOCS-491 @HOCS-236 @DCUMIN
  Scenario: I must select a type of correspondent
    When I do not select a type of correspondence when creating a case
    Then an error message is displayed

  @HOCS-341 @HOCS-491 @HOCS-236 @@SmokeTests @CreateCases @DCUMIN
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

  @Allocate
  Scenario: A single case is allocated to the current user
    And I create a single MIN case
    When I allocate the case to myself via the successful case creation screen
    Then the case should be visible in my workstack

  @Allocate
  Scenario: A single case is allocated to the current user using checkboxes
    And I create a single MIN case
    When I navigate to the Performance and Process Team and select the check box against the newly created case and allocate it to myself
    Then the case should be visible in my workstack

  Scenario: I can bulk upload cases
    When I bulk create 40 "DCU MIN" cases
    Then A case is created successfully

  Scenario: Newly created MIN cases should be moved to the Performance and Process Team workstack
    And I create a single MIN case
    Then the case should be visible in the Performance and Process Team workstack

  @Navigation
  Scenario: User should be taken back to the dashboard when they click the cancel button on the what type of correspondence page
    And I click the "CANCEL" button
    Then I should be taken to the homepage

  @Navigation
  Scenario: User should be taken back to the dashboard when they click the cancel button on the when was the correspodence received page
    And I move to the When Was Correspondence Received Page
    And I click the "CANCEL" button
    Then I should be taken to the homepage

  @Validation
  Scenario: Create Single Case correspondence selection is validated
    And I click the "NEXT" button
    Then an error message should be displayed as I have not selected the case type

  @Validation @SmokeTests
  Scenario: When creating a Single MIN Case date received is required
    And I move to the When Was Correspondence Received Page
    And I enter a blank date
    And I click the "FINISH" button
    Then an error message should be displayed as I have not entered the correspondence received date

  @Validation @SmokeTests
  Scenario: When creating a Single MIN case a valid date must be entered
    And I move to the When Was Correspondence Received Page
    And I enter an invalid date
    And I click the "FINISH" button
    Then an error message should be displayed as I have entered an invalid date

