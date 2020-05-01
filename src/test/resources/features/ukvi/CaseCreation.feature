@CaseCreation
Feature: CaseCreation

  Background:
    Given I am user "AUTOMATION_USER"
    And I create a "UKVI" case and move it to the "Case Creation" stage
    And I load and claim the current case

  Scenario: User should be on the UKVI Data Input Page
    Then the "UKVI Data Input" page should be displayed

  Scenario: User adds an MP correspondent at Case Creation stage
    When I select to add a correspondent that "is" a member of parliament
    And I add the member of parliament "Nicola Sturgeon MSP"
    Then the submitted correspondent should be visible in the list of correspondents

  Scenario: User adds a member of public correspondent at Case Creation stage
    When I select to add a correspondent that "is not" a member of parliament
    And I fill all mandatory fields on the "CORRESPONDENT DETAILS" page with valid data
    Then the submitted correspondent should be visible in the list of correspondents

  Scenario: User removes a correspondent

  Scenario: User edits an existing correspondent

  Scenario: User adds a second correspondent and selects them as the primary correspondent


  Scenario: User attempts to progress without answering required questions
    When I complete all required fields for Case Creation
    And I click the "Send to Triage" button
    Then an error message should be displayed as I must enter a Primary Correspondent at Case Creation stage


  Scenario: User attempts to progress without adding a correspondent
    When I add a public correspondent
    And I click the "Send to Triage" button

  @Workflow @SmokeTests
  Scenario Outline: User completed case creation with specific Business Area and Reference Type
    When I add a public correspondent
    And I select "<businessArea>" as the Business Area and "<refType>" as the Reference Type
    And I complete the other required fields for Case Creation
    And I click the "Send to Triage" button
    Then the case should be moved to the "Case Triage" stage
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