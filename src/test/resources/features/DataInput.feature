Feature: HOCS User is add data to a case

  Background:
    Given I am user "EAMON"
    When I am at the "DATA INPUT" stage

  @HOCS-274 @HOCS-238
  Scenario: DCU data entry user selects correspondence channel and date of correspondence
    When I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    Then "Add a correspondent" link is displayed

  @HOCS-274 @HOCS-238 @Testing
  Scenario Outline: DCU data entry user select different correspondence channels
    When I fill all mandatory fields on the "Data Input" page with valid data
    And I set the correspondence channel to "<channel>"
    And I click the "Continue" button
    Then "Add a correspondent" link is displayed
    Examples:
      | channel |
      | Email   |
      | Post    |
      | Phone   |
      | No. 10  |

  @HOCS-274 @HOCS-238
  Scenario Outline: DCU data entry user must enter valid dates on Data Input
    When I fill all mandatory fields on the "Data Input" page with valid data
    But I enter an invalid "<field>" date
    And I click the "Continue" button
    Then I am taken to the "Record Correspondence Details" page
    Examples:
      | field                   |
      | Correspondence Sent     |
      | Correspondence Received |

  @HOCS-274 @HOCS-238
  Scenario Outline: DCU data entry user cannot leave dates blank on Data Input
    When I fill all mandatory fields on the "Data Input" page with valid data
    But I do not enter a "<field>"
    And I click the "Continue" button
    Then "<error message>" error message is displayed
    Examples:
      | field                        | error message           |
      | Correspondence Sent Date     | Correspondence Sent     |
      | Correspondence Received Date | Correspondence Received |

  @HOCS-276 @HOCS-238
  Scenario: User can select a member John Bercow from the dropdown
    Given I select to add a correspondent that "is" a member
    When I enter "<string>" in the "search" field
    And I select the correspondent
    Then they should be added to the list of correspondents

  @HOCS-277, @HOCS-238
  Scenario: User adds a correspondent manually
    When I select to add a correspondent that "is not" a member
    And I enter "<string>" in the "Full Name" field
    And I enter "<string>" in the "Address" field
    Then they should be added to the list of correspondents

  @HOCS-394, @HOCS-238
  Scenario: User adds more than one correspondent
    Given a case has a "primary" correspondent
    When I add an additional correspondent
    Then I need to choose a "primary" correspondent

  @HOCS-394, @HOCS-238 @manual
  Scenario: User chooses to make a secondary correspondent the primary correspondent
    Given a case has a "Secondary" correspondent
    When I select the primary correspondent radio button for a different correspondent
    Then the correspondence type is the "primary" correspondent

  @Validation
  Scenario: Date correspondence was sent must be entered at Data Input stage
    And I click the continue button at the data input stage
    Then an error message should be displayed as I have not entered a correspondence date

  @Validation
  Scenario: How correspondence was received radio button must be selected at Data Input stage
    And I click the continue button at the data input stage
    Then an error message should be displayed as I have not selected a radio button

  @Validation
  Scenario: User must add a primary correspondent at Data Input stage
    And I click the finish button on the which is the primary correspondent screen
    Then an error message should be displayed as I have not added a primary correspondent

  @Validation
  Scenario: User must select whether the primary correspondent is an MP or not at the Data Input stage
    And I click the continue button on the is the correspondent an MP screen
    Then an error message should be displayed as I must select a radio button on this screen

  @Validation
  Scenario: User must select an MP from drop down box at Data Input stage
    And I click the add button on the add member of parliament screen
    Then an error message should be displayed as I must select a member of parliament from the drop down

  @Validation
  Scenario: User must select a correspondent type from the drop down if the correspondent is not an MP
    And I click the add button on the record correspondent details screen
    Then an error message should be displayed as I have not selected the correspondent type

  @Validation
  Scenario: User must enter text in correspondent's Full Name field
    And I click the add button on the record correspondent details screen
    Then an error message should be displayed as I have not entered text in the full name field


