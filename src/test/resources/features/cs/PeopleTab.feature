@PeopleTab @CS
Feature: PeopleTab

  Background:
    Given I am logged into "CS" as user "DECS_USER"

  @CSRegression
  Scenario Outline: User changes the primary correspondent of the case
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

  @CSRegression
  Scenario: User is able to remove non-primary correspondents from the case
    And I create a "MIN" case and move it to the "DATA INPUT" stage
    And I load and claim the current case
    And I complete the Data Input stage adding 3 member correspondents
    And I load and claim the current case
    And I remove "Nicola Sturgeon" as a correspondent of the case
    Then "Nicola Sturgeon" should be removed as a correspondent on the case

  @CSRegression
  Scenario Outline: User is able to add a correspondent to the case through the people tab
    And I create a "MIN" case and move it to the "DATA INPUT" stage
    And I load and claim the current case
    And I add a "<correspondentType>" correspondent to the case
    Then the new correspondent is added to the case
    Examples:
      | correspondentType |
      | Member            |
      | Public            |

  @CSRegression
  Scenario Outline: User can add a Member of Parliament as a Correspondent
    When I create a "MIN" case and move it to the "Data Input" stage
    And I load and claim the current case
    And I fill all mandatory fields on the "Data Input" page with valid data
    And I click the "Continue" button
    And I add "<mpName>" MP as a correspondent
    Then the submitted correspondent should be visible in the list of correspondents
    Examples:
      | mpName                                                |
      | Boris Johnson                                         |
      | Nicola Sturgeon MSP                                   |
      | The Admiral of the Fleet the Lord Boyce KG GCB OBE DL |
      | Dr Caoimhe Archibald MLA                              |
      | Mabon ap Gwynfor MS                                   |