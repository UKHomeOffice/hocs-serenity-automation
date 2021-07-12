@ManagementUI @WCS
Feature: Management UI

  @Navigation
  Scenario: User navigates to a management page
    Given I am logged into "WCS Management UI" as user "WCS_USER"
    When I navigate to the "TEAM" Management page
    Then I should be taken to the "TEAM" Management page

  @TeamManagement @WCSRegression
  Scenario: Users can be added and removed from teams in Management UI
    Given I am logged into "WCS Management UI" as user "WCS_USER"
    And I navigate to the "TEAM" Management page
    When I select the "Initial Consideration Casework" team from the dropdown
    And I add the user "TEST_USER_1" to the team
    Then the users should be visible in the team list
    When I remove the user "TEST_USER_1" from the team
    Then that user should no longer appear in the list of team members

  @Validation
  Scenario: User should see an error when attempting to remove user from team that they currently have assigned cases in
    Given I am logged into "WCS Management UI" as user "WCS_USER"
    And I navigate to the "TEAM" Management page
    When I select the "WCS Registration Team" team from the dropdown
    And I attempt to remove the user "WCS_USER"
    Then an error message should be displayed as they have claims assigned in that team

  @WCSRegression
  Scenario: User withdraws a claim
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim
    And I navigate to "WCS Management UI"
    And I navigate to the "Withdraw A Case" Management page
    And I enter the reference, a valid withdrawal date and text into the note field
    When I click the "Withdraw" button
    And I navigate to "WCS"
    Then the claim should be closed
    And a case withdrawn note should be visible showing the entered withdrawal reason




