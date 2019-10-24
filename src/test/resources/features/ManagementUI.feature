Feature: User manages HOCS teams and units

  Background:
    Given that I have navigated to the Management UI as the user "EAMON"

  Scenario: User navigates to a management page
    When I navigate to the "STANDARD LINE" Management page
    Then I should be taken to the "STANDARD LINE" Management page

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
    And I navigate to the "UNIT" Management page
    When I click the "SUBMIT" button
    Then an error message should be displayed as they have not entered a display name and short code

  Scenario: User can choose and submit teams to link to a new topic
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic that "DOES NOT" have linked teams
    And I click the "SUBMIT" button
    And I select a "INITIAL DRAFT AND QA RESONSE STAGES" team
    And I select a "PRIVATE OFFICE/MINISTER SIGN OFF STAGES" team
    And I click the "SUBMIT" button
    Then the summary should detail the teams chosen to link to the topic

  Scenario: User can choose and submit teams to amend the links of a topic
    And I select a topic that "DOES" have linked teams
    And I click the "SUBMIT" button
    And I select a "INITIAL DRAFT AND QA RESONSE STAGES" team
    And I select a "PRIVATE OFFICE/MINISTER SIGN OFF STAGES" team
    And I click the "SUBMIT" button
    Then the summary should detail the teams chosen to link to the topic

  @Validation
  Scenario: User must select a topic on the topic search page for linking team to topic
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    When I click the "SUBMIT" button
    Then an error message should be displayed as no team has been selected

  @Validation
  Scenario: User must select a 'Initial Draft and QA response stages' team to assign topic to
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic
    And I click the "SUBMIT" button
    And I select a "PRIVATE OFFICE/MINISTER SIGN OFF STAGES" team
    When I click the "SUBMIT" button
    Then an error message should be displayed as no "INITIAL DRAFT AND QA RESONSE STAGES" team has been selected

  @Validation
  Scenario: User must select a 'Private Office/Minister sign off stages' team to assign topic to
    And I navigate to the "LINK TOPIC TO TEAM" Management page
    And I select a topic
    And I click the "SUBMIT" button
    And I select a "INITIAL DRAFT AND QA RESONSE STAGES" team
    When I click the "SUBMIT" button
    Then an error message should be displayed as no "PRIVATE OFFICE/MINISTER SIGN OFF STAGES" team has been selected