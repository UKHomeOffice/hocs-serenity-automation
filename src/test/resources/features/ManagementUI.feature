Feature: User manages HOCS teams, topics and units

  Background:
    Given that I have navigated to the Management UI as the user "EAMON"

  Scenario Outline: User navigates to a management page
    When I navigate to the "<pageType>" Management page
    Then I should be taken to the "<pageType>" Management page
    Examples:
      | pageType           |
      | STANDARD LINE      |
      | TEAM               |
      | ADD CHILD TOPIC    |
      | LINK TOPIC TO TEAM |
#      | ADD A UNIT         |
#      | VIEW UNITS         |

  @HOCS-832
  Scenario: User can not see any assigned users if team does not have any
    And I navigate to the "TEAM" Management page
    When I search for a team with no assigned users
    Then no users should be shown in user list

  @HOCS-832
  Scenario: Adding a new user to a team displays that user in the team list
    And I navigate to the "TEAM" Management page
    When I select the "Pursue Disruptions Unit" team from the dropdown
    And I add the user "eamon.droko@ten10.com" to the team
    Then the user should be visible in the team list

  Scenario: User can add multiple users to a team
    And I navigate to the "TEAM" Management page
    When I select the "OSCT Secretariat" team from the dropdown
    And I add the users "eamon.droko@ten10.com" and "danny.large@ten10.com" to the team
    Then the users should visible in the team list

  @HOCS-832
  Scenario: Users should no longer be visible in team page when removed
    And I navigate to the "TEAM" Management page
    When I select the "Pursue Disruptions Unit" team from the dropdown
    And I remove a user from the team
    Then that user should no longer appear in the list of team members

  @HOCS-832
  Scenario: User should see an error when attempting to remove user from team that they currently have assigned cases in
    And I navigate to the "TEAM" Management page
    When I select the "ANIMALS IN SCIENCE REGULATION UNIT" team from the dropdown
    And I attempt to remove the user "eamon.droko@ten10.com"
    Then an error message should be displayed as they have cases assigned in that team

  @Validation
  Scenario: User must select a topic, add a document and enter an expiration date when creating a Standard Line
    And I navigate to the "STANDARD LINE" Management page
    When I click the "SUBMIT" button
    Then an error message should be displayed as all Standard Line information has not been added

  @Validation
  Scenario: User must select a team from the dropdown on the team search page
    And I navigate to the "TEAM" Management page
    When I click the "VIEW TEAM" button
    Then an error message should displayed as no team been selected

  @Validation
  Scenario: User must select at least one user on the add users page
    And I navigate to the "TEAM" Management page
    When I select the "ANIMALS IN SCIENCE REGULATION UNIT" team from the dropdown
    And I click the Add Selected Users button
    Then an error message should be displayed as no users have been selected

  @Validation
  Scenario: User must enter a display name and short code on the add unit page
    And I navigate to the "ADD A UNIT" Management page
    When I click the "SUBMIT" button
    Then an error message should be displayed as they have not entered a display name and short code
    
  @HOCS-1130 @LinkTopicToTeam
  Scenario: User can choose and submit teams to link to a new topic
    Given I have created a new child topic
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic that "DOES NOT" have linked teams
    And I click the "SUBMIT" button
    And I select a "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I select a "PRIVATE OFFICE/MINISTER SIGN OFF STAGES" team
    And I click the "SUBMIT" button
    Then the summary should correctly detail the topic and the teams chosen to link to it

  @HOCS-1130 @LinkTopicToTeam
  Scenario: User can choose and submit teams to amend the links of a topic
    Given I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic that "DOES" have linked teams
    And I click the "SUBMIT" button
    And I select a "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I select a "PRIVATE OFFICE/MINISTER SIGN OFF STAGES" team
    And I click the "SUBMIT" button
    Then the summary should correctly detail the topic and the teams chosen to link to it

  @HOCS-1130 @LinkTopicToTeam @Validation
  Scenario: User must select a topic on the topic search page for linking team to topic
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    When I click the "SUBMIT" button
    Then an error message should be displayed as no topic has been selected

  @HOCS-1130 @LinkTopicToTeam @Validation
  Scenario: User must select a 'Initial Draft and QA response stages' team to assign topic to
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic that "DOES" have linked teams
    And I click the "SUBMIT" button
    And I select a "PRIVATE OFFICE/MINISTER SIGN OFF STAGES" team
    When I click the "SUBMIT" button
    Then an error message should be displayed as no "INITIAL DRAFT AND QA RESPONSE STAGES" team has been selected

  @HOCS-1130 @LinkTopicToTeam @Validation
#    Currently has incorrect error message, test will need updating when correct error message implemented
  Scenario: User must select a 'Private Office/Minister sign off stages' team to assign topic to
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic that "DOES" have linked teams
    And I click the "SUBMIT" button
    And I select a "INITIAL DRAFT AND QA RESPONSE STAGES" team
    When I click the "SUBMIT" button
    Then an error message should be displayed as no "PRIVATE OFFICE/MINISTER SIGN OFF STAGES" team has been selected

  @HOCS-1130 @LinkTopicToTeam
  Scenario: Teams linked to new child topic in Management UI are displayed as default teams in HOCS for that topic
    Given I have linked teams to a new child topic in Management UI
    And I navigate to "HOCS"
    And I create a single case "DCU MIN"
    And the Data Input Stage is completed for "DCU MIN" caseType
    When I assign the Topic "NEW CHILD TOPIC"
    Then the case should be assigned to the "NEW DRAFTING AND QA TEAM" for drafting
    And the case should be assigned to the "NEW PRIVATE AND MINISTERIAL TEAM" for approval

  @HOCS-1130 @LinkTopicToTeam
  Scenario: A topic with existing team links can have those links amended in Management UI
    Given I navigate to "HOCS"
    And I discover the current default team links for a topic
    And I navigate to "Management UI"
    And I select to amend the team links for the topic
    And I select a different "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I select a different "PRIVATE OFFICE/MINISTER SIGN OFF STAGES" team
    And I click the "SUBMIT" button
    And I click the "SUBMIT" button
    When I check the default team links in HOCS again
    Then the case should be assigned to the "NEW DRAFTING AND QA TEAM" for drafting
    And the case should be assigned to the "NEW PRIVATE AND MINISTERIAL TEAM" for approval







