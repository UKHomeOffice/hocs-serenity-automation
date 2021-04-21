@Creation @UKVI
Feature: Creation

  Background:
    Given I log in to "DECS" as user "UKVI_USER"
    And I create a "MPAM" case and move it to the "Creation" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the MPAM Data Input Page
    Then the "MPAM Data Input" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User can navigate to the MPAM Correspondents Details Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    Then the "MPAM Correspondents Details" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User can navigate to the Add Correspondent Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    And I click the "Add a correspondent" link
    Then the "Add Correspondent" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User can navigate to the Add Member of Parliament Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    And I select to add a correspondent that "is" a member of parliament
    Then the "Add member of parliament" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User can navigate to the Record Correspondent Details Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    And I select to add a correspondent that "is not" a member of parliament
    Then the "Record Correspondent Details" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @UKVIWorkflow @UKVIRegression
  Scenario: User completes Case Creation stage
    When I select "UKVI" as the Business Area and "Ministerial" as the Reference Type
    And I complete the other required fields for Creation stage
    And I click the "Continue" button
    And I select to add a correspondent that "is" a member of parliament
    And I add the member of parliament "Boris Johnson"
    And I click the "Move to Triage" button
    Then the case should be moved to the "Triage" stage

  @UKVIWorkflow
  Scenario Outline: User completes Case Creation stage with specific Business Area and Reference Type
    When I select "<businessArea>" as the Business Area and "<refType>" as the Reference Type
    And I complete the other required fields for Creation stage
    And I click the "Continue" button
    And I select to add a correspondent that "is" a member of parliament
    And I add the member of parliament "Boris Johnson"
    And I click the "Move to Triage" button
    Then the case should be moved to the "Triage" stage
    Examples:
      | businessArea | refType     |
      | UKVI         | Ministerial |
      | BF           | Ministerial |
      | IE           | Ministerial |
      | EUSS         | Ministerial |
      | HMPO         | Ministerial |
      | Windrush     | Ministerial |
      | Coronavirus  | Ministerial |
      | UKVI         | Official    |
      | BF           | Official    |
      | IE           | Official    |
      | EUSS         | Official    |
      | HMPO         | Official    |
      | Windrush     | Official    |
      | Coronavirus  | Official    |

  @UKVIRegression @test
  Scenario: User adds an MP correspondent at Case Creation stage
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    When I select to add a correspondent that "is" a member of parliament
    And I add the member of parliament "Nicola Sturgeon MSP"
    Then the submitted correspondent should be visible in the list of correspondents

  @UKVIRegression
  Scenario: User adds a member of public correspondent at Case Creation stage
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    When I select to add a correspondent that "is not" a member of parliament
    And I fill all mandatory fields on the "CORRESPONDENT DETAILS" page with valid data
    Then the submitted correspondent should be visible in the list of correspondents

  @UKVIRegression
  Scenario: User removes the primary correspondent
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    When I add a public correspondent
    And I remove the primary correspondent
    Then there shouldn't be a primary correspondent displayed

  @UKVIRegression
  Scenario: User edits an existing correspondents name
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    When I add a public correspondent
    And I edit the primary correspondents name
    Then the correspondents name should be updated

  @UKVIRegression
  Scenario: User adds a second correspondent and selects them as the primary correspondent
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    When I add "Nicola Sturgeon" MP as a correspondent
    And I add a public correspondent
    When I select the primary correspondent radio button for a different correspondent
    And I click the "Move to Triage" button
    Then the case summary should list the correct primary correspondent

  @Validation
  Scenario: User attempts to progress without answering required questions
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    And I click the "Move to Triage" button
    Then an error message should be displayed as I must enter a Primary Correspondent at Creation stage

  @Validation
  Scenario: User attempts to progress without adding a correspondent
    And I click the "Continue" button
    Then an error message should be displayed as I must complete all required questions at Creation stage

  @OtherTests
  Scenario: User can select a Ministerial Sign off team for the case
    And I select "Home Secretary" as the Ministerial sign off team when completing the creation stage
    And I load the current case
    Then the "Creation" accordion in case details should display the correct information for "Ministerial Sign Off Team"

  @OtherTests
  Scenario: User can select a Ministerial Sign Off team for a case and the selection is visible in a team workstack
    And I select "Home Secretary" as the Ministerial sign off team when completing the creation stage
    And I view the MPAM case in the appropriate "Triage" stage workstack
    Then the Minister sign off team is correctly displayed

  @Validation
  Scenario: User attempts to progress a case without adding an MP correspondent
    And I try to advance a case with a public correspondent at Creation stage
    Then the MP correspondent is mandatory screen is displayed