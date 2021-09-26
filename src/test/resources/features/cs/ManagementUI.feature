@ManagementUI @CS
Feature: ManagementUI

  Background:
    Given I am logged into "CS Management UI" as user "DECS_USER"

  @Navigation
  Scenario Outline: User navigates to a management page
    When I navigate to the "<pageType>" Management page
    Then I should be taken to the "<pageType>" Management page
    Examples:
      | pageType            |
      | ADD A STANDARD LINE |
      | TEAM                |
      | ADD CHILD TOPIC     |
      | LINK TOPIC TO TEAM  |
      | ADD A UNIT          |
      | VIEW UNITS          |

  @TeamManagement
  Scenario: User can not see any assigned users if team does not have any
    And I navigate to the "TEAM" Management page
    When I search for a team with no assigned users
    Then no users should be shown in user list

  @TeamManagement @CSRegression
  Scenario: Users can be added and removed from teams in CS Management UI
    And I navigate to the "TEAM" Management page
    When I select the "UK Central Authority" team from the dropdown
    And I add the user "TEST_USER_1" to the team
    Then the user "should" be visible in the team list
    When I remove the user "TEST_USER_1" from the team
    Then the user "should not" be visible in the team list

  @TeamManagement
  Scenario: User can add multiple users to a team
    And I navigate to the "TEAM" Management page
    When I select the "Animals in Science Regulation Unit" team from the dropdown
    And I add the users "CAMERON" and "CASEY" to the team
    Then the users should be visible in the team list

  @TeamManagement @Validation
  Scenario: User should see an error when attempting to remove user from team that they currently have assigned cases in
    And I navigate to the "TEAM" Management page
    When I select the "ANIMALS IN SCIENCE REGULATION UNIT" team from the dropdown
    And I attempt to remove the user "DECS_USER"
    Then an error message should be displayed as they have cases assigned in that team

  @TeamManagement @Validation
  Scenario: User must select a team from the dropdown on the team search page
    And I navigate to the "TEAM" Management page
    When I click the view team button
    Then an error message should displayed as no team been selected

  @TeamManagement @Validation
  Scenario: User must select at least one user on the add users page
    And I navigate to the "TEAM" Management page
    When I select the "ANIMALS IN SCIENCE REGULATION UNIT" team from the dropdown
    And I click the Add Selected Users button
    Then an error message should be displayed as no users have been selected

  @AddStandardLine @DCURegression
  Scenario: User can add a new Standard Line
    And I navigate to the "ADD A STANDARD LINE" Management page
    When I add a new Standard Line with "Animal alternatives (3Rs)" as the topic
    Then the Standard Line should be added to the selected topic

  @AddStandardLine @Validation
  Scenario: User must enter an expiration date in the future when creating a Standard Line
    And I navigate to the "ADD A STANDARD LINE" Management page
    When I enter a Standard Line expiration date in the past
    Then an error message should be displayed as the expiration date must be in the future

  @AddStandardLine @Validation
  Scenario: User must select a topic, add a document and enter an expiration date when creating a Standard Line
    And I navigate to the "ADD A STANDARD LINE" Management page
    When I click the "Submit" button
    Then an error message should be displayed as all Standard Line information has not been added

  @ManageStandardLines
  Scenario: User is able to filter the standard lines on the manage standard lines screen
    And I navigate to the "Manage Standard Lines" Management page
    And I enter "Animal" into the standard line filter
    Then the standard lines in the "Topic" column should contain "Animal"

  @ManageStandardLines @DCURegression
  Scenario: User is able to amend the expiry date of a standard line
    And I navigate to the "Manage Standard Lines" Management page
    And I amend the expiry date of the "Animal alternatives (3Rs)" standard line to 5 days from today
    Then the standard line expiry date has been correctly amended

  @ManageStandardLines @DCURegression
  Scenario: User is able to delete a standard line
    And I navigate to the "ADD A STANDARD LINE" Management page
    And I add a new Standard Line with "101 non-emergency number (cost)" as the topic
    Then the standard line "is" visible
    When I select the "Delete" action for the "101 non-emergency number (cost)" standard line
    And I select the checkbox to include expired standard lines
    Then the standard line "isn't" visible

  @ManageStandardLines @DCURegression
  Scenario: User is able to expire a standard line
    And I navigate to the "Add a Standard Line" Management page
    And I add a new Standard Line with "101 non-emergency number (cost)" as the topic
    When I select the "Expire" action for the "101 non-emergency number (cost)" standard line
    And the standard line "isn't" visible
    And I select the checkbox to include expired standard lines
    Then the standard line "is" visible

  @UnitManagement @Validation
  Scenario: User must enter a display name and short code on the add unit page
    And I navigate to the "ADD A UNIT" Management page
    When I click the "Submit" button
    Then an error message should be displayed as they have not entered a display name and short code

  @UnitManagement
  Scenario: User can submit a new Unit
    And I navigate to the "ADD A UNIT" Management page
    And I enter a "NEW" Display Name
    And I enter a "NEW" Short Code
    When I click the "Submit" button
    Then I am returned to the dashboard screen
    And a success message is displayed

  @UnitManagement @Validation
  Scenario: User cannot submit a duplicate Unit
    And I navigate to the "ADD A UNIT" Management page
    And I enter a "NEW" Display Name
    And I enter a "NEW" Short Code
    And I click the "Submit" button
    And I navigate to the "ADD A UNIT" Management page
    And I enter a "Duplicate" Display Name
    And I enter a "Duplicate" Short Code
    And I click the "Submit" button
    Then an error message should be displayed a unit with those details already exists

  @UnitManagement
  Scenario: User can view a list of units
    When I navigate to the "VIEW UNITS" Management page
    Then a list of units should be displayed

  @UnitManagement @Validation
  Scenario: User can view a unit they create in the list of units
    And I navigate to the "ADD A UNIT" Management page
    And I enter a "NEW" Display Name
    And I enter a "NEW" Short Code
    And I click the "Submit" button
    When I navigate to the "VIEW UNITS" Management page
    Then the previously created unit should be listed

  @AddChildTopic @Validation
  Scenario: User must select a parent topic on the Add Child Topic page
    And I navigate to the "ADD CHILD TOPIC" Management page
    When I enter a display name
    And I click the "Submit" button
    Then an error message should be displayed as no parent topic has been selected

  @AddChildTopic @Validation
  Scenario: User must enter a display name on the Add Child Topic page
    And I navigate to the "ADD CHILD TOPIC" Management page
    When I select a parent topic
    And I click the "Submit" button
    Then an error message should be displayed as no display name has been entered

  @AddChildTopic
  Scenario: User can submit a new child topic
    And I navigate to the "ADD CHILD TOPIC" Management page
    And I select a parent topic
    And I enter a display name
    Then I am returned to the dashboard screen
    And a success message is displayed

  @AddChildTopic @Validation
  Scenario: User cannot create a child topic with the same parent topic and display name as one that already exists
    And I navigate to the "ADD CHILD TOPIC" Management page
    And I enter a parent topic and display name that duplicate an existing child topic
    When I click the "Submit" button
    Then an error message should be displayed stating that topic already exists

  @AddChildTopic
  Scenario: User can use the same display name for two different child topics if the parent topics are different
    And I have created a new child topic
    And I navigate to the "ADD CHILD TOPIC" Management page
    And I select a different parent topic
    And I enter the same display name
    Then I am returned to the dashboard screen
    And a success message is displayed

  @AddChildTopic @LinkTopicToTeam @DCURegression
  Scenario: User can create a new child topic in Management UI and assign that topic to a case during Markup stage in CS
    And I have created a new child topic
    And I have linked teams to the new child topic
    And I navigate to "CS"
    And I create a "MIN" case and move it to the "Markup" stage
    And I progress the case to the point of adding a topic
    When I add the new child topic
    Then the topic can be viewed in the case timeline

  @AddChildTopic @Validation
  Scenario: User cannot select a new child topic in HOCS if it has not had team links assigned in Management UI
    And I have created a new child topic
    And I navigate to "CS"
    And I create a "MIN" case and move it to the "Markup" stage
    And I progress the case to the point of adding a topic
    When I add the new child topic
    Then an error message should be displayed as the topic was not recognised as a valid topic

  @LinkTopicToTeam
  Scenario: User can view a summary of the topic and teams before final submission
    Given I have created a new child topic
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic that "DOES NOT" have linked teams
    And I click the "Submit" button
    And I select a "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I select a "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team
    When I click the "Submit" button
    Then the summary should correctly detail the topic and the teams chosen to link to it

  @LinkTopicToTeam
  Scenario: User can choose and submit teams to amend the links of a topic
    Given I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic that "DOES" have linked teams
    And I click the "Submit" button
    And I select a "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I select a "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team
    When I click the "Submit" button
    And I click the "Submit" button
    Then I am returned to the dashboard screen
    And a success message is displayed

  @LinkTopicToTeam @Validation
  Scenario: User must select a topic on the topic search page for linking team to topic
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    When I click the "Submit" button
    Then an error message should be displayed as no topic has been selected

  @LinkTopicToTeam @Validation
  Scenario: User must select a 'Initial Draft and QA response stages' team to assign topic to
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic that "DOES" have linked teams
    And I click the "Submit" button
    And I select a "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team
    When I click the "Submit" button
    Then an error message should be displayed as no "INITIAL DRAFT AND QA RESPONSE STAGES" team has been selected

  @LinkTopicToTeam @Validation
  Scenario: User must select a 'Private Office/Ministerial Sign Off stages' team to assign topic to
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic that "DOES" have linked teams
    And I click the "Submit" button
    And I select a "INITIAL DRAFT AND QA RESPONSE STAGES" team
    When I click the "Submit" button
    Then an error message should be displayed as no "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team has been selected

  @LinkTopicToTeam @DCURegression
  Scenario: Teams linked to new child topic in Management UI are displayed as default teams in HOCS for that topic
    And I have created a new child topic
    And I have linked teams to the new child topic
    And I navigate to "CS"
    And I create a single "MIN" case and return to the dashboard
    And I complete the "Data Input" stage
    When I assign the Topic "NEW CHILD TOPIC"
    Then the case should be assigned to the "NEW DRAFTING AND QA TEAM" for drafting
    And the case should be assigned to the "NEW PRIVATE AND MINISTERIAL TEAM" for approval

  @LinkTopicToTeam
  Scenario: A topic with existing team links can have those links amended in Management UI
    Given I navigate to "CS"
    And I discover the current default team links for a topic
    And I navigate to "CS Management UI"
    And I select to amend the team links for the topic
    And I select a different "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I select a different "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team
    And I click the "Submit" button
    And I click the "Submit" button
    When I check the default team links in CS again
    Then the case should be assigned to the "NEW DRAFTING AND QA TEAM" for drafting
    And the case should be assigned to the "NEW PRIVATE AND MINISTERIAL TEAM" for approval

  @UserManagement
  Scenario: A user can check the teams a user is in through User Management
    Given I navigate to the "User Management" Management page
    And I load the teams of which "Cameron" is a member
    Then the teams the user is a part of are displayed

  @UserManagement @CSRegression
  Scenario: A user can be added and removed from a team through User Management
    Given I navigate to the "User Management" Management page
    And I load the teams of which "Cameron" is a member
    And I add the user to the "OSCT Secretariat" team
    Then the success ribbon should be displayed once the user is added
    And the team should be visible in the users list of teams
    And I remove the user from the "OSCT Secretariat" team
    Then the team should be removed from the users list of teams

  @CampaignManagement
  Scenario: User is able to add a Campaign through Campaign management
    Given I navigate to the "Campaign Management" Management page
    And I add a Campaign with random name and campaign code
    Then the new Campaign has been added to the list of Campaigns

  @CampaignManagement @UKVIRegression2
  Scenario: User is able to amend the details of a Campaign through Campaign Management
    Given I navigate to the "Campaign Management" Management page
    And I add a Campaign with random name and campaign code
    And I navigate to the "Campaign Management" Management page
    And I edit a Campaign name
    Then the Campaign name should have changed in the list of Campaigns

  @CampaignManagement @UKVIRegression2
  Scenario: User can add a case to a new Campaign that was added through Campaign management
    Given I navigate to the "Campaign Management" Management page
    And I add a Campaign with random name and campaign code
    And I navigate to "CS"
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I add the case to the new campaign
    And I load the current case
    Then the case is added to the correct Campaign

  @TeamManagement
  Scenario: User can create a DCU drafting team through team management
    Given I navigate to the "Create DCU Drafting Team" Management page
    And I create a new DCU drafting team
    Then the success message for team "Creation" should be displayed
    And I navigate to "CS Management UI"
    And I navigate to the "Team" Management page
    And I load the "created" DCU Drafting team through team management
    Then the "created" DCU Drafting team is displayed

  @TeamManagement @CSRegression
  Scenario: User is able to rename teams through team management
    Given I navigate to the "Create DCU Drafting Team" Management page
    And I create a new DCU drafting team
    And I navigate to "CS Management UI"
    And I navigate to the "Team" Management page
    And I edit the name of the created DCU drafting team
    Then the success message for team "Rename" should be displayed
    And I navigate to "CS Management UI"
    And I navigate to the "Team" Management page
    And I load the "renamed" DCU Drafting team through team management
    Then the "renamed" DCU Drafting team is displayed

  @TeamManagement @CSRegression
  Scenario: User is able to assign users to a DCU drafting team created through team management
    Given I navigate to the "Create DCU Drafting Team" Management page
    And I create a new DCU drafting team
    And I navigate to "CS Management UI"
    And I navigate to the "Team" Management page
    And I load the "created" DCU Drafting team through team management
    And I add the user "DCU_USER" to the team
    Then the user "should" be visible in the team list

  @TeamManagement
  Scenario: User is able to assign cases to a DCU drafting team created through team management
    Given I navigate to the "Create DCU Drafting Team" Management page
    And I create a new DCU drafting team
    And I navigate to "CS"
    And I create a "MIN" case with "Cyber Stalking And Harassment" as the primary topic
    And I override the initial draft team of the case to the team created in Management UI
    And I load the current case
    Then the case should be assigned to the DCU draft team created in Management UI

  @TeamManagement @CSRegression
  Scenario: User is able to move a team to a different unit
    Given I navigate to the "Team" Management page
    When I select the "Detention Services" team from the dropdown
    And I navigate to edit team
    And I change to a different unit
    Then success message is displayed

  @TeamManagement
  Scenario: User is able to deactivate and reactivate a team through Team Management
    And I navigate to the "Create DCU Drafting Team" Management page
    And I create a new DCU drafting team
    And I navigate to "CS Management UI"
    And I navigate to the "Team" Management page
    And I load the "created" DCU Drafting team through team management
    And I "Deactivate" the team in team management
    Then the team should be displayed as "Inactive" in team management
    And a message should be displayed stating that the team has been successfully "Deactivated"
    And I "Reactivate" the team in team management
    Then the team should be displayed as "Active" in team management
    And a message should be displayed stating that the team has been successfully "Reactivated"

  @TeamManagement
  Scenario: User is able to load deactivated teams from the teams typeahead in team management
    When I navigate to the "Create DCU Drafting Team" Management page
    And I create a new DCU drafting team
    And I navigate to "CS Management UI"
    And I navigate to the "Team" Management page
    And I load the "created" DCU Drafting team through team management
    And I "Deactivate" the team in team management
    And I navigate to "CS Management UI"
    And I navigate to the "Team" Management page
    And I select to include deactivated teams in teams typeahead
    And I load the "deactivated" DCU Drafting team through team management
    Then the deactivated team should be displayed