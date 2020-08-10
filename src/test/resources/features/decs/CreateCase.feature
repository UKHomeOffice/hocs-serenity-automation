@CreateCase @DECS
Feature: Create case

  Background:
    Given I log in to DECS
    When I navigate to the "CREATE SINGLE CASE" page

  @Validation
  Scenario: I must select a type of correspondence
    When I do not select a type of correspondence when creating a case
    Then an error message is displayed

  @SmokeTests
  Scenario Outline: I can create a case
    When I create a "<case>" case "<with / without>" a document
    Then A case is created successfully "<with / without>" a document
    Examples:
      | case | with / without |
      | MIN  | with           |
      | MIN  | without        |
      | TRO  | with           |
      | TRO  | without        |
      | DTEN | with           |
      | DTEN | without        |
      | MPAM | with           |
      | MPAM | without        |

  @Allocation @OtherTests
  Scenario: A single case is allocated to the current user
    And I create a single "MIN" case
    When I allocate the case to myself via the successful case creation screen
    Then the case should be visible in my workstack

  @Allocation @OtherTests
  Scenario: A single case is allocated to another user
    And I create a single "MPAM" case
    And I go to the case from the successful case creation screen
    When I allocate the case to "CAMERON" on the case details accordion screen
    And I click to view the "MPAM Creation" workstack
    Then the owner field should display "CAMERON"

  @Workflow @SmokeTests
  Scenario: I can bulk upload cases
    When I bulk create 40 "MIN" cases
    Then bulk cases are created successfully

  @DCUWorkflow @DCUSmokeTests
  Scenario Outline: Newly created DCU cases should move to the Data Input stage
    And I create a single "<caseType>" case and return to the dashboard
    Then the case should be moved to the "Data Input" stage
    Examples:
      | caseType |
      | MIN      |
      | DTEN     |
      | TRO      |

  @MPAMWorkflow @MPAMSmokeTests
  Scenario: Newly created MPAM cases should move to the Creation stage
    And I create a single "MPAM" case and return to the dashboard
    Then the case should be moved to the "Creation" stage

  @Navigation
  Scenario: User should be taken back to the dashboard when they click the back button on the what type of correspondence
  page
    And I click the "BACK" link
    Then I should be taken to the homepage

  @Navigation
  Scenario: User should be taken back to the dashboard when they click the back button on the when was the
  correspondence received page
    And I move to the When Was Correspondence Received Page
    And I click the "BACK" link
    Then I should be taken to the homepage

  @Validation
  Scenario: When creating a Single Case case type selection is required
    And I click the "Next" button
    Then an error message should be displayed as I have not selected the case type

  @Validation
  Scenario: When creating a Single MIN Case date received is required
    And I move to the When Was Correspondence Received Page
    And I enter a blank date
    And I click the "Create case" button
    Then an error message should be displayed as I have not entered the correspondence received date

  @Validation
  Scenario: When creating a Single MIN case a valid date must be entered
    And I move to the When Was Correspondence Received Page
    And I enter an invalid date
    And I click the "Create case" button
    Then an error message should be displayed as I have entered an invalid date