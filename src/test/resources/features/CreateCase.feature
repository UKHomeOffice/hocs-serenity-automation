Feature: HOCS User is able to create a case

  @HOCS-423 @HOCS-419
  Scenario: I am in one tenant
    Given I am user "A"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "case types 1,2,3 only"

  @HOCS-423, @HOCS-419
  Scenario: I am in more than one tenant
    Given I am user "B"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "case types 1,2,3 and 4,5,6 only"

  @HOCS-423, @HOCS-419
  Scenario: I am in a tenant that doesn't have a case type
    Given I am user "C"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "no case types"

  @HOCS-423, @HOCS-419
  Scenario: I am in a tenant that does have case types but I have no permissions
    Given I am user "D"
    When I navigate to the "Create Single Case" Page
    Then I am presented with "no case types"

  @HOCS-274, @HOCS-238
  Scenario: DCU data entry user selects correspondence channel and date of correspondence
    Given I am user "<string>"
    And I navigate to the "<string>" Page
    When I fill all mandatory fields on the "<string>" page with valid data
    And I click the "Next" button
    Then I am taken to the "<string>" Page

  @HOCS-274, @HOCS-238
  Scenario: DCU data entry user selects correspondence channel but does not input valid date of correspondence
    Given I am user "<string>"
    And I navigate to the "<string>" Page
    When I fill all mandatory fields on the "<string>" page with valid data
    But I enter an invalid date
    And I click the "Next" button
    Then I see the "invalid date of correspondence" message

  @HOCS-274, @HOCS-238
  Scenario: DCU data entry user selects a reference  (see attached screenshot)
    Given I am user "<string>"
    And I navigate to the "<string>" Page
    When I fill all mandatory fields on the "<string>" page with valid data
    And I select a reference type from the list of reference type options
    And I enter a reference in the reference field
    And I click the "Next" button
    Then I am taken to the "<string>" Page

  @HOCS-276, @HOCS-238
  Scenario: User can select a member from the dropdown
    Given I am user "<string>"
    And I navigate to the "data entry 2" Page
    When I select to correspond with a member from the dropdown
    Then the member is the "primary" correspondent

  @HOCS-276, @HOCS-238
  Scenario: User can select a member from the search function
    Given I am user "<string>"
    And I navigate to the "data entry 2" Page
    When I select to correspond with "<minister>" from the search function
    Then the member is the "primary" correspondent

  @HOCS-277, @HOCS-238
  Scenario: User adds a correspondent manually
    Given I am user "<string>"
    And I navigate to the "data entry 2" Page
    When I enter correspondence data manually
    Then the correspondence type is the "primary" correspondent

  @HOCS-394, @HOCS-238
  Scenario: User chooses to add another correspondent
    Given I am user "<string>"
    And a case has a "Primary" correspondent
    When I add an additional correspondent
    Then the correspondence type is the "secondary" correspondent

  @HOCS-394, @HOCS-238
  Scenario: User chooses to make a secondary correspondent the primary correspondent
    Given I am user "<string>"
    And a case has a "Secondary" correspondent
    When I select the primary correspondent radio button for a different correspondent
    Then the correspondence type is the "primary" correspondent