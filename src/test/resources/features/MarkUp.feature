Feature: DCU user decides how a case should be handled

  Background:
    Given I am user "EAMON"
    When I get a "DCU MIN" case at "MARKUP" stage

  @HOCS-266, @HOCS-237
  Scenario Outline: Central Drafting Team user selects an initial decision of Policy Response or FAQ
    When I select an initial decision of "<radioButton>"
    And I click the "CONTINUE" button
    And I click the "ADD A TOPIC" button
    Then a mandatory "TOPIC" free text field is displayed

    Examples:
    | radioButton |
    | Policy Response |
    | FAQ             |


  @HOCS-266, @HOCS-237
  Scenario: User selects an initial decision of Transfer to OGD
    When I select an initial decision of "REFER TO OGD"
    And I click the "CONTINUE" button
    Then the Other Government Department name free text field is displayed

  @HOCS-266, @HOCS-237
  Scenario: User selects an initial decision of No Response Needed
    When I select an initial decision of "NO RESPONSE NEEDED"
    And I click the "CONTINUE" button
    Then the No Response Needed casenote field is displayed

@HOCS-259, @HOCS-237
  Scenario: User does not enter department in free text field
    When I select an initial decision of "Refer to OGD"
    And I click the "CONTINUE" button
    But I do not enter an "Other Government Department"
    Then an error message is displayed

  @HOCS-257, @HOCS-237
  Scenario: User does not enter reasons for no reply needed
    When I select an initial decision of "NO RESPONSE NEEDED"
    And I click the "CONTINUE" button
    But I do not enter a "REASON FOR NO RESPONSE NEEDED"
    Then an error message is displayed

  @HOCS-258, @HOCS-262, @HOCS-237
  Scenario: User selects topic
    When I select an initial decision of "POLICY RESPONSE"
    And I click the "CONTINUE" button
    And I add the topic "CARDIFF UNIVERSITY KITTENS"
    Then the topic should be added to the case
    
  @Navigation
  Scenario: Clicking the cancel button on the allocate case screen at the Markup stage should take the user back to the
  dashboard
    And I click the "CANCEL" button
    Then I should be taken to the homepage

  @Validation
  Scenario: User must select a response on the first Markup Stage screen
    And I click the "CONTINUE" button
    Then an error message should be displayed as I have not selected a response

  @Validation
  Scenario: User must add a topic at the Markup Stage
    And I click the "CONTINUE" button on the "ADD A TOPIC" page
    Then an error message should be displayed as I have not added a topic

  @Validation
  Scenario: User must select a topic from the dropdown box at the Markup Stage
    And I click the "ADD" button on the "ENTER A NEW TOPIC" page
    Then an error message should be displayed as I have not selected a topic

  @Validation
  Scenario: User must enter text in the text box when creating a Case note at the Markup stage
    And I click the "ADD" button
    Then an error message should be displayed as I have not "ADDED ANY TEXT INTO THE CASE NOTE TEXT BOX"




  
