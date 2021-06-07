@CreateCase @DECS
Feature: Create case

  Background:
    Given I am logged into "DECS" as user "DECS_USER"
    When I navigate to the "CREATE SINGLE CASE" page

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
#      | COMP | with           |
#      | COMP | without        |

  @Allocation @OtherTests
  Scenario: A single case is allocated to the current user
    And I create a single "ANY" case
    When I allocate the case to myself via the successful case creation screen
    And I click to view the "Summary" tab
    Then the case "Should" be allocated to me in the summary


  @Allocation @OtherTests
  Scenario: A single case is allocated to another user
    And I create a single "ANY" case
    And I go to the case from the successful case creation screen
    When I allocate the case to another user on the case details accordion screen
    And I load the current case
    And I click to view the "Summary" tab
    Then the case should be allocated to the previously selected user in the summary

  @Workflow @Regression @SmokeTests
  Scenario: I can bulk upload cases
    When I bulk create 40 "MIN" cases
    Then bulk cases are created successfully

  @Workflow @Regression
  Scenario Outline: Newly created cases should move to the correct first stage of the workflow
    And I create a single "<caseType>" case and return to the dashboard
    Then the case should be moved to the "<stage>" stage
    Examples:
      | caseType | stage        |
      | MIN      | Data Input   |
      | DTEN     | Data Input   |
      | TRO      | Data Input   |
      | MPAM     | Creation     |
      | MTS      | Data Input   |
#      | COMP     | Registration |

  @Navigation
  Scenario: User should be taken back to the dashboard when they click the cancel link on the first 'Create Single Case' page
    And I click the "Cancel" link
    Then I should be taken to the homepage

  @Validation
  Scenario: When creating a Single Case case type selection is required
    And I click the "Next" button
    Then an error message should be displayed as I have not selected the case type

  @Validation
  Scenario: When creating a single case the date received is required
    And I select "Any" case type and continue
    And I enter a blank date
    And I click the "Create case" button
    Then an error message should be displayed as I have not entered the correspondence received date

  @Validation
  Scenario: When creating a Single MIN case a valid date must be entered
    And I select "Any" case type and continue
    And I enter an invalid date
    And I click the "Create case" button
    Then an error message should be displayed as I have entered an invalid date