Feature: DCU user decides how a case should be handled

  @HOCS-257, @HOCS-237
  Scenario: User enters reasons for no reply and sends for closure
    Given I am user "<string>"
    And I am at the "mark up" stage
    When I close the case with no reply needed
    Then the case is sent to the closure stage - See BPMN
    And I am navigated to my "to do" page

  @HOCS-257, @HOCS-237
  Scenario: User does not enter reasons for no reply needed
    Given I am user "<string>"
    And I am at the "mark up" stage
    When do not enter reasons for a no reply needed case closure
    Then An error message is displayed
    And the case is not sent to the closure stage - See BPMN
    And I remain on the page

  @HOCS-258, @HOCS-237
  Scenario: User selects topic
    Given I am user "<string>"
    And I am at the "mark up" stage
    When I select an FAQ topic for a case
    Then the drafting team is pre-set by the business rules (BR MIN-7)
    And the sign off minister is set by the business rules (BR MIN-7)

  @HOCS-258, @HOCS-237
  Scenario: User overwrites drafting team
    Given I am user "<string>"
    And I am at the "mark up" stage
    And I select an "FAQ" topic for a case
    When I click to amend the drafting team
    Then I can only select from a fixed list of drafting teams

  @HOCS-258, @HOCS-237
  Scenario: User overwrites Minister
    Given I am user "<string>"
    And I am at the "mark up" stage
    And I select an "FAQ" topic for a case
    When I click to amend the Minister
    Then I can only select from a fixed list of Ministers

  @HOCS-258, @HOCS-237
  Scenario: Allocated case progresses
    Given I am user "<string>"
    And I am at the "mark up" stage
    And I select an "FAQ" topic for a case
    When I click to allocate the case
    Then the case progresses as per BPMN (see BPMN link)
    And I am taken to the "<string>" Page

  @HOCS-259, @HOCS-237
  Scenario: User can refer a case to another Govt Department
    Given I am user "<string>"
    And I am at the "mark up" stage
    When I refer the case to another Government Department
    Then the case proceeds as per the BPMN (see BPMN link)
    And I am taken to the "<string>" Page

  @HOCS-259, @HOCS-237
  Scenario: User does not enter department in free text field
    Given I am user "<string>"
    And I am at the "mark up" stage
    When I do not state the Other Government Department for assignment
    Then An error message is displayed

  @HOCS-260, @HOCS-237
  Scenario: User selects Policy Response topic
    Given I am user "<string>"
    And I am at the "mark up" stage
    When I select a "Policy Response" topic for a case
    Then the answering unit is set to the unit that is linked to the primary topic (see BR MIN-10)
    And the answering team is set to the team that is linked to the primary topic (see BR MIN-10)
    And the sign off minister is set to the minister that is linked to the primary topic (see BR MIN-10))

  @HOCS-260, @HOCS-237
  Scenario: User overwrites answering unit
    Given I am user "<string>"
    And I am at the "mark up" stage
    And I select a "Policy Response" topic for a case
    When I click to amend the answering "unit"
    Then I can only select from a fixed list of answering "units"

  @HOCS-260, @HOCS-237
  Scenario: User overwrites answering team
    Given I am user "<string>"
    And I am at the "mark up" stage
    And I select a "Policy Response" topic for a case
    When I click to amend the answering "team"
    Then I can only select from a fixed list of answering "teams"

  @HOCS-260, @HOCS-237
  Scenario: User overwrites answering team
    Given I am user "<string>"
    And I am at the "mark up" stage
    And I select a "Policy Response" topic for a case
    When I click to amend the answering "minister"
    Then I can only select from a fixed list of answering "ministers"

  @HOCS260, @HOCS-237
  Scenario: Allocated case progresses
    Given I am user "<string>"
    And I am at the "mark up" stage
    And I select a "Policy Response" topic for a case
    When I click to allocate the case
    Then the case progresses as per BPMN (see BPMN link)
    And I am navigated to my "to do" page