@Creation @MPAM
Feature: Creation

  Background:
    Given I am logged into "CS" as user "MPAM_USER"
    And I create a "MPAM" case and move it to the "Creation" stage
    And I load and claim the current case

  @MPAMWorkflow @MPAMRegression1
  Scenario Outline: As an MPAM Creation user, I want to be able to enter all relevant case details, so the case can be Triaged
    When I enter the details of a "<refType>" MPAM case
    And I add a "Member" correspondent
    And I confirm the primary correspondent
    Then the case should be moved to the "Triage" stage
    And the summary should display the correct MPAM "Triage" stage team as the owning team
    And the read-only Case Details accordion should contain all case information entered during the "Creation" stage
    And the summary should contain the Business Area, Channel Received, Reference Type and Urgency
    Examples:
      | refType     |
      | Ministerial |
      | Official    |

  @MPAMRegression1
  Scenario: User can select a Ministerial Sign off team for the case
    And I select "Home Secretary" as the Ministerial sign off team when completing the creation stage
    And I view the MPAM case in the appropriate "Triage" stage workstack
    Then the Minister sign off team is correctly displayed
    And I click the link for the current case in the workstack
    Then the "Creation" MPAM accordion in case details should display the correct information for "Ministerial Sign Off Team"

  @Navigation
  Scenario: User should be on the MPAM Data Input Page
    Then the "MPAM Data Input" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User can navigate to the MPAM Correspondents Details Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    Then the "MPAM Correspondents Details" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User can navigate to the Add Correspondent Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    And I select to add a correspondent to the case
    Then the "Add Correspondent" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User can navigate to the Add Member of Parliament Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    And I select to add a correspondent that "is" a member of parliament
    Then the "Add member of parliament" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User can navigate to the Record Correspondent Details Page
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    And I select to add a correspondent that "is not" a member of parliament
    Then the "Record Correspondent Details" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @MPAMWorkflow
  Scenario Outline: User completes Case Creation stage with specific Business Area and Reference Type
    When I select "<businessArea>" as the Business Area and "<refType>" as the Reference Type
    And I complete the other required fields for Creation stage
    And I click the "Continue" button
    And I add a "Member" correspondent
    And I confirm the primary correspondent
    Then the case should be moved to the "Triage" stage
    Examples:
      | businessArea | refType     |
      | UKVI                   | Ministerial |
      | BF                     | Ministerial |
      | IE                     | Ministerial |
      | EUSS                   | Ministerial |
      | HMPO                   | Ministerial |
      | Windrush               | Ministerial |
      | Coronavirus (COVID-19) | Ministerial |
      | UKVI                   | Official    |
      | BF                     | Official    |
      | IE                     | Official    |
      | EUSS                   | Official    |
      | HMPO                   | Official    |
      | Windrush               | Official    |
      | Coronavirus (COVID-19) | Official    |

  @Validation
  Scenario: User attempts to progress a case without adding an MP correspondent
    And I try to advance a case with a public correspondent at Creation stage
    Then the MP correspondent is mandatory screen is displayed

  @Validation
  Scenario Outline: User tests validation at MPAM Creation
    And I trigger the "<errorType>" error message at "Creation"
    Then the "<errorType>" error message is displayed at "Creation"
    Examples:
      | errorType                                 |
      | BUSINESS AREA REQUIRED                    |
      | IS MINISTERIAL RESPONSE REQUIRED REQUIRED |
      | MINISTERIAL SIGN OFF TEAM REQUIRED        |
      | ADDRESSEE REQUIRED                        |
      | URGENCY REQUIRED                          |
      | CHANNEL RECEIVED REQUIRED                 |
      | PRIMARY CORRESPONDENT REQUIRED            |