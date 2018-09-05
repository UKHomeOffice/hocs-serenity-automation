Feature: HOCS User is able to create a case

  Background:
    Given I am user "Dom"
    And I am at the "Data Input" stage

  @HOCS-274 @HOCS-238 @critical
  Scenario: DCU data entry user selects correspondence channel and date of correspondence
    When I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    Then I am taken to the "Record Correspondent Details" Page

  @HOCS-274 @HOCS-238 @critical
  Scenario: DCU data entry user selects correspondence channel but does not input valid correspondence sent date
    When I fill all mandatory fields on the "Data Input" page with valid data
    But I enter an invalid "Correspondence Sent" date
    And I click the "Continue" button
    Then I am taken to the "Record Correspondence Details" Page

  @HOCS-274 @HOCS-238 @critical
  Scenario: DCU data entry user selects correspondence channel but does not input valid correspondence received date
    When I fill all mandatory fields on the "Data Input" page with valid data
    But I enter an invalid "Correspondence Received" date
    And I click the "Continue" button
    Then I am taken to the "Record Correspondence Details" Page

  @HOCS-274 @HOCS-238 @critical
  Scenario: DCU data entry user does not enter a Correspondence Sent Date
    When I fill all mandatory fields on the "Data Input" page with valid data
    But I do not enter a "Correspondence Sent Date"
    And I click the "Continue" button
    Then "Correspondence sent" error message is displayed

  @HOCS-274 @HOCS-238 @critical
  Scenario: DCU data entry user does not enter a Correspondence Received Date
    When I fill all mandatory fields on the "Data Input" page with valid data
    But I do not enter a "Correspondence Received Date"
    And I click the "Continue" button
    Then "Correspondence Received" error message is displayed

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