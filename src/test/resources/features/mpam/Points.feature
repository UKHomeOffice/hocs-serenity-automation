@Points
Feature: Points

  Background:
    Given I log in to DECS

  Scenario Outline: User checks points for cases with different Priorities and Reference Types

    And I complete Creation stage with "<urgency>" as the Urgency and "<refType>" as the Reference Type


