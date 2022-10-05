@PeopleTab @CS
Feature: PeopleTab

  Background:
    Given I am logged into "CS" as user "DECS_USER"

  @CSRegression
  Scenario: User adds an MP correspondent to a case as part of a stage
    And I get a new case that allows adding a Member correspondent
    And I progress to the point of adding correspondents
    And I add a "Member" correspondent
    Then the submitted correspondent should be visible in the list of correspondents

  @CSRegression
  Scenario: User adds a non-mp correspondent to a case as part of a stage
    And I get a new "CS" case
    And I progress to the point of adding correspondents
    When I add a "Non-Member" correspondent
    Then the submitted correspondent should be visible in the list of correspondents

  @CSRegression
  Scenario: User removes the primary correspondent from a case as part of a stage
    And I get a new "CS" case
    And I progress to the point of adding correspondents
    When I add a "Non-Member" correspondent
    And I remove the primary correspondent
    Then there shouldn't be a primary correspondent displayed

  @CSRegression
  Scenario: User edits an existing correspondents name as part of a stage
    And I get a new "CS" case
    And I progress to the point of adding correspondents
    When I add a "Non-Member" correspondent
    And I edit the primary correspondents name
    Then the correspondents name should be updated

  @CSRegression
  Scenario: User adds a second correspondent and selects them as the primary correspondent as part of a stage
    And I get a new case that allows adding a Member correspondent
    And I progress to the point of adding correspondents
    And I add a "Member" correspondent
    And I add a "Non-Member" correspondent
    When I select the primary correspondent radio button for a different correspondent
    And I progress the case to save the change of primary correspondent
    Then the case summary should list the correct primary correspondent

  @CSRegression
  Scenario: User changes the primary correspondent of the case via the people tab
    And I get a new case that allows adding a Member correspondent
    And I progress to the point of adding correspondents
    And I add 3 "Member" correspondents
    When I manage the correspondents using the People tab
    And I change the primary correspondent of the case to "<correspondent>"
    Then the primary correspondent should be "<correspondent>"

  @CSRegression
  Scenario: User is able to remove non-primary correspondents from the case via the people tab
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
    And I fill all mandatory fields on the Data Input page
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