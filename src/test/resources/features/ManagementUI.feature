Feature: User manages HOCS teams and units
  
  Background: 
    Given that I have navigated to the Management UI as the user "EAMON"

    Scenario: User navigates to a management page
      When I navigate to the "TEAM" Management page
      Then I should be taken to the "TEAM" Management page

    @HOCS-832
    Scenario: User can see all users assigned to the team they have searched for

    @HOCS-832
    Scenario: User can not see any assigned users if team does not have any
      And I navigate to the "TEAM" Management page
      When I search for a team with no assigned users
      Then no users should be shown in user list

    @HOCS-832
    Scenario: Adding a new user to a team displays that user in the team list
      And I navigate to the "TEAM" Management page
      When I select the "Pursue Disruptions Unit" team from the dropdown
      And I add the user "danny.large@ten10.com" to the team
      Then the user should be visible in the team list

    @HOCS-832
    Scenario: Users should no longer be visible in team page when removed
      And I navigate to the "TEAM" Management page
      When I select the "Pursue Disruptions Unit" team from the dropdown
      And I remove a user from the team
      Then that user should no longer appear in the list of team members

    @HOCS-832
    Scenario: User should see an error when attempting to remove user from team that they currently have assigned cases in
      And I navigate to the "TEAM" Management page
      When I attempt to remove a user from a team they have assigned cases in
      Then an error message should be displayed
      And they should remain in the team
