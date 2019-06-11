Feature: HOCS User is add data to a case

  Background:
    Given I am user "EAMON"
    And I get a "DCU N10" case at "DATA INPUT" stage

  @HOCS-274 @HOCS-238
  Scenario: DCU data entry user selects correspondence channel and date of correspondence
    When I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    Then "Add a correspondent" link is displayed

  @HOCS-274 @HOCS-238
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

  @HOCS-274 @HOCS-238 @FixMe
  Scenario Outline: DCU data entry user must enter valid dates on Data Input
    # Scenario is failing as Assertion is not correct
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

  @HOCS-276 @HOCS-238 @FixMe
  Scenario: User can add a Member of Parliament as a Correspondent
    # Scenario is failing as the Add Correspondent Link is not loading correctly during the test run.
    When I fill all mandatory fields on the "DATA INPUT" page with valid data
    And I select to add a correspondent that "is" a member of parliament
    And I enter "Rt Hon John Bercow MP" in the "ADD A MEMBER OF PARLIAMENT" field
    Then they should be added to the list of correspondents

  @HOCS-277, @HOCS-238
  Scenario: User can add a Correspondent who is not a Member of Parliament
    When I fill all mandatory fields on the "DATA INPUT" page with valid data
    And I click the "Continue" button
    And I select to add a correspondent that "is not" a member of parliament
    And I fill all mandatory fields on the "CORRESPONDENT DETAILS" page with valid data
    Then they should be added to the list of correspondents

  @HOCS-394, @HOCS-238 @FixMe
  Scenario: User adds more than one correspondent
    Given a case has a "primary" correspondent
    When I add an additional correspondent
    Then I need to choose a "primary" correspondent

  @HOCS-394, @HOCS-238 @manual @FixMe
  Scenario: User chooses to make a secondary correspondent the primary correspondent
    Given a case has a "Secondary" correspondent
    When I select the primary correspondent radio button for a different correspondent
    Then the correspondence type is the "primary" correspondent

  @Validation
  Scenario Outline: User must complete all mandatory inputs on the Data Input form
    When I click the "continue" button
    Then an error message should be displayed as I have not entered a "<formDetail>"

    Examples:
    | formDetail |
    | Correspondence Date |
    | Correspondence Type |
    | Copy to Number Ten  |

  @Validation
  Scenario: User must select whether the primary correspondent is an MP or not at the Data Input stage
#    And I fill all mandatory fields on the "DATA INPUT" page with valid data
    And I click the "CONTINUE" button on the "IS THE CORRESPONDENT AN MP" page
    Then an error message should be displayed as I must select a correspondent type on this screen

  @Validation
  Scenario: User must select an MP from drop down box at Data Input stage
    And I click the "ADD" button on the "ADD MEMBER OF PARLIAMENT" page
    Then an error message should be displayed as I must select a member of parliament from the drop down

  @Validation
  Scenario: User must select a correspondent type from the drop down if the correspondent is not an MP
    And I click the "ADD" button on the "RECORD CORRESPONDENT DETAILS" page
    Then an error message should be displayed as I have not selected the correspondent type

  @Validation
  Scenario: User must enter text in correspondent's Full Name field
    And I click the "ADD" button on the "RECORD CORRESPONDENT DETAILS" page
    Then an error message should be displayed as I have not entered text in the full name field

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Data Input stage
    And I click the add button when creating a case note
    Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"