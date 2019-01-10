Feature: HOCS User is add data to a case

  Background:
    Given I am user "DCU"
    And I am at the "Data Input" stage

  @HOCS-274 @HOCS-238
  Scenario: DCU data entry user selects correspondence channel and date of correspondence
    When I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    Then "Add a correspondent" link is displayed


    #After first test the first case in 'Data Input' is moved to the Add Correspondent stage
    # Therefore the following tests fail as they expect to arrive on the Data Input page
    # Must clear out and remake a data input stage case before each run but test is sound otherwise
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


    # Members of parliament not available in dropdowns
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
