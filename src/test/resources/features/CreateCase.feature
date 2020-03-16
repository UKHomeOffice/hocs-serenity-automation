Feature: HOCS User is able to create a case

  Background:
    Given I am user "AUTOMATION_USER"
    When I navigate to the "CREATE SINGLE CASE" page

  @CreateCase @Validation
  Scenario: I must select a type of correspondence
    When I do not select a type of correspondence when creating a case
    Then an error message is displayed

  @CreateCase @Workflow @SmokeTests
  Scenario Outline: I can create a case
    When I create a "<case>" case "<with / without>" a document
    Then A case is created successfully
    Examples:
      | case    | with / without |
      | MIN | with           |
      | MIN | without        |
      | TRO | with           |
      | TRO | without        |
      | DTEN | with           |
      | DTEN | without        |

  @CreateCase @Allocation
  Scenario: A single case is allocated to the current user
    And I create a single "MIN" case
    When I allocate the case to myself via the successful case creation screen
    Then the case should be visible in my workstack

  @CreateCase @Workflow @SmokeTests @Ignore
  Scenario: I can bulk upload cases
    When I bulk create 40 "MIN" cases
    Then bulk cases are created successfully

  @CreateCase @Workflow
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

  @CreateCase @Validation
  Scenario: When creating a Single MIN Case date received is required
    And I move to the When Was Correspondence Received Page
    And I enter a blank date
    And I click the "FINISH" button
    Then an error message should be displayed as I have not entered the correspondence received date

  @CreateCase @Validation
  Scenario: When creating a Single MIN case a valid date must be entered
    And I move to the When Was Correspondence Received Page
    And I enter an invalid date
    And I click the "FINISH" button
    Then an error message should be displayed as I have entered an invalid date