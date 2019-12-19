Feature: HOCS User is able to create a case

  Background:
    Given I log in as the designated user
    When I navigate to the "CREATE SINGLE CASE" page

  @CreateCase @DCUMIN @HOCS-341 @HOCS-491 @HOCS-236
  Scenario: I must select a type of correspondence
    When I do not select a type of correspondence when creating a case
    Then an error message is displayed

  @CreateCase @SmokeTests @DCUMIN @HOCS-341 @HOCS-491 @HOCS-236
  Scenario Outline: I can create a case
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

  @CreateCase @Allocate
  Scenario: A single case is allocated to the current user
    And I create a single "DCU MIN" case
    When I allocate the case to myself via the successful case creation screen
    Then the case should be visible in my workstack

  @CreateCase @Allocate
  Scenario: A single case is allocated to the current user using checkboxes
    And I create a single "DCU MIN" case
    When I navigate to the "PERFORMANCE AND PROCESS TEAM" and select the check box against the newly created case and allocate it to myself
    Then the case should be visible in my workstack

  @CreateCases @DCUMIN
  Scenario: I can bulk upload cases
    When I bulk create 40 "DCU MIN" cases
    Then bulk cases are created successfully

  @CreateCase
  Scenario: Newly created MIN cases should be moved to the Performance and Process Team workstack
    And I create a single MIN case
    Then the case should be visible in the Performance and Process Team workstack

  @CreateCase @Navigation
  Scenario: User should be taken back to the dashboard when they click the back button on the what type of correspondence
  page
    And I click the "BACK" button
    Then I should be taken to the homepage

  @CreateCase @Navigation
  Scenario: User should be taken back to the dashboard when they click the back button on the when was the
  correspondence received page
    And I move to the When Was Correspondence Received Page
    And I click the "BACK" button
    Then I should be taken to the homepage

  @CreateCase @Validation
  Scenario: When creating a Single Case case type selection is required
    And I click the "NEXT" button
    Then an error message should be displayed as I have not selected the case type

  @CreateCase @Validation @SmokeTests
  Scenario: When creating a Single MIN Case date received is required
    And I move to the When Was Correspondence Received Page
    And I enter a blank date
    And I click the "FINISH" button
    Then an error message should be displayed as I have not entered the correspondence received date

  @CreateCase @Validation @SmokeTests
  Scenario: When creating a Single MIN case a valid date must be entered
    And I move to the When Was Correspondence Received Page
    And I enter an invalid date
    And I click the "FINISH" button
    Then an error message should be displayed as I have entered an invalid date