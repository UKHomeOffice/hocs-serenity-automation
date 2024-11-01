@ManagementUI @CS
Feature: ManagementUI

  Background:
    Given I am logged into "CS Management UI" as user "DECS_USER"

  @Navigation
  Scenario Outline: User navigates to a management page
    When I select to "<managementAction>"
    Then the "<pageTitle>" management page should be displayed
    Examples:
      | managementAction                               | pageTitle                                        |
      | Manage standard lines                          | View and update standard lines                   |
      | Add a standard line                            | Add a Standard Line                              |
      | Manage a team                                  | Team search                                      |
      | Create a DCU drafting team                     | Create a DCU drafting team                       |
      | Manage a user                                  | User search                                      |
      | Add a user                                     | Add a User                                       |
      | Manage templates                               | Select a Case Type                               |
      | Add parent topic                               | Add Parent Topic                                 |
      | Add child topic                                | Add Child Topic                                  |
      | Link topic to team                             | Topic search                                     |
      | Add a unit                                     | Add Unit                                         |
      | View units and their teams                     | Unit List                                        |
      | Withdraw a case                                | Withdraw a Case                                  |
      | Manage MPAM campaigns                          | View and edit campaigns                          |
      | Manage Ex-Gratia Business Area Representatives | View and edit Ex-Gratia Business Representatives |
      | Manage MPAM Business Units                     | Select a Business Area                           |
      | Manage MPAM Enquiry Reasons                    | Select an Enquiry Subject                        |
      | Manage FOI Account Managers                    | View and edit account managers                   |
      | Manage FOI Interested Parties                  | View and edit interested parties                 |
      | Manage Treat Official campaigns                | View and edit campaigns                          |
      | Manage Treat Official Recipients               | View and edit recipients                         |
      | Manage UKVI Complaint Enquiry Reasons          | View and edit UKVI enquiry reasons               |
      | Manage UKVI Complaints Business Areas          | Select a Directorate                             |


#    MANAGE STANDARD LINES

  @StandardLines
  Scenario: User is able to filter the standard lines on the manage standard lines screen
    When I select to "Manage standard lines"
    And I enter "Animal" into the standard line filter
    Then the standard lines in the "Topic" column should contain "Animal"

  @StandardLines @DCURegression
  Scenario: User is able to amend the expiry date of a standard line
    When I select to "Manage standard lines"
    And I add a new Standard Line with "Animal alternatives (3Rs)" as the topic
    And I amend the expiry date of the "Animal alternatives (3Rs)" standard line to 5 days from today
    Then the standard line expiry date has been correctly amended


#    ADD A STANDARD LINE

  @StandardLines @DCURegression @ManagmentUI2eTests
  Scenario: User can add a new Standard Line
    When I select to "Add a standard line"
    And I add a new Standard Line with "Animal alternatives (3Rs)" as the topic
    Then the Standard Line should be added to the selected topic

  @StandardLines @Validation
  Scenario: User must enter an expiration date in the future when creating a Standard Line
    When I select to "Add a standard line"
    And I enter a Standard Line expiration date in the past
    Then an error message should be displayed as the expiration date must be in the future

  @StandardLines @Validation
  Scenario: User must select a topic, add a document and enter an expiration date when creating a Standard Line
    When I select to "Add a standard line"
    And I click the "Submit" button
    Then an error message should be displayed as all Standard Line information has not been added

  @StandardLines @DCURegression
  Scenario: User is able to delete a standard line
    When I select to "Add a standard line"
    And I add a new Standard Line with "101 non-emergency number (cost)" as the topic
    Then the standard line "is" visible
    When I select the "Delete" action for the "101 non-emergency number (cost)" standard line
    And I select the checkbox to include expired standard lines
    Then the standard line "isn't" visible

  @StandardLines @DCURegression
  Scenario: User is able to expire a standard line
    When I select to "Add a standard line"
    And I add a new Standard Line with "101 non-emergency number (cost)" as the topic
    And I select the "Expire" action for the "101 non-emergency number (cost)" standard line
    Then the standard line "isn't" visible
    When I select the checkbox to include expired standard lines
    Then the standard line "is" visible

  @StandardLines
  Scenario: User is able to see a standard line created through MUI when drafting a MIN case
    When I select to "Add a standard line"
    And I add a new Standard Line with "Animal alternatives (3Rs)" as the topic
    And I navigate to "CS"
    And I create a "MIN" case and move it to the "Initial Draft" stage
    And I load and claim the current case
    And I select a case "should" be answered by my team
    And I select to reply by "post"
    Then the document added through MUI should be displayed in the list of available standard line documents


#    MANAGE A TEAM

  @TeamManagement
  Scenario: User can not see any assigned users if team does not have any
    When I select to "Manage a team"
    And I search for a team with no assigned users
    Then no users should be shown in user list

  @TeamManagement @CSRegression
  Scenario: Users can be added and removed from teams in CS Management UI
    When I select to "Manage a team"
    And I select the "UK Central Authority" team from the dropdown
    And I add the user "TEST_USER_1" to the team
    Then the user "should" be visible in the team list
    When I remove the user "TEST_USER_1" from the team
    Then the user "should not" be visible in the team list

  @TeamManagement
  Scenario: User can add multiple users to a team
    When I select to "Manage a team"
    And I select the "Animals in Science Regulation Unit" team from the dropdown
    And I add the users "CAMERON" and "CASEY" to the team
    Then the users should be visible in the team list

  @TeamManagement @Validation
  Scenario: User should see an error when attempting to remove user from team that they currently have assigned cases in
    When I select to "Manage a team"
    And I select the "ANIMALS IN SCIENCE REGULATION UNIT" team from the dropdown
    And I attempt to remove the user "DECS_USER"
    Then an error message should be displayed as they have cases assigned in that team

  @TeamManagement @Validation
  Scenario: User must select a team from the dropdown on the team search page
    When I select to "Manage a team"
    And I click the view team button
    Then an error message should displayed as no team been selected

  @TeamManagement @Validation
  Scenario: User must select at least one user on the add users page
    When I select to "Manage a team"
    And I select the "ANIMALS IN SCIENCE REGULATION UNIT" team from the dropdown
    And I click the Add Selected Users button
    Then an error message should be displayed as no users have been selected

  @TeamManagement @CSRegression
  Scenario: User is able to move a team to a different unit
    When I select to "Manage a team"
    And I select the "Detention Services" team from the dropdown
    And I navigate to edit team
    And I change to a different unit
    Then the correct success message for changing the unit is displayed

  @TeamManagement
  Scenario: User is able to deactivate and reactivate a team through Team Management
    When I select to "Create a DCU drafting team"
    And I create a new DCU drafting team
    And I navigate to "CS Management UI"
    And I select to "Manage a team"
    And I load the "created" DCU Drafting team through team management
    And I "Deactivate" the team in team management
    Then the team should be displayed as "Inactive" in team management
    And a message should be displayed stating that the team has been successfully "Deactivated"
    When I "Reactivate" the team in team management
    Then the team should be displayed as "Active" in team management
    And a message should be displayed stating that the team has been successfully "Reactivated"

  @TeamManagement
  Scenario: User is able to load deactivated teams from the teams typeahead in team management
    When I select to "Create a DCU drafting team"
    And I create a new DCU drafting team
    And I navigate to "CS Management UI"
    And I select to "Manage a team"
    And I load the "created" DCU Drafting team through team management
    And I "Deactivate" the team in team management
    And I navigate to "CS Management UI"
    When I select to "Manage a team"
    And I select to include deactivated teams in teams typeahead
    And I load the "deactivated" DCU Drafting team through team management
    Then the deactivated team should be displayed


#    CREATE A DCU DRAFTING TEAM

  @TeamManagement
  Scenario: User can create a DCU drafting team through team management
    When I select to "Create a DCU drafting team"
    And I create a new DCU drafting team
    Then the success message for team "Creation" should be displayed
    When I navigate to "CS Management UI"
    And I select to "Manage a team"
    And I load the "created" DCU Drafting team through team management
    Then the "created" DCU Drafting team is displayed

  @TeamManagement @CSRegression
  Scenario: User is able to rename teams through team management
    When I select to "Create a DCU drafting team"
    And I create a new DCU drafting team
    And I navigate to "CS Management UI"
    And I select to "Manage a team"
    And I edit the name of the created DCU drafting team
    Then the success message for team "Rename" should be displayed
    And I navigate to "CS Management UI"
    When I select to "Manage a team"
    And I load the "renamed" DCU Drafting team through team management
    Then the "renamed" DCU Drafting team is displayed

  @TeamManagement @CSRegression
  Scenario: User is able to assign users to a DCU drafting team created through team management
    When I select to "Create a DCU drafting team"
    And I create a new DCU drafting team
    And I navigate to "CS Management UI"
    And I select to "Manage a team"
    And I load the "created" DCU Drafting team through team management
    And I add the user "DCU_USER" to the team
    Then the user "should" be visible in the team list

  @TeamManagement
  Scenario: User is able to assign cases to a DCU drafting team created through team management
    When I select to "Create a DCU drafting team"
    And I create a new DCU drafting team
    And I navigate to "CS"
    And I create a "MIN" case with "Cyber Stalking And Harassment" as the primary topic
    And I override the initial draft team of the case to the team created in Management UI
    And I load the current case
    Then the case should be assigned to the DCU draft team created in Management UI


#    MANAGE A USER

  @UserManagement
  Scenario: A user can check the teams a user is in through User Management
    When I select to "Manage a user"
    And I load the teams of which "Cameron" is a member
    Then the teams the user is a part of are displayed

  @UserManagement @CSRegression
  Scenario: A user can be added and removed from a team through User Management
    Given I select to "Manage a user"
    And I load the teams of which "Cameron" is a member
    And I add the user to the "OSCT Secretariat" team
    Then the success ribbon should be displayed once the user is added
    And the team should be visible in the users list of teams
    When I remove the user from the "OSCT Secretariat" team
    Then the team should be removed from the users list of teams


#    ADD A USER

  @UserManagement @CSRegression @ManagmentUI2eTests
  Scenario: A new user can be created in DECS through User Management
    Given I select to "Add a user"
    When I submit the details for the new user
    Then a success message is displayed


#    MANAGE TEMPLATES

  @TemplateManagement
  Scenario: User is able to add a new template to a case type
    Given I select to "Manage templates"
    When I load the templates for the "MIN" case type
    And I add a new template to the case type
    Then a success message is displayed

  @TemplateManagement
  Scenario: User is able to remove a template from a case type
    Given I select to "Manage templates"
    When I load the templates for the "MIN" case type
    And I add a new template to the case type
    And I remove a template from the case type
    Then the template should be removed from the case type

  @TemplateManagement
  Scenario: User is able to see a template added through MUI when drafting a COMP case
    Given I select to "Manage templates"
    When I load the templates for the "COMP" case type
    And I add a new template to the case type
    And I navigate to "CS"
    And I create a "COMP" case and move it to the "Service Draft" stage
    And I load and claim the current case
    Then the template should be displayed in the list of available templates


#    ADD PARENT TOPIC

  @TopicManagement
  Scenario: User is able to create a new Parent Topic through Topic Management
    Given I select to "Add parent topic"
    When I create a new parent topic
    Then a success message is displayed

  @TopicManagement
  Scenario: User is able to link the new Parent Topic to a Child topic during creation
    Given I select to "Add parent topic"
    When I create a new parent topic
    And I select to "Add child topic"
    And I can create a child topic with the newly created parent topic linked
    Then a success message is displayed


#    ADD CHILD TOPIC

  @TopicManagement @Validation
  Scenario: User must select a parent topic on the Add Child Topic page
    When I select to "Add child topic"
    And I enter a display name
    And I click the "Submit" button
    Then an error message should be displayed as no parent topic has been selected

  @TopicManagement @Validation
  Scenario: User must enter a display name on the Add Child Topic page
    When I select to "Add child topic"
    And I select a parent topic
    And I click the "Submit" button
    Then an error message should be displayed as no display name has been entered

  @TopicManagement
  Scenario: User can submit a new child topic
    When I select to "Add child topic"
    And I select a parent topic
    And I enter a display name
    Then I am returned to the dashboard screen
    And a success message is displayed

  @TopicManagement @Validation
  Scenario: User cannot create a child topic with the same parent topic and display name as one that already exists
    When I select to "Add child topic"
    And I enter a parent topic and display name that duplicate an existing child topic
    And I click the "Submit" button
    Then an error message should be displayed stating that topic already exists

  @TopicManagement
  Scenario: User can use the same display name for two different child topics if the parent topics are different
    And I have created a new child topic
    And I select to "Add child topic"
    And I select a different parent topic
    And I enter the same display name
    Then I am returned to the dashboard screen
    And a success message is displayed


#    LINK TOPIC TO TEAM

  @TopicManagement @DCURegression
  Scenario: User can create a new child topic in Management UI and assign that topic to a case during Markup stage in CS
    And I have created a new child topic
    And I have linked teams to the new child topic
    When I navigate to "CS"
    And I create a "MIN" case and move it to the "Markup" stage
    And I progress the case to the point of adding a topic
    And I add the new child topic
    Then the topic can be viewed in the case timeline

  @TopicManagement @Validation
  Scenario: User cannot select a new child topic in HOCS if it has not had team links assigned in Management UI
    And I have created a new child topic
    And I navigate to "CS"
    And I create a "MIN" case and move it to the "Markup" stage
    And I progress the case to the point of adding a topic
    When I add the new child topic
    Then an error message is displayed

  @TopicManagement
  Scenario: User can view a summary of the topic and teams before final submission
    Given I have created a new child topic
    When I select to "Link topic to team"
    And I select a topic that "DOES NOT" have linked teams
    And I click the "Submit" button
    And I select a "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I select a "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team
    And I click the "Submit" button
    Then the summary should correctly detail the topic and the teams chosen to link to it

  @TopicManagement
  Scenario: User can choose and submit teams to amend the links of a topic
    When I select to "Link topic to team"
    And I select a topic that "DOES" have linked teams
    And I click the "Submit" button
    And I select a "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I select a "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team
    And I click the "Submit" button
    And I click the "Submit" button
    Then I am returned to the dashboard screen
    And a success message is displayed

  @TopicManagement @Validation
  Scenario: User must select a topic on the topic search page for linking team to topic
    When I select to "Link topic to team"
    And I click the "Submit" button
    Then an error message should be displayed as no topic has been selected

  @TopicManagement @Validation
  Scenario: User must select a 'Initial Draft and QA response stages' team to assign topic to
    When I select to "Link topic to team"
    And I select a topic that "DOES" have linked teams
    And I click the "Submit" button
    And I select a "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team
    And I click the "Submit" button
    Then an error message should be displayed as no "INITIAL DRAFT AND QA RESPONSE STAGES" team has been selected

  @TopicManagement @Validation
  Scenario: User must select a 'Private Office/Ministerial Sign Off stages' team to assign topic to
    When I select to "Link topic to team"
    And I select a topic that "DOES" have linked teams
    And I click the "Submit" button
    And I select a "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I click the "Submit" button
    Then an error message should be displayed as no "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team has been selected

  @TopicManagement @DCURegression
  Scenario: Teams linked to new child topic in Management UI are displayed as default teams in HOCS for that topic
    And I have created a new child topic
    And I have linked teams to the new child topic
    When I navigate to "CS"
    And I create a single "MIN" case and return to the dashboard
    And I complete the "Data Input" stage
    And I assign the Topic "NEW CHILD TOPIC"
    Then the case should be assigned to the "NEW DRAFTING AND QA TEAM" for drafting
    And the case should be assigned to the "NEW PRIVATE AND MINISTERIAL TEAM" for approval

  @TopicManagement
  Scenario: A topic with existing team links can have those links amended in Management UI
    Given I navigate to "CS"
    And I discover the current default team links for a topic
    When I navigate to "CS Management UI"
    And I select to amend the team links for the topic
    And I select a different "INITIAL DRAFT AND QA RESPONSE STAGES" team
    And I select a different "PRIVATE OFFICE/MINISTERIAL SIGN OFF STAGES" team
    And I click the "Submit" button
    And I click the "Submit" button
    And I check the default team links in CS again
    Then the case should be assigned to the "NEW DRAFTING AND QA TEAM" for drafting
    And the case should be assigned to the "NEW PRIVATE AND MINISTERIAL TEAM" for approval


#    ADD A UNIT

  @UnitManagement @Validation
  Scenario: User must enter a display name and short code on the add unit page
    When I select to "Add a unit"
    And I click the "Submit" button
    Then an error message should be displayed as they have not entered a display name and short code

  @UnitManagement
  Scenario: User can submit a new Unit
    When I select to "Add a unit"
    And I enter a "NEW" Display Name
    And I enter a "NEW" Short Code
    And I click the "Submit" button
    Then I am returned to the dashboard screen
    And a success message is displayed

  @UnitManagement @Validation
  Scenario: User cannot submit a duplicate Unit
    When I select to "Add a unit"
    And I enter a "NEW" Display Name
    And I enter a "NEW" Short Code
    And I click the "Submit" button
    And I select to "Add a unit"
    And I enter a "Duplicate" Display Name
    And I enter a "Duplicate" Short Code
    And I click the "Submit" button
    Then an error message should be displayed a unit with those details already exists

  @UnitManagement @Validation
  Scenario: User can view a unit they create in the list of units
    When I select to "Add a unit"
    And I enter a "NEW" Display Name
    And I enter a "NEW" Short Code
    And I click the "Submit" button
    And I select to "View units and their teams"
    Then the previously created unit should be listed


#    VIEW UNITS AND THEIR TEAMS

  @UnitManagement
  Scenario: User can view a list of units
    When I select to "View units and their teams"
    Then a list of units should be displayed


#    WITHDRAW A CASE (COVERED BY WCS MANAGEMENT UI SCENARIO)


#    MANAGE MPAM CAMPAIGNS

  @ListsManagement @MPAMRegression2
  Scenario: User is able to add a MPAM Campaign through Lists Management
    When I select to "Manage MPAM campaigns"
    And I select to add a new campaign
    And I submit details for the new campaign
    Then the success message for adding a campaign should be displayed
    And I should be able to view the new "MPAM" campaign in the table of campaigns

  @ListsManagement @MPAMRegression2
  Scenario: User is able to amend the details of a MPAM campaign through Lists Management
    And I have an existing "MPAM" campaign I want to amend
    When I select to "Manage MPAM campaigns"
    And I select to amend the campaign
    And I submit a new name for the campaign
    Then the success message for amending a campaign should be displayed
    And I should be able to view the renamed "MPAM" campaign in the table of campaigns

  @ListsManagement @MPAMRegression2
  Scenario: User can add a MPAM case to a new MPAM campaign that was added through Lists Management
    And I have added a new "MPAM" campaign in MUI
    When I navigate to "CS"
    And I get a "MPAM" case at the "Triage" stage
    And I add the case to the new campaign
    And I load the current case
    Then the case is added to the correct Campaign


#    MANAGE EX-GRATIA BUSINESS AREA REPRESENTATIVES

  @ListsManagement @COMPRegression @ManagmentUI2eTests
  Scenario: User can add and delete an Ex-Gratia Business Area Representative through Lists Management
    When I select to "Manage Ex-Gratia Business Area Representatives"
    And I select to add a new representative
    And I submit details for the new representative
    Then the success message for adding a representative should be displayed
    And I should be able to view the new representative in the table of representatives
    When I select to delete the representative
    Then the success message for deleting a representative should be displayed
    And I should not be able to view the deleted representative in the table of representatives


#    MANAGE MPAM BUSINESS UNITS

  @ListsManagement @MPAMRegression2
  Scenario: User is able to add an MPAM Business Unit through Lists Management
    When I select to "Manage MPAM Business Units"
    And I select a Business Area to add a new Business Unit to
    And I select to add a new Business Unit
    And I submit details for the new Business Unit
    Then the success message for adding a Business Unit should be displayed
    And I should be able to view the new Business Unit in the table of Business Units

  @ListsManagement @MPAMRegression2
  Scenario: User is able to amend the details of an MPAM Business Unit through Lists Management
    And I have an existing Business Unit I want to amend
    When I select to "Manage MPAM Business Units"
    And I select the correct Business Area
    And I select to amend the Business Unit
    And I submit a new name for the Business Unit
    Then the success message for amending a Business Unit should be displayed
    And I should be able to view the renamed Business Unit in the table of Business Units


#    MANAGE MPAM ENQUIRY REASONS

  @ListsManagement @MPAMRegression2
  Scenario: User is able to add an MPAM Enquiry Reason through Lists Management
    When I select to "Manage MPAM Enquiry Reasons"
    And I select a Enquiry Subject to add a new Enquiry Reason to
    And I select to add a new "MPAM" Enquiry Reason
    And I submit details for the new "MPAM" Enquiry Reason
    Then the success message for adding an Enquiry Reason should be displayed
    And I should be able to view the new "MPAM" Enquiry Reason in the table of Enquiry Reasons

  @ListsManagement @MPAMRegression2
  Scenario: User is able to amend the details of an MPAM Enquiry Reason through Lists Management
    And I have an existing Enquiry Reason I want to amend
    When I select to "Manage MPAM Enquiry Reasons"
    And I select the correct Enquiry Subject
    And I select to amend the Enquiry Reason
    And I submit a new name for the "MPAM" Enquiry Reason
    Then the success message for amending an Enquiry Reason should be displayed
    And I should be able to view the renamed "MPAM" Enquiry Reason in the table of Enquiry Reasons

  @ListsManagement @MPAMRegression2
  Scenario: User can select a new Enquiry Reason that was added through Lists Management when viewing a case in DECS
    And I have added a new "MPAM" Enquiry Reason in MUI
    When I navigate to "CS"
    And I get a "MPAM" case at the "Triage" stage
    And I select the appropriate Enquiry Subject
    Then I should be able to select the new Enquiry Reason


#    MANAGE FOI ACCOUNT MANAGERS

  @ListsManagement @FOIRegression
  Scenario: User is able to add a FOI Account Manager through Lists Management
    When I select to "Manage FOI Account Managers"
    And I select to add a new account manager
    And I submit details for the new account manager
    Then the success message for adding an account manager should be displayed
    And I should be able to view the new account manager in the table of account managers

  @ListsManagement @FOIRegression
  Scenario: User is able to amend the details of an FOI Account Manager through Lists Management
    And I have an existing account manager I want to amend
    When I select to "Manage FOI Account Managers"
    And I select to amend the account manager
    And I submit a new name for the account manager
    Then the success message for amending an account manager should be displayed
    And I should be able to view the renamed account manager in the table of account managers


#    MANAGE FOI INTERESTED PARTIES

  @ListsManagement @FOIRegression
  Scenario: User is able to add a FOI Interested Party through Lists Management
    When I select to "Manage FOI Interested Parties"
    And I select to add a new interested party
    And I submit details for the new interested party
    Then the success message for adding an interested party should be displayed
    And I should be able to view the new interested party in the table of interested parties

  @ListsManagement @FOIRegression
  Scenario: User is able to amend the details of an FOI Interested Party through Lists Management
    And I have an existing interested party I want to amend
    When I select to "Manage FOI Interested Parties"
    And I select to amend the interested party
    And I submit a new name for the interested party
    Then the success message for amending an interested party should be displayed
    And I should be able to view the renamed interested party in the table of interested parties

  @ListsManagement @FOIRegression
  Scenario: User is able to select an Interested Party added through Lists Management when registering an interest in an FOI case in DECS
    And I have added a new interested party in MUI
    When I navigate to "CS"
    And I get a "FOI" case at the "Case Creation" stage
    And I select the actions tab
    When I select to record an interest in the case
    Then I should be able to select that the interested party is the one I created in MUI


#    MANAGE TREAT OFFICIAL CAMPAIGNS

  @ListsManagement @TORegression
  Scenario: User is able to add a Treat Official campaign through Lists Management
    When I select to "Manage Treat Official campaigns"
    And I select to add a new campaign
    And I submit details for the new campaign
    Then the success message for adding a campaign should be displayed
    And I should be able to view the new "Treat Official" campaign in the table of campaigns

  @ListsManagement @TORegression
  Scenario: User is able to amend the details of a Treat Official campaign through Lists Management
    And I have an existing "Treat Official" campaign I want to amend
    When I select to "Manage Treat Official campaigns"
    And I select to amend the campaign
    And I submit a new name for the campaign
    Then the success message for amending a campaign should be displayed
    And I should be able to view the renamed "Treat Official" campaign in the table of campaigns

  @ListsManagement @TORegression
  Scenario: User can add a Treat Official case to a new Treat Official campaign that was added through Lists Management
    And I have added a new "Treat Official" campaign in MUI
    When I navigate to "CS"
    And I get a "TO" case at the "Triage" stage
    And I set an Enquiry Subject and Reason
    And I select a Business Unit Type and corresponding Business Unit
    And I put the case into the new campaign
    Then the case should have been put into the new campaign


#    MANAGE TREAT OFFICIAL RECIPIENTS

  @ListsManagement @TORegression
  Scenario: User is able to add a new Treat Official Recipient through Lists Management
    And I select to "Manage Treat Official Recipients"
    And I select to add a new recipient
    And I submit details for the new recipient
    Then the success message for adding a new recipient should be displayed
    And I should be able to view the new recipient in the table of recipients

  @ListsManagement @TORegression
  Scenario: User is able to amend the details of a Treat Official recipient through recipient management
    And I have an existing recipient I want to amend
    And I select to "Manage Treat Official Recipients"
    And I select to amend the recipient
    And I submit a new name for the recipient
    Then the success message for amending a recipient should be displayed
    And I should be able to view the renamed recipient in the table of recipients

  @ListsManagement @TORegression @ManagmentUI2eTests
  Scenario: User can add a Recipient that was added through Lists Management to a TO case in DECS
    And I have added a new recipient in MUI
    And I navigate to "CS"
    And I get a "TO" case at "Data Input" stage
    And I select which business area the correspondence is for
    And I select which channel the correspondence was received by
    And I select whether the Home Secretary has an interest in the case
    And I add a "Correspondent" correspondent
    And I confirm the primary correspondent
    And I add the newly created recipient to the case
    And I load the current case
    And I select the summary tab
    Then the newly created recipient details should be displayed in the summary tab


#     MANAGE UKVI COMPLAINT ENQUIRY REASONS

  @ListsManagement @COMPRegression
  Scenario: User is able to add a new UKVI Complaints Enquiry Reason through Lists Management
    And I select to "Manage UKVI Complaint Enquiry Reasons"
    And I select to add a new "COMP" Enquiry Reason
    And I submit details for the new "COMP" Enquiry Reason
    Then the success message for adding an Enquiry Reason should be displayed
    And I should be able to view the new "COMP" Enquiry Reason in the table of Enquiry Reasons

  @ListsManagement @COMPRegression
  Scenario: User is able to amend the details of a UKVI Complaints Enquiry Reason through Lists Management
    And I have added a new "COMP" Enquiry Reason in MUI
    When I select to "Manage UKVI Complaint Enquiry Reasons"
    And I select to amend the Enquiry Reason
    And I submit a new name for the "COMP" Enquiry Reason
    Then the success message for amending an Enquiry Reason should be displayed
    And I should be able to view the renamed "COMP" Enquiry Reason in the table of Enquiry Reasons

  @ListsManagement @COMPRegression @ManagmentUI2eTests
  Scenario: User can select a new UKVI Complaints Enquiry Reason that was added through Lists Management when viewing a case in DECS
    And I have added a new "COMP" Enquiry Reason in MUI
    When I navigate to "CS"
    And I get a "COMP" case at the "Service Triage" stage
    And I accept the case at "Service" Triage stage
    And I accept the previous Claim Category selection
    And I accept the previous Case Details selection
    Then I should be able to select the new COMP Enquiry Reason


#     MANAGE UKVI COMPLAINTS BUSINESS AREAS

  @ListsManagement @COMPRegression
  Scenario: User is able to add a new UKVI Complaints Business Area through Lists Management
    And I select to "Manage UKVI Complaints Business Areas"
    And I select a directorate to add the new Business Area to
    And I add a new business area to the selected directorate
#    Then the success message for adding a new business area should be displayed  Comment to be removed once HOCS-5117 is resolved
    And I should be able to view the created business area in the table of business areas

  @ListsManagement @COMPRegression
  Scenario: User is able to amend the details of a UKVI Complaints Business Area through Lists Management
    And I select to "Manage UKVI Complaints Business Areas"
    And I select a directorate to add the new Business Area to
    And I add a new business area to the selected directorate
    And I navigate to the business area list and select the amend link for the business area
    And I amend the name of the business area
#    Then the success message for amending a Business Area should be displayed  Comment to be removed once HOCS-5117 is removed
    And I should be able to view the renamed business area in the table of business areas

  @ListsManagement @COMPRegression
  Scenario: User can select a new UKVI Complaints Business Area that was added through Lists Management when viewing a case in DECS
    And I select to "Manage UKVI Complaints Business Areas"
    And I select a directorate to add the new Business Area to
    And I add a new business area to the selected directorate
    When I navigate to "CS"
    And I get a "COMP" case at the "Service Triage" stage
    And I accept the case at "Service" Triage stage
    And I accept the Claim Category selection
    And I accept the Case Details selection
    Then I should be able to select the new COMP Business Area