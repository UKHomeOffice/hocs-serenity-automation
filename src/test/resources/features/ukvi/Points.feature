@Points
Feature: Points

  Background:
    Given I am user "AUTOMATION_USER"

  Scenario Outline: User checks points for cases with different Priorities and Reference Types
    When I create a single "UKVI" case with the correspondence received date set 0 days ago
    And I go to the case from the successful case creation screen
    And I claim the current case
    And I complete Creation stage with "<urgency>" as the Urgency and "<refType>" as the Reference Type
    And I view the case in the correct UKVI "Triage" workstack
    Then the case should be assigned "<expectedPoints>" points
    Examples:
      | urgency  | refType | expectedPoints |
      | Standard  | B:Ref   | 0.0            |
      | Standard  | M:Ref   | 1.0            |
      | Priority  | B:Ref   | 7.0            |
      | Priority  | M:Ref   | 15             |
      | Immediate | B:Ref   | 30             |
      | Immediate | M:Ref   | 31             |

