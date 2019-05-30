Feature: HOCS Topics are assigned to the correct Team

  Background:
    Given I am user "DANNY"

  @Topics @TopicsDefaultTeams @SmokeTests
  Scenario Outline: Topics are assigned to the correct team
    When I create a single case "DCU MIN"
    #And I complete the Data Input stage
    And the Data Input Stage is completed for "DCU MIN" caseType
    And I assign the Topic "<Topic>"
    Then the case should be assigned to the "<draftingTeam>" for drafting
    And the case should be assigned to the "<privateOfficeTeam>" for approval
    Examples:
      | Topic                               | draftingTeam                       | privateOfficeTeam                                                  |
      | Cardiff University Kittens          | Animals in Science Regulation Unit | Minister for Lords                                                 |
      | Cyber Stalking And Harassment       | Public Protection Unit             | Under Secretary of State for Crime, Safeguarding and Vulnerability |
      | Domestic Violence Protection Orders | Public Protection Unit             | Under Secretary of State for Crime, Safeguarding and Vulnerability |


  @Topics @Overrides
  Scenario Outline: The user overrides the "<defaultTeam>" Team
    When I create a "<caseType>" case with "<Topic>"
    And I override the "<defaultTeam>" team to "<overrideTeam>"
    Then the case should be found in the "<overrideTeam>" team
    # Permissions removed from users so just running one example currently.
    Examples:
      | caseType | Topic                                        | defaultTeam    | overrideTeam                                                |
#      | DCU MIN  | Cyber Stalking And Harassment                | Initial Draft  | Extremism Analysis Unit
       | DCU MIN  | Cyber Stalking And Harassment                | Initial Draft  | Press Office                                                |
#      | DCU MIN  | Cyber Stalking And Harassment                | Initial Draft  | Finance                                                     |
#      | DCU MIN  | Cyber Stalking And Harassment                | Initial Draft  | Criminal & Financial Investigations                         |
#      | DCU MIN  | Cyber Stalking And Harassment                | Initial Draft  | Counter-Terrorism Legislation and Investigatory Powers Unit |
#      | DCU MIN  | Cyber Stalking And Harassment                | Initial Draft  | Chemical, Biological, Radiological, Nuclear & Explosives    |
#      | DCU MIN  | Legalisation of Drugs                        | Initial Draft  | Police Strategy & Reform Unit                               |
#      | DCU MIN  | Domestic Violence Protection Orders          | Initial Draft  | Animals in Science Regulation Unit                          |
#      | DCU MIN  | Moped pursuits                               | Initial Draft  | Counter Extremism Unit                                      |
#      | DCU MIN  | Emergency services collaboration/integration | Initial Draft  | Police Workforce and Professionalism Unit                   |
#      | DCU MIN  | Cardiff University Kittens                   | Private Office | Minister for Lords                                          |
#      | DCU MIN  | Legalisation of Drugs                        | Private Office | Minister for Lords                                          |
#      | DCU MIN  | Domestic Violence Protection Orders          | Private Office | Minister of State for Immigration                           |
#      | DCU MIN  | Emergency services collaboration/integration | Private Office | Minister of State for Security and Economic Crime           |
#      | DCU MIN  | Cyber Stalking And Harassment                | Private Office | Minister of State for Policing and Fire Service             |
