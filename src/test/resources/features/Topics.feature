Feature: HOCS Topics are assigned to the correct Team

  Background:
    Given I am user "EAMON"

  @Topics @TopicsDefaultTeams
  Scenario Outline: Topics are assigned to the correct team
    When I create a single case "DCU MIN"
    And the Data Input Stage is completed for "DCU MIN" caseType
    And I assign the Topic "<Topic>"
    Then the case should be assigned to the "<draftingTeam>" for drafting
    And the case should be assigned to the "<privateOfficeTeam>" for approval
    Examples:
      | Topic                               | draftingTeam                         | privateOfficeTeam                                                  |
      | Cardiff University Kittens          | Animals in Science Regulation Unit   | Minister for Lords                                                 |
#      | Cyber Stalking And Harassment       | Public Protection Unit               | Under Secretary of State for Crime, Safeguarding and Vulnerability |
#      | Domestic Violence Protection Orders | Public Protection Unit               | Under Secretary of State for Crime, Safeguarding and Vulnerability |
#      | Child Sexual Exploitation           | Tackling Exploitation and Abuse Unit | Under Secretary of State for Crime, Safeguarding and Vulnerability |
#      | Permanent Secretary AFU             | Accounting and Finance Unit          | Permanent Secretary                                                |
#      | Permanent Secretary CAIT            | Central Analysis and Insight Team    | Permanent Secretary                                                |
#      | Afghan Interpreters                 | Domestic Asylum Policy               | Minister of State for Immigration                                  |
#      | Future Funds                        | Efficiency and Resources Unit        | Minister of State for Policing and Fire Service                    |
#      | Criminal Records                    | International Criminality Unit       | Minister of State for Policing and Fire Service                    |
#      | Classification of Cannabis          | Drugs & Alcohol Unit                 | Minister of State for Policing and Fire Service                    |

  @Topics @Overrides @DroneTest
  Scenario Outline: The user overrides the "<defaultTeam>" Team
    When I create a "<caseType>" case with "<Topic>"
    And I override the "<defaultTeam>" team to "<overrideTeam>"
    Then the case should be found in the "<overrideTeam>" team
    # Permissions removed from users so just running one example currently.
    Examples:
      | caseType | Topic                         | defaultTeam   | overrideTeam                        |
      | DCU MIN  | Cyber Stalking And Harassment | Initial Draft | Extremism Analysis Unit             |
      | DCU MIN  | Cyber Stalking And Harassment | Initial Draft | Press Office                        |
      | DCU MIN  | Cyber Stalking And Harassment | Initial Draft | Finance                             |
      | DCU MIN  | Cyber Stalking And Harassment | Initial Draft | Criminal & Financial Investigations |