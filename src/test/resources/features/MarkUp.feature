eature: DCU user decides how a case should be handled

  Background:
    Given I am user "Dom"
    And I am at the "markup" stage

  @HOCS-266, @HOCS-237
  Scenario: Central Drafting Team user selects an initial decision of Policy Response or FAQ
    When I select an initial decision of "Policy Response"
    Then I have to allocate the case a "Topic"

  @HOCS-266, @HOCS-237
  Scenario: Central Drafting Team user selects an initial decision of Policy Response or FAQ
    When I select an initial decision of "FAQ"
    Then I have to allocate the case a "Topic"

  @HOCS-266, @HOCS-237
  Scenario: User selects an initial decision of Transfer to OGD
    When I select an initial decision of "Refer to OGD"
    Then I enter the transferring department name

  @HOCS-266, @HOCS-237
  Scenario: User selects an initial decision of No Reply Needed
    When I select an initial decision of "No Reply Needed"
    Then a mandatory "Reason for No Reply Needed" free text field is available

@HOCS-259, @HOCS-237
  Scenario: User does not enter department in free text field
    When I do not state the Other Government Department for assignment
    Then An error message is displayed

  @HOCS-257, @HOCS-237
  Scenario: User does not enter reasons for no reply needed
    When I do not enter reasons for a "no reply needed" case closure
    Then an error message is displayed

  @HOCS-258, @HOCS-262, @HOCS-237
  Scenario: User selects topic
    Then I select a "topic"
    
  @HOCS-258, @HOCS-262, @HOCS-237
  Scenario: User selects additional topics
    Then I select a "additional topic"
    
  @HOCS-258, @HOCS-262, @HOCS-237
  Scenario: User selects primary topic
    When I have added more than "1" "topic" to the case
    Then I choose a "primary topic" from the "topics" I have choose
   
  @HOCS-260, @HOCS-262, @HOCS-237
  Scenario: User selects Policy Response and has selected a Topic
    When I select a "Policy Response"
    And I allocate the case a "Topic"
    Then the "answering unit" is set to the "unit" that is linked to the "primary topic" 
    And the "answering team" is set to the "team" that is linked to the "primary topic"
    And the sign off minister is set to the minister that is linked to the "primary topic"

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
  Scenario: User overwrites answering minister
    Given I select a "Policy Response" topic for a case from the dropdown
    When I amend the answering "minister"
    Then I can only select from a fixed list of answering "ministers"

  

  
