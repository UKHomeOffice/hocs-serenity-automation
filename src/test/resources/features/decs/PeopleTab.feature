@PeopleTab @DECS
Feature: PeopleTab

  Background:
    Given I log in to DECS

  @SmokeTests
  Scenario Outline: User change the primary correspondent of the case
    And I create a "MIN" case and move it to the "DATA INPUT" stage
    And I load and claim the current case
    And I complete the Data Input stage adding 3 member correspondents
    And I load and claim the current case
    And I change the primary correspondent of the case to "<correspondent>"
    Then the primary correspondent should be "<correspondent>"
    Examples:
      | correspondent   |
      | Theresa May     |
      | Nicola Sturgeon |

  @SmokeTests
  Scenario Outline: User is able to remove a correspondent from the case
    And I create a "MIN" case and move it to the "DATA INPUT" stage
    And I load and claim the current case
    And I complete the Data Input stage adding 3 member correspondents
    And I load and claim the current case
    And I remove "<correspondent>" as a correspondent of the case
    Then "<correspondent>" should be removed as a correspondent on the case
    Examples:
      | correspondent   |
      | Boris Johnson   |
      | Theresa May     |
      | Nicola Sturgeon |

  @SmokeTests
  Scenario Outline: User is able to add a correspondent to the case through the people tab
    And I create a "MIN" case and move it to the "DATA INPUT" stage
    And I load and claim the current case
    And I add a "<correspondentType>" correspondent to the case
    Then the new correspondent is added to the case
    Examples:
      | correspondentType |
      | Member            |
      | Public            |