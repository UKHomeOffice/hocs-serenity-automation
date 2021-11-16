@ManagementUI @WCS
Feature: WCS Management UI

  @Navigation
  Scenario: User navigates to a management page
    Given I am logged into "WCS Management UI" as user "WCS_USER"
    When I select to "Manage a team"
    Then the "Team search" management page should be displayed

  @TeamManagement @WCSRegression
  Scenario: Users can be added and removed from teams in WCS Management UI
    Given I am logged into "WCS Management UI" as user "WCS_USER"
    When I select to "Manage a team"
    When I select the "Initial Consideration Casework" team from the dropdown
    And I add the user "TEST_USER_1" to the team
    Then the user "should" be visible in the team list
    When I remove the user "TEST_USER_1" from the team
    Then the user "should not" be visible in the team list

  @Validation
  Scenario: User should see an error when attempting to remove user from a team that they currently have assigned cases in
    Given I am logged into "WCS Management UI" as user "WCS_USER"
    When I select to "Manage a team"
    When I select the "WCS Registration Team" team from the dropdown
    And I attempt to remove the user "WCS_USER"
    Then an error message should be displayed as they have claims assigned in that team

  @WCSRegression
  Scenario: User withdraws a claim
    Given I am logged into "WCS" as user "WCS_USER"
    And I create a single "WCS" claim
    And I navigate to "WCS Management UI"
    When I select to "Withdraw a case"
    And I enter the reference, a valid withdrawal date and text into the note field
    When I click the "Withdraw" button
    And I navigate to "WCS"
    Then the claim should be closed
    And a Case withdrawn note should be visible showing the submitted notes on the withdrawal of the case
