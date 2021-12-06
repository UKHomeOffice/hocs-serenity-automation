@CreateCase @CS
Feature: Create case

  Background:
    Given I am logged into "CS" as user "DECS_USER"
    When I navigate to the "CREATE SINGLE CASE" page

  @Workflow @CSRegression
  Scenario Outline: As a CS user, I want to be able to create a case, so I can start the casework process for the received correspondence
    And I create a single "<caseType>" case
    Then the case should be moved to the "<stage>" stage
    And the summary should display the owning team as "<team>"
    And the document added at case creation should be listed under the "<documentType>" document type heading
    Examples:
      | caseType | stage                | documentType            | team                          |
      | MIN      | Data Input           | ORIGINAL                | Performance and Process Team  |
      | DTEN     | Data Input           | ORIGINAL                | Transfers & No10 Team         |
      | TRO      | Data Input           | ORIGINAL                | Performance and Process Team  |
      | MPAM     | Creation             | Original correspondence | MPAM Creation                 |
      | MTS      | Data Input           | Original correspondence | MTS Team                      |
      | COMP     | Registration         | To document             | Complaint Registration        |
      | COMP2    | Stage 2 Registration | To document             | Stage 2 Complaint Registration |
      | IEDET    | Registration         | To document             | IE Detention                  |
      | SMC      | Registration         | To document             | Serious Misconduct            |
      | FOI      | Case Creation        | Request                 | FOI Creation                  |
#      | BF       | Registration         |                         | Border Force                  |
#      | TO       | Data Input           |                         | Treat Official Creation       |

  @Allocation
  Scenario: A single case is allocated to the current user
    And I create a single "CS" case
    When I allocate the case to myself via the successful case creation screen
    Then the case "should" be allocated to me in the summary

  @Allocation
  Scenario: A single case is allocated to another user
    And I create a single "CS" case
    And I go to the case from the successful case creation screen
    When I allocate the case to another user on the case details accordion screen
    And I load the current case
    Then the case should be allocated to the previously selected user in the summary

  @Workflow @CSRegression @SmokeTests
  Scenario: I can bulk upload cases
    When I bulk create 40 "MIN" cases
    Then bulk cases are created successfully

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
    And I select a case type and continue
    And I enter a blank date
    And I click the "Create case" button
    Then an error message should be displayed as I have not entered the correspondence received date

  @Validation
  Scenario: When creating a Single MIN case a valid date must be entered
    And I select a case type and continue
    And I enter an invalid date
    And I click the "Create case" button
    Then an error message should be displayed as I have entered an invalid date