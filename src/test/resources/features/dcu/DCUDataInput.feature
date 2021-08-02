@DCUDataInput @DCU
Feature: DCU Data Input

  Background:
    Given I am logged into "CS" as user "DCU_USER"

  @Navigation
  Scenario: DCU data entry user selects correspondence channel and date of correspondence
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    Then "Add a correspondent" link is displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario Outline: DCU data entry user select different correspondence channels
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I fill all mandatory fields on the "Data Input" page with valid data
    And I set the correspondence channel to "<channel>"
    And I click the "Continue" button
    Then "Add a correspondent" link is displayed
    Examples:
      | channel |
      | Email   |
      | Post    |
      | Phone   |
      | No. 10  |

  @Validation
  Scenario Outline: DCU data entry user must enter valid dates on Data Input
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
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
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I fill all mandatory fields on the "Data Input" page with valid data
    But I do not enter a "<field>"
    And I click the "Continue" button
    Then "<error message>" error message is displayed
    Examples:
      | field                        | error message           |
      | Correspondence Sent Date     | Correspondence Sent     |
      | Correspondence Received Date | Correspondence Received |

  @DCURegression
  Scenario: User can add a Correspondent who is not a Member of Parliament
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I fill all mandatory fields on the "DATA INPUT" page with valid data
    And I click the "Continue" button
    And I select to add a correspondent that "IS NOT" a member of parliament
    And I fill all mandatory fields on the "CORRESPONDENT DETAILS" page with valid data
    Then the submitted correspondent should be visible in the list of correspondents


  Scenario: User adds more than one correspondent
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And a case has a "primary" correspondent
    When I add an additional correspondent
    Then both correspondents are listed


  Scenario: User chooses to make a secondary correspondent the primary correspondent
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And a case has a "Secondary" correspondent
    When I select the primary correspondent radio button for a different correspondent
    And I click the "Finish" button
    Then the correct correspondent is recorded as the primary correspondent

  @Validation
  Scenario: User must select whether the primary correspondent is an MP or not at the Data Input stage
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I click the "Continue" button on the "IS THE CORRESPONDENT AN MP" page
    Then an error message should be displayed as I must select a correspondent type on this screen

  @Validation
  Scenario: User must select an MP from drop down box at Data Input stage
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I click the "Add" button on the "ADD MEMBER OF PARLIAMENT" page
    Then an error message should be displayed as I must select a member of parliament from the drop down

  @Validation
  Scenario: User must select a correspondent type from the drop down if the correspondent is not an MP
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I click the "Add" button on the "RECORD CORRESPONDENT DETAILS" page
    Then an error message should be displayed as I have not selected the correspondent type

  @Validation
  Scenario: User must enter text in correspondent's Full Name field
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I click the "Add" button on the "RECORD CORRESPONDENT DETAILS" page
    Then an error message should be displayed as I have not entered text in the full name field

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Data Input stage
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not entered text in the Case Note text box


  Scenario: User removes a correspondent
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    And I add "Nicola Sturgeon" MP as a correspondent
    And I remove the primary correspondent
    Then there shouldn't be a primary correspondent displayed


  Scenario: User edits an existing correspondent
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    And I add "Nicola Sturgeon" MP as a correspondent
    And I edit the primary correspondents name
    Then the correspondents name should be updated

  @DCURegression
  Scenario Outline: User checks that Home Secretary interest decision is properly displayed in summary tab
    When I create a "<caseType>" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I select "<homeSecInterest>" for Home Secretary interest and complete the data input stage
    And I load the current case
    Then the Home Secretary interest decision should match the one displayed in the summary tab
    Examples:
    | caseType  | homeSecInterest |
    | MIN       | Yes             |
    | MIN       | No              |
    | TRO       | Yes             |
    | TRO       | No              |

  @Validation
  Scenario Outline: User tests the validation at the DCU Data Input stage
    When I create a "<caseType>" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I trigger the "<errorMessage>" error message at the "Data Input" stage
    Then the "<errorMessage>" error message is displayed at the "Data Input" stage
    Examples:
      | caseType  | errorMessage                      |
      | MIN       | CORRESPONDENCE SENT DATE REQUIRED |
      | MIN       | INBOUND CHANNEL REQUIRED          |
      | TRO       | COPY TO NUMBER 10 REQUIRED        |
      | TRO       | HOME SECRETARY INTEREST REQUIRED  |
      | DTEN      | PRIMARY CORRESPONDENT REQUIRED    |
      | DTEN      | DRAFTING DEADLINE REQUIRED        |
      | DTEN      | DISPATCH DEADLINE REQUIRED        |