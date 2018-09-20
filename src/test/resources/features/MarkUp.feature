Feature: DCU user decides how a case should be handled

  Background:
    Given I am user "Dom"
    And I am at the "mark up" stage

  @HOCS-266, @HOCS-237
  Scenario: Central Drafting Team user selects an initial decision of Policy Response or FAQ
    When I select an initial decision of "Policy Response"
    Then an optional "Topics" free text field is available

  @HOCS-266, @HOCS-237
  Scenario: Central Drafting Team user selects an initial decision of Policy Response or FAQ
    When I select an initial decision of "FAQ"
    Then an optional "Topics" free text field is available

  @HOCS-266, @HOCS-237
  Scenario: User selects an initial decision of Transfer to OGD
    When I select an initial decision of "Transfer to OGD"
    Then a mandatory "Topics" free text field is available

  @HOCS-266, @HOCS-237
  Scenario: User selects an initial decision of No Reply Needed
    When I select an initial decision of "No Reply Needed"
    Then a mandatory "Reason for No Reply Needed" free text field is available

  @HOCS-257, @HOCS-237
  Scenario: User enters reasons for no reply and sends for closure
    When I close the case with no reply needed
    Then the case is moved to the "closure" stage
    And I am taken to the "to do" page

  @HOCS-257, @HOCS-237
  Scenario: User does not enter reasons for no reply needed
    When I do not enter reasons for a "no reply needed" case closure
    Then an error message is displayed

  @HOCS-258, @HOCS-262, @HOCS-237
  Scenario: User selects topic
    When I select an "FAQ" topic for a case from the dropdown
    Then the drafting team is pre-set by the business rules (BR MIN-7)
    And the sign off minister is set by the business rules (BR MIN-7)

  @HOCS-258, @HOCS-262, @HOCS-237
  Scenario: User overwrites drafting team
    Given I select an "FAQ" topic for a case from the type function
    When I amend the "drafting team"
    Then I can only select from a fixed list of drafting teams

  @HOCS-258, @HOCS-237
  Scenario: User overwrites Minister
    Given I select an "FAQ" topic for a case from the dropdown
    When I amend the "Minister"
    Then I can only select from a fixed list of Ministers

  @HOCS-258, @HOCS-237
  Scenario: Allocated case progresses
    Given I select an "FAQ" topic for a case from the dropdown
    When I "allocate" the case
    Then the case progresses as per BPMN (see BPMN link)
    And I am taken to the "<string>" page

  @HOCS-259, @HOCS-237
  Scenario: User can refer a case to another Govt Department
    When I refer the case to another Government Department
    Then the case proceeds as per the BPMN (see BPMN link)
    And I am taken to the "<string>" page

  @HOCS-259, @HOCS-237
  Scenario: User does not enter department in free text field
    When I do not state the Other Government Department for assignment
    Then An error message is displayed

  @HOCS-260, @HOCS-262, @HOCS-237
  Scenario: User selects Policy Response topic
    When I select a "Policy Response" topic for a case from the dropdown
    Then the answering unit is set to the unit that is linked to the primary topic (see BR MIN-10)
    And the answering team is set to the team that is linked to the primary topic (see BR MIN-10)
    And the sign off minister is set to the minister that is linked to the primary topic (see BR MIN-10))

  @HOCS-260, @HOCS-2372
  Scenario: User overwrites answering unit
    Given I select a "Policy Response" topic for a case from the type function
    When I amend the answering "unit"
    Then I can only select from a fixed list of answering "units"

  @HOCS-260, @HOCS-237
  Scenario: User overwrites answering team
    Given I select a "Policy Response" topic for a case from the dropdown
    When I amend the answering "team"
    Then I can only select from a fixed list of answering "teams"

  @HOCS-260, @HOCS-237
  Scenario: User overwrites answering team
    Given I select a "Policy Response" topic for a case from the dropdown
    When I amend the answering "minister"
    Then I can only select from a fixed list of answering "ministers"

  @HOCS260, @HOCS-237
  Scenario: Allocated case progresses
    Given I select a "Policy Response" topic for a case from the type function
    When I "allocate" the case
    Then the case is moved to the "<string>" stage
    And I am taken to the "to do" page

  @HOCS-263, @HOCS-238
  Scenario: User chooses to add another topic via type ahead function
    Given a primary topic has been set
    When I select a "Policy Response" topic for a case from the type function
    Then the topic is set as the "Secondary" Topic

  @HOCS-263, @HOCS-238
  Scenario: User chooses to add another topic via type ahead function
    Given a primary topic has been set
    When I select a "FAQ" topic for a case from the type function
    Then the topic is set as the "Secondary" Topic

  @HOCS-263, @HOCS-238
  Scenario: User chooses to add another topic via type ahead function
    Given a primary topic has been set
    When I select a "Policy Response" topic for a case from the dropdown
    Then the topic is set as the "Secondary" Topic

  @HOCS-263, @HOCS-238
  Scenario: User chooses to add another topic via type ahead function
    Given a primary topic has been set
    When I select a "FAQ" topic for a case from the dropdown
    Then the topic is set as the "Secondary" Topic

  @HOCS-263, @HOCS-238
  Scenario: User chooses to add another topic via type ahead function
    Given multiple topics have been set
    When I select the Primary Topic radio button for a topic that was a secondary topic
    Then the topic is set as the "Primary" Topic

  @HOCS-263, @HOCS-238
  Scenario: User chooses to add another topic via type ahead function
    Given multiple topics have been set
    When I select the Primary Topic radio button for a topic that was a secondary topic
    Then the appropriate Answering Unit, Team and Minister is pre-populated by default

  @HOCS-264, @HOCS-237
  Scenario: Answering Unit and Team is pre-populated by default
    When I select a "Policy Response" topic for a case from the dropdown
    Then the appropriate Answering Unit and Team is pre-populated by default

  @HOCS-264, @HOCS-237
  Scenario: DCU Central Drafting Team user chooses to select alternative Answering Unit and Team
    Given I select a "Policy Response" topic for a case from the dropdown
    When I select an answering unit from the dropdown
    Then I must select one of the answering teams attached to that unit

  @HOCS-265, @HOCS-237
  Scenario: Answering Unit and Team is pre-populated by default
    When I select a "Policy Response" topic for a case from the dropdown
    Then the appropriate Minister linked to the Answering Unit and Team is pre-populated by default

  @HOCS-265, @HOCS-237
  Scenario: DCU Central Drafting Team user chooses to select alternative Answering Unit and Team
    When I select a "Policy Response" topic for a case from the dropdown
    When I select a Minister from the dropdown
    Then an alternative minister is set for signing off correspondence
