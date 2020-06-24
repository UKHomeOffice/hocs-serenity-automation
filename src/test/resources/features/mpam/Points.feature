@Points
Feature: Points

  Background:
    Given I log in to DECS

  Scenario Outline: User checks points for cases with different Priorities and Reference Types
    When I create a single "MPAM" case with the correspondence received date set 0 days ago
    And I go to the case from the successful case creation screen
    And I claim the current case
    And I complete Creation stage with "<urgency>" as the Urgency and "<refType>" as the Reference Type
    And I view the case in the correct MPAM "Triage" workstack
    Then the case should be assigned "<expectedPoints>" points
    Examples:
      | urgency  | refType | expectedPoints |
      | Standard  | Official   | 0.0            |
      | Standard  | Ministerial   | 1.0            |
      | Priority  | Official   | 7.0            |
      | Priority  | Ministerial   | 15             |
      | Immediate | Official   | 30             |
      | Immediate | Ministerial   | 31             |

