Feature: User manages HOCS teams and units
  
  Background: 
    Given that I have navigated to the Management UI as the user "EAMON"

    Scenario: User navigates to a management page
      When I navigate to the "TEAM" Management page
      Then I should be taken to the "TEAM" Management page

    @AddUserToTeams
    Scenario: User can see all users assigned to the team they have searched for

    @AddUserToTeams
    Scenario: Adding a new user to a team displays that user in the team list
      And I navigate to the "TEAM" Management page
      When I select the "PURSUE DISRUPTIONS UNIT" team from the dropdown
      And I add the user "EAMON.DROKO@TEN10.COM" to the team
      Then the user "EAMON.DROKO@TEN10.COM" should be visible in the team list
