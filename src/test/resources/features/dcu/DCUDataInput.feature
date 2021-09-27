@DCUDataInput @DCU
Feature: DCU Data Input

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @DCUWorkflow @DCURegression
  Scenario Outline: As a Data Input user, I want to be able to complete the Data Input stage, so the case can progress to the Markup stage
    When I get a "<caseType>" case at the "Data Input" stage
    And I complete the "Data Input" stage
    Then the case should be moved to the "Markup" stage
    And the summary should display the owning team as "<markupTeam>"
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage
    Examples:
      | caseType | markupTeam                                       |
      | MIN      | Direct Communications Unit Central Drafting Team |
      | TRO      | Direct Communications Unit Central Drafting Team |
      | DTEN     | Transfers & No10 Team                            |

  @DCURegression
  Scenario: As a Data Input user, I want to be able to mark a MIN case as a potential Home Secretary Reply, so Markup users can prioritise the case
    When I get a "MIN" case at the "Data Input" stage
    And I complete the Data Input stage, selecting that the case is a potential Home Secretary Reply case
    And the "Direct Communications Unit Central Drafting Team" workstack should display a HS symbol next to the case reference

  @Navigation
  Scenario: DCU data entry user selects correspondence channel and date of correspondence
    When I get a "MIN" case at the "Data Input" stage
    And I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    Then "Add a correspondent" link is displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  Scenario: User can add a Correspondent who is not a Member of Parliament
    When I get a "MIN" case at the "Data Input" stage
    And I fill all mandatory fields on the "DATA INPUT" page with valid data
    And I click the "Continue" button
    And I select to add a correspondent that "IS NOT" a member of parliament
    And I fill all mandatory fields on the "CORRESPONDENT DETAILS" page with valid data
    Then the submitted correspondent should be visible in the list of correspondents

  Scenario: User adds more than one correspondent
    When I get a "MIN" case at the "Data Input" stage
    And a case has a "primary" correspondent
    When I add an additional correspondent
    Then both correspondents are listed

  Scenario: User chooses to make a secondary correspondent the primary correspondent
    When I get a "MIN" case at the "Data Input" stage
    And a case has a "Secondary" correspondent
    When I select the primary correspondent radio button for a different correspondent
    And I click the "Finish" button
    Then the correct correspondent is recorded as the primary correspondent

  Scenario: User removes a correspondent
    When I get a "MIN" case at the "Data Input" stage
    And I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    And I add a "Member" correspondent
    And I remove the primary correspondent
    Then there shouldn't be a primary correspondent displayed

  Scenario: User edits an existing correspondent
    When I get a "MIN" case at the "Data Input" stage
    And I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    And I add a "Member" correspondent
    And I edit the primary correspondents name
    Then the correspondents name should be updated

  Scenario Outline: User checks that Home Secretary interest decision is properly displayed in summary tab
    When I get a "<caseType>" case at the "Data Input" stage
    And I select "<homeSecInterest>" for Home Secretary interest and complete the data input stage
    And I load the current case
    Then the Home Secretary interest decision should match the one displayed in the summary tab
    Examples:
      | caseType | homeSecInterest |
      | MIN      | Yes             |
      | MIN      | No              |
      | TRO      | Yes             |
      | TRO      | No              |

  @Validation
  Scenario: User must select whether the primary correspondent is an MP or not at the Data Input stage
    When I get a "MIN" case at the "Data Input" stage
    And I click the "Continue" button on the "IS THE CORRESPONDENT AN MP" page
    Then an error message should be displayed as I must select a correspondent type on this screen

  @Validation
  Scenario: User must select an MP from drop down box at Data Input stage
    When I get a "MIN" case at the "Data Input" stage
    And I click the "Add" button on the "ADD MEMBER OF PARLIAMENT" page
    Then an error message should be displayed as I must select a member of parliament from the drop down

  @Validation
  Scenario: User must select a correspondent type from the drop down if the correspondent is not an MP
    When I get a "MIN" case at the "Data Input" stage
    And I click the "Add" button on the "RECORD CORRESPONDENT DETAILS" page
    Then an error message should be displayed as I have not selected the correspondent type

  @Validation
  Scenario: User must enter text in correspondent's Full Name field
    When I get a "MIN" case at the "Data Input" stage
    And I click the "Add" button on the "RECORD CORRESPONDENT DETAILS" page
    Then an error message should be displayed as I have not entered text in the full name field

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Data Input stage
    When I get a "MIN" case at the "Data Input" stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box

  @Validation
  Scenario Outline: DCU data entry user must enter valid dates on Data Input
    When I get a "MIN" case at the "Data Input" stage
    And I fill all mandatory fields on the "Data Input" page with valid data
    But I enter an invalid "<field>" date
    And I click the "Continue" button
    Then "Invalid date" error message is displayed
    Examples:
      | field                   |
      | Correspondence Sent     |
      | Correspondence Received |

  @Validation
  Scenario Outline: DCU data entry user cannot leave dates blank on Data Input
    When I get a "MIN" case at the "Data Input" stage
    And I fill all mandatory fields on the "Data Input" page with valid data
    But I do not enter a "<field>"
    And I click the "Continue" button
    Then "<error message>" error message is displayed
    Examples:
      | field                        | error message           |
      | Correspondence Sent Date     | Correspondence Sent     |
      | Correspondence Received Date | Correspondence Received |

  @Validation
  Scenario Outline: User tests the validation at the DCU Data Input stage
    When I get a "<caseType>" case at the "Data Input" stage
    And I trigger the "<errorMessage>" error message at the "Data Input" stage
    Then the "<errorMessage>" error message is displayed at the "Data Input" stage
    Examples:
      | caseType | errorMessage                      |
      | MIN      | CORRESPONDENCE SENT DATE REQUIRED |
      | MIN      | INBOUND CHANNEL REQUIRED          |
      | TRO      | COPY TO NUMBER 10 REQUIRED        |
      | TRO      | HOME SECRETARY INTEREST REQUIRED  |
      | DTEN     | PRIMARY CORRESPONDENT REQUIRED    |
      | DTEN     | DRAFTING DEADLINE REQUIRED        |
      | DTEN     | DISPATCH DEADLINE REQUIRED        |