@Topics @DCU
Feature: Topics

  Background:
    Given I log in to DECS

  @DataValidation @Ignore
  Scenario Outline: Topics are assigned to the correct team
    When I create a single "MIN" case and return to the dashboard
    And I complete the Data Input Stage
    And I assign the Topic "<Topic>"
    Then the case should be assigned to the "<draftingTeam>" for drafting
    And the case should be assigned to the "<privateOfficeTeam>" for approval
    Examples:
      | Topic                               | draftingTeam                         | privateOfficeTeam                                                  |
      | Animal alternatives (3Rs)           | Animals in Science Regulation Unit   | Minister for Lords                                                 |
      | Cyber Stalking And Harassment       | Public Protection Unit               | Under Secretary of State for Crime, Safeguarding and Vulnerability |
      | Domestic Violence Protection Orders | Public Protection Unit               | Under Secretary of State for Crime, Safeguarding and Vulnerability |
      | Child Sexual Exploitation           | Tackling Exploitation and Abuse Unit | Under Secretary of State for Crime, Safeguarding and Vulnerability |
      | Permanent Secretary AFU             | Accounting and Finance Unit          | Permanent Secretary                                                |
      | Permanent Secretary CAIT            | Central Analysis and Insight Team    | Permanent Secretary                                                |
      | Afghan Interpreters                 | Domestic Asylum Policy               | Minister of State for Immigration                                  |
      | Future Funds                        | Efficiency and Resources Unit        | Minister of State for Policing and Fire Service                    |
      | Criminal Records                    | International Criminality Unit       | Minister of State for Policing and Fire Service                    |
      | Classification of Cannabis          | Drugs & Alcohol Unit                 | Minister of State for Policing and Fire Service                    |

  @DCUWorkflow @DCUSmokeTests
  Scenario Outline: The user overrides the "<defaultTeam>" Team
    When I create a "<caseType>" case with "<Topic>" as the primary topic
    And I override the "<defaultTeam>" team to "<overrideTeam>"
    Then the case should be found in the "<overrideTeam>" team
    Examples:
      | caseType | Topic                         | defaultTeam   | overrideTeam                                             |
      | MIN      | Cyber Stalking And Harassment | Initial Draft | Extremism Analysis Unit                                  |
      | MIN      | Cyber Stalking And Harassment | Initial Draft | Press Office                                             |
      | MIN      | Cyber Stalking And Harassment | Initial Draft | Finance                                                  |
      | MIN      | Cyber Stalking And Harassment | Initial Draft | Chemical, Biological, Radiological, Nuclear & Explosives |