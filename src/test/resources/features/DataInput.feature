Feature: HOCS User is add data to a case

  Background:
    Given I am user "Dom"
    And I am at the "Data Input" stage

  @HOCS-274 @HOCS-238 @critical
  Scenario: DCU data entry user selects correspondence channel and date of correspondence
    When I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    Then I am taken to the "Record Correspondent Details" page

  @HOCS-274 @HOCS-238 @critical
  Scenario Outline: DCU data entry user select different correspondence channels
    When I fill all mandatory fields on the "Data Input" page with valid data
    And I set the correspondence channel to "<channel>"
    And I click the "Continue" button
    Then I am taken to the "Record Correspondent Details" page
    Examples:
      | channel |
      | Email   |
      | Post    |
      | Phone   |
      | No.10   |

  @HOCS-274 @HOCS-238 @critical
  Scenario Outline: DCU data entry user must enter valid dates on Data Input
    When I fill all mandatory fields on the "Data Input" page with valid data
    But I enter an invalid "<field>" date
    And I click the "Continue" button
    Then I am taken to the "Record Correspondence Details" page
    Examples:
      | field                   |
      | Correspondence Sent     |
      | Correspondence Received |

  @HOCS-274 @HOCS-238 @critical
  Scenario Outline: DCU data entry user cannot leave dates blank on Data Input
    When I fill all mandatory fields on the "Data Input" page with valid data
    But I do not enter a "<field>"
    And I click the "Continue" button
    Then "<error message>" error message is displayed
    Examples:
      | field                   | error message           |
      | Correspondence Sent     | Correspondence Sent     |
      | Correspondence Received | Correspondence Received |

  @HOCS-276, @HOCS-238
  Scenario: User can select a member from the dropdown
    When I select to correspond with a member from the dropdown
    Then the member is the "primary" correspondent

  @HOCS-276, @HOCS-238
  Scenario: User can select a member from the search function
    When I select to correspond with "<minister>" from the search function
    Then the member is the "primary" correspondent

  @HOCS-277, @HOCS-238
  Scenario: User adds a correspondent manually
    When I enter correspondence data manually
    Then the correspondence type is the "primary" correspondent

  @HOCS-394, @HOCS-238
  Scenario: User chooses to add another correspondent
    Given I select to correspond with a member from the dropdown
    When I add an additional correspondent
    Then the correspondence type is the "secondary" correspondent

  @HOCS-394, @HOCS-238 @manual
  Scenario: User chooses to make a secondary correspondent the primary correspondent
    Given a case has a "Secondary" correspondent
    When I select the primary correspondent radio button for a different correspondent
    Then the correspondence type is the "primary" correspondent