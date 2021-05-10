@CreateCase @DECS
Feature: Create case

  Background:
    Given I log in to "DECS" as user "DECS_USER"
    When I navigate to the "CREATE SINGLE CASE" page

  @Validation
  Scenario: I must select a type of correspondence
    When I do not select a type of correspondence when creating a case
    Then an error message is displayed

  @Regression
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
      | MTS  | with           |
      | MTS  | without        |

  @Allocation @OtherTests
  Scenario: A single case is allocated to the current user
    And I create a single "MIN" case
    When I allocate the case to myself via the successful case creation screen
    And I click to view the "Performance and Process team" workstack
    Then I should own the case

  @Allocation @OtherTests
  Scenario: A single case is allocated to another user
    And I create a single "MPAM" case
    And I go to the case from the successful case creation screen
    When I allocate the case to "CAMERON" on the case details accordion screen
    And I click to view the "MPAM Creation" workstack
    Then the owner field should display "CAMERON"

  @Workflow @Regression @SmokeTests @ignore
  Scenario: I can bulk upload cases
    When I bulk create 40 "MIN" cases
    Then bulk cases are created successfully

  @DCUWorkflow @DCURegression
  Scenario Outline: Newly created DCU cases should move to the Data Input stage
    And I create a single "<caseType>" case and return to the dashboard
    Then the case should be moved to the "Data Input" stage
    Examples:
      | caseType |
      | MIN      |
      | DTEN     |
      | TRO      |

  @UKVIWorkflow @UKVIRegression
  Scenario: Newly created MPAM cases should move to the Creation stage
    And I create a single "MPAM" case and return to the dashboard
    Then the case should be moved to the "Creation" stage

  @UKVIWorkflow @UKVIRegression
  Scenario: Newly created MTS cases should move to the Data Input stage
    And I create a single "MTS" case and return to the dashboard
    Then the case should be moved to the "Data Input" stage

  @Navigation
  Scenario: User should be taken back to the dashboard when they click the cancel link on the first 'Create Single Case' page
    And I click the "Cancel" link
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