Feature: DCU user decides how a case should be handled

  @HOCS-257, @HOCS-237
  Scenario: User enters reasons for no reply and sends for closure
    Given I am user "<string>"
    And I am at the "mark up" stage
    And I have entered information into the no reply needed text field
    When I click to close the case
    Then the case is sent to the closure stage - See BPMN
    And I am navigated to my "to do" page

  @HOCS-257, @HOCS-237
  Scenario: User does not enter reasons for no reply needed
    Given I am user "<string>"
    And I am at the "mark up" stage
    And I have not entered information into the no reply needed text field
    When I click to close the case
    Then I get an error message to enter information in the no reply needed field
    And the case is not sent to the closure stage - See BPMN
    And I remain on the page

    @HOCS-258, @HOCS-237
      Scenario: User selects Frequently Asked Question
      Given I have mark up permissions and I am at the mark up stage
      When I have clicked to identify the case as a Frequently Asked Question response
      Then a list of topics is available for me to select from (See BR MIN-7)

  @HOCS-258, @HOCS-237
  Scenario: User selects topic
    Given I have mark up permissions and I am at the mark up stage
    And I have clicked to identify the case as a Frequently Asked Question response
    When I select a topic
    Then the drafting team is pre-set by the business rules (BR MIN-7)
    And the sign off minister is set by the business rules (BR MIN-7)

  @HOCS-258, @HOCS-237
  Scenario: User overwrites drafting team
    Given I have mark up permissions and I am at the mark up stage
    And I have clicked to identify the case as a Frequently Asked Question response
    And I have select a topic
    And the drafting team is pre-set by the business rules (BR MIN-7)
    When I click to amend the drafting team
    Then I can only select from a fixed list of drafting teams

  @HOCS-258, @HOCS-237
  Scenario: User overwrites Minister
    Given I have mark up permissions and I am at the mark up stage
    And I have clicked to identify the case as a Frequently Asked Question response
    And I have select a topic
    And the Minister is pre-set by the business rules (BR MIN-7)
    When I click to amend the Minister
    Then I can only select from a fixed list of Ministers

  @HOCS-258, @HOCS-237
  Scenario: Allocated case progresses
    Given I have mark up permissions and I am at the mark up stage
    And I have selected Frequently Asked Question And I have selected a topic
    And a drafting team and sign off Minister are selected
    When I click to allocate the case
    Then the case progresses as per BPMN (see BPMN link)
    And I am navigated to my "to do" page

