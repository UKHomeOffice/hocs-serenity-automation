@Creation
Feature: Creation

  Background:
    Given I log in to DECS
    And I create a "UKVI" case and move it to the "Creation" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the UKVI Data Input Page
    Then the "UKVI Data Input" page should be displayed

  @Navigation
  Scenario: User should be on the UKVI Correspondents Details Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    Then the "UKVI Correspondents Details" page should be displayed

  @Navigation
  Scenario: User should be on the Add Correspondent Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    And I click the "Add a correspondent" link
    Then the "Add Correspondent" page should be displayed

  @Navigation
  Scenario: User should be on the Add Member of Parliament Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    And I select to add a correspondent that "is" a member of parliament
    Then the "Add member of parliament" page should be displayed

  @Navigation
  Scenario: User should be on the Record Correspondent Details Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    And I select to add a correspondent that "is not" a member of parliament
    Then the "Record Correspondent Details" page should be displayed

  @Workflow
  Scenario Outline: User completes Case Creation stage with specific Business Area and Reference Type
    When I select "<businessArea>" as the Business Area and "<refType>" as the Reference Type
    And I complete the other required fields for Creation stage
    And I click the "Continue" button
    And I add a public correspondent
    And I click the "Move to Triage" button
    Then the case should be moved to the "Triage" stage
    Examples:
      | businessArea | refType |
      | UKVI         | M:Ref   |
      | BF           | M:Ref   |
      | IE           | M:Ref   |
      | EUSS         | M:Ref   |
      | HMPO         | M:Ref   |
      | Windrush     | M:Ref   |
#      | Coronavirus  | M:Ref   |
      | UKVI         | B:Ref   |
      | BF           | B:Ref   |
      | IE           | B:Ref   |
      | EUSS         | B:Ref   |
      | HMPO         | B:Ref   |
      | Windrush     | B:Ref   |
#      | Coronavirus  | B:Ref   |

  Scenario: User adds an MP correspondent at Case Creation stage
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    When I select to add a correspondent that "is" a member of parliament
    And I add the member of parliament "Nicola Sturgeon MSP"
    Then the submitted correspondent should be visible in the list of correspondents

  Scenario: User adds a member of public correspondent at Case Creation stage
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    When I select to add a correspondent that "is not" a member of parliament
    And I fill all mandatory fields on the "CORRESPONDENT DETAILS" page with valid data
    Then the submitted correspondent should be visible in the list of correspondents

  Scenario: User removes the primary correspondent
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    When I add a public correspondent
    And I remove the primary correspondent
    Then there shouldn't be a primary correspondent displayed

  Scenario: User edits an existing correspondent
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    When I add a public correspondent
    And I edit the primary correspondents name
    Then the correspondents name should be updated

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