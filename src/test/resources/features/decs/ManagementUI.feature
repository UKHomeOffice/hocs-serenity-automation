@ManagementUI @DECS
Feature: ManagementUI

  Background:
    Given that I have navigated to the Management UI as the designated user

  Scenario Outline: User navigates to a management page
    When I navigate to the "<pageType>" Management page
    Then I should be taken to the "<pageType>" Management page
    Examples:
      | pageType           |
      | STANDARD LINE      |
      | TEAM               |
      | ADD CHILD TOPIC    |
      | LINK TOPIC TO TEAM |
      | ADD A UNIT         |
      | VIEW UNITS         |

  @TeamManagement @OtherTests
  Scenario: User can not see any assigned users if team does not have any
    And I navigate to the "TEAM" Management page
    When I search for a team with no assigned users
    Then no users should be shown in user list

  @TeamManagement @OtherTests
  Scenario: Adding a new user to a team displays that user in the team list
    And I navigate to the "TEAM" Management page
    When I select the "UK Central Authority" team from the dropdown
    And I add the user "CAMERON" to the team
    Then the user should be visible in the team list

  @TeamManagement @SmokeTests
  Scenario: User can add multiple users to a team
    And I navigate to the "TEAM" Management page
    When I select the "OSCT Secretariat" team from the dropdown
    And I add the users "CAMERON" and "CASEY" to the team
    Then the users should be visible in the team list

  @TeamManagement @SmokeTests
  Scenario: Users should no longer be visible in team page when removed
    And I navigate to the "TEAM" Management page
    When I select the "UK Central Authority" team from the dropdown
    And I remove the user "CAMERON" from the team
    Then that user should no longer appear in the list of team members

  @TeamManagement @Validation
  Scenario: User should see an error when attempting to remove user from team that they currently have assigned cases in
    And I navigate to the "TEAM" Management page
    When I select the "ANIMALS IN SCIENCE REGULATION UNIT" team from the dropdown
    And I attempt to remove the user "AUTOMATION_USER"
    Then an error message should be displayed as they have cases assigned in that team

  @TeamManagement @Validation
  Scenario: User must select a team from the dropdown on the team search page
    And I navigate to the "TEAM" Management page
    When I click the "View Team" button
    Then an error message should displayed as no team been selected

  @TeamManagement @Validation
  Scenario: User must select at least one user on the add users page
    And I navigate to the "TEAM" Management page
    When I select the "ANIMALS IN SCIENCE REGULATION UNIT" team from the dropdown
    And I click the Add Selected Users button
    Then an error message should be displayed as no users have been selected

  @AddStandardLine @SmokeTests
  Scenario: User can add a new Standard Line
    And I navigate to the "STANDARD LINE" Management page
    When I add a new Standard Line
    Then the Standard Line should be added to the selected topic

  @AddStandardLine @Validation
  Scenario: User must enter an expiration date in the future when creating a Standard Line
    And I navigate to the "STANDARD LINE" Management page
    When I enter a Standard Line expiration date in the past
    Then an error message should be displayed as the expiration date must be in the future

  @AddStandardLine @Validation
  Scenario: User must select a topic, add a document and enter an expiration date when creating a Standard Line
    And I navigate to the "STANDARD LINE" Management page
    When I click the "Submit" button
    Then an error message should be displayed as all Standard Line information has not been added

  @UnitManagement @Validation
  Scenario: User must enter a display name and short code on the add unit page
    And I navigate to the "ADD A UNIT" Management page
    When I click the "Submit" button
    Then an error message should be displayed as they have not entered a display name and short code

  @UnitManagement @OtherTests
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

  @UnitManagement @OtherTests
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

  @AddChildTopic @OtherTests
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

  @AddChildTopic @OtherTests
  Scenario: User can use the same display name for two different child topics if the parent topics are different
    And I have created a new child topic
    And I navigate to the "ADD CHILD TOPIC" Management page
    And I select a different parent topic
    And I enter the same display name
    Then I am returned to the dashboard screen
    And a success message is displayed

  @AddChildTopic @LinkTopicToTeam @SmokeTests
  Scenario: User can create a new child topic in Management UI and assign that topic to a case during Markup stage in HOCS
    And I have created a new child topic
    And I have linked teams to the new child topic
    And I navigate to "HOCS"
    And I get a case and progress to the point of adding a topic
    When I add the topic "NEW CHILD TOPIC"
    Then the topic can be viewed in the case timeline

  @AddChildTopic @Validation
  Scenario: User cannot select a new child topic in HOCS if it has not had team links assigned in Management UI
    And I have created a new child topic
    And I navigate to "HOCS"
    And I get a case and progress to the point of adding a topic
    When I add the topic "NEW CHILD TOPIC"
    Then an error message should be displayed as the topic was not recognised as a valid topic

  @LinkTopicToTeam @OtherTests
  Scenario: User can view a summary of the topic and teams before final submission
    Given I have created a new child topic
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic that "DOES NOT" have linked teams
    And I click the "Submit" button
    And I select a "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I select a "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team
    When I click the "Submit" button
    Then the summary should correctly detail the topic and the teams chosen to link to it

  @LinkTopicToTeam @OtherTests
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
  Scenario: User must select a 'Private Office/MINISTERIAL SIGN OFF stages' team to assign topic to
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic that "DOES" have linked teams
    And I click the "Submit" button
    And I select a "INITIAL DRAFT AND QA RESPONSE STAGES" team
    When I click the "Submit" button
    Then an error message should be displayed as no "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team has been selected

  @LinkTopicToTeam @SmokeTests
  Scenario: Teams linked to new child topic in Management UI are displayed as default teams in HOCS for that topic
    And I have created a new child topic
    And I have linked teams to the new child topic
    And I navigate to "HOCS"
    And I create a single "MIN" case and return to the dashboard
    And I complete the Data Input Stage
    When I assign the Topic "NEW CHILD TOPIC"
    Then the case should be assigned to the "NEW DRAFTING AND QA TEAM" for drafting
    And the case should be assigned to the "NEW PRIVATE AND MINISTERIAL TEAM" for approval

  @LinkTopicToTeam @OtherTests
  Scenario: A topic with existing team links can have those links amended in Management UI
    Given I navigate to "HOCS"
    And I discover the current default team links for a topic
    And I navigate to "Management UI"
    And I select to amend the team links for the topic
    And I select a different "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I select a different "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team
    And I click the "Submit" button
    And I click the "Submit" button
    When I check the default team links in HOCS again
    Then the case should be assigned to the "NEW DRAFTING AND QA TEAM" for drafting
    And the case should be assigned to the "NEW PRIVATE AND MINISTERIAL TEAM" for approval

  @UserManagement
  Scenario: A user can check the teams a user is in through User Management
    Given I navigate to the "User Management" Management page
    And I load the teams of which "CAMERON_HO" is a member
    Then the teams the user is a part of are displayed

  @UserManagement
  Scenario: A user can be added to a team through User Management
    Given I navigate to the "User Management" Management page
    And I load the teams of which "CAMERON_HO" is a member
    And I add the user to the "Animals in Science Regulation Unit" team
    Then the team should be visible in the users list of teams

  @UserManagement
  Scenario: The success ribbon should be displayed upon adding a user to a team
    Given I navigate to the "User Management" Management page
    And I load the teams of which "CAMERON_HO" is a member
    And I add the user to the "Animals in Science Regulation Unit" team
    Then the success ribbon should be displayed once the user is added

  @UserManagement
  Scenario: A user can be removed from a team through User Management
    Given I navigate to the "User Management" Management page
    And I load the teams of which "CAMERON_HO" is a member
    And I add the user to the "Animals in Science Regulation Unit" team
    And I remove the user from the "Animals in Science Regulation Unit" team
    Then the team should be removed from the users list of teams

  @CampaignManagement
  Scenario: User is able to add a Campaign through Campaign management
    Given I navigate to the "Campaign Management" Management page
    And I add a Campaign with random name and campaign code
    Then the new Campaign has been added to the list of Campaigns

  @CampaignManagement
  Scenario: User is able to amend the details of a Campaign through Campaign Management
    Given I navigate to the "Campaign Management" Management page
    And I add a Campaign with random name and campaign code
    And I navigate to the "Campaign Management" Management page
    And I edit a Campaign name
    Then the Campaign name should have changed in the list of Campaigns

  @CampaignManagement
  Scenario: User can add a case to a new Campaign that was added through Campaign management
    Given I navigate to the "Campaign Management" Management page
    And I add a Campaign with random name and campaign code
    And I navigate to "HOCS"
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case
    And I add the case to the new campaign
    And I load the current case
    Then the case is added to the correct Campaign