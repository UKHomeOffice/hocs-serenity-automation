@PeopleTab @CS
Feature: PeopleTab

  Background:
    Given I am logged into "CS" as user "DECS_USER"

  @CSRegression
  Scenario: User adds an MP correspondent to a case as part of a stage
    And I get a new case that allows adding a Member correspondent
    And I progress to the point of adding correspondents
    When I add a "Member" correspondent
    Then the submitted correspondent should be visible in the list of correspondents

  @CSRegression
  Scenario: User adds a non-mp correspondent to a case as part of a stage
    And I get a new case that requires correspondents to be added as part of a stage
    And I progress to the point of adding correspondents
    When I add a "Non-Member" correspondent
    Then the submitted correspondent should be visible in the list of correspondents

  @CSRegression
  Scenario: User removes the primary correspondent from a case as part of a stage
    And I get a new case that requires correspondents to be added as part of a stage
    And I progress to the point of adding correspondents
    When I add a "Non-Member" correspondent
    And I remove the primary correspondent
    Then there shouldn't be a primary correspondent displayed

  @CSRegression
  Scenario: User edits an existing correspondents name as part of a stage
    And I get a new case that requires correspondents to be added as part of a stage
    And I progress to the point of adding correspondents
    When I add a "Non-Member" correspondent
    And I edit the primary correspondents name
    Then the primary correspondents name should be updated

  @CSRegression
  Scenario: User adds a second correspondent and selects them as the primary correspondent as part of a stage
    And I get a new case that allows adding a Member correspondent
    And I progress to the point of adding correspondents
    When I add a "Member" correspondent
    And I add a "Non-Member" correspondent
    And I select a different correspondent as the primary correspondent
    And I progress the case to save the change of primary correspondent
    Then the case summary should list the correct primary correspondent

  @CSRegression
  Scenario Outline: User can add a Member of each Parliament as a Correspondent
    And I get a new case that allows adding a Member correspondent
    And I progress to the point of adding correspondents
    When I add "<mpName>" as a correspondent
    Then the submitted correspondent should be visible in the list of correspondents
    Examples:
      | mpName                                                |
      | Boris Johnson                                         |
      | Nicola Sturgeon MSP                                   |
      | The Admiral of the Fleet the Lord Boyce KG GCB OBE DL |
      | Dr Caoimhe Archibald MLA                              |
      | Mabon ap Gwynfor MS                                   |

  @CSRegression
  Scenario Outline: User is able to add a correspondent to the case via the people tab
    And I get a new case that allows adding a Member correspondent
    When I add a "<correspondentType>" correspondent using the People tab
    Then the correspondent is added to the case
    Examples:
      | correspondentType |
      | Member            |
      | Public            |

  @CSRegression
  Scenario: User changes the primary correspondent of the case via the people tab
    And I get a new case that allows adding a Member correspondent
    And I progress to the point of adding correspondents
    And I add 2 "Member" correspondents
    When I manage the correspondents using the People tab
    And I select a different correspondent as the primary correspondent
    And I confirm the change of primary correspondent
    Then the case summary should list the correct primary correspondent

  @CSRegression
  Scenario: User is able to remove non-primary correspondents from the case via the people tab
    And I get a new case that allows adding a Member correspondent
    And I progress to the point of adding correspondents
    And I add 2 "Member" correspondents
    When I manage the correspondents using the People tab
    And I remove a non-primary correspondent from the case
    Then the removed correspondent should no longer be visible