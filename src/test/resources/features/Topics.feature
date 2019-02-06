Feature: HOCS Topics are assigned to the correct Team

  Background:
    Given I am user "DANNY"

  @Topics @HOCS-584
  Scenario Outline: Topics are assigned to the correct team
    When the current user creates a single case "DCU MIN"
    And I complete the Data Input stage
    And I assign the Topic "<Topic>"
    Then The case should be assigned to the "<draftingTeam>" for Drafting
    And The case should be assigned to "<privateOfficeTeam>" for Approval

    Examples:
      | Topic                      | draftingTeam                       | privateOfficeTeam |
      | Cardiff University Kittens | Animals in Science Regulation Unit | Minister For Lords|
