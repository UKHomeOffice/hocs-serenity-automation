@PeopleTab @CS
Feature: PeopleTab

  Background:
    Given I am logged into "CS" as user "DECS_USER"

  Scenario: User adds an MP correspondent to a case as part of a stage
    When I create a case that allows adding an MP correspondent
    And I progress to the point of adding correspondents
    And I add a "Member" correspondent
    Then the submitted correspondent should be visible in the list of correspondents
    And the primary correspondent details should be visible in the summary tab

  Scenario: User adds a non-mp correspondent to a case as part of a stage
    When I create a case that allows adding an MP correspondent
    And I progress to the point of adding correspondents
    When I select to add a correspondent that "is not" a member of parliament
    And I fill all mandatory fields on the "CORRESPONDENT DETAILS" page with valid data
    Then the submitted correspondent should be visible in the list of correspondents

  Scenario: User removes the primary correspondent from a case as part of a stage
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    When I add a "Constituent" correspondent
    And I remove the primary correspondent
    Then there shouldn't be a primary correspondent displayed

  Scenario: User edits an existing correspondents name as part of a stage
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    When I add a "Constituent" correspondent
    And I edit the primary correspondents name
    Then the correspondents name should be updated

  Scenario: User adds a second correspondent and selects them as the primary correspondent as part of a stage
    When I complete all required fields for Creation stage
    And I click the "Continue" button
    And I add a "Member" correspondent
    And I add a "Constituent" correspondent
    When I select the primary correspondent radio button for a different correspondent
    And I click the "Move to Triage" button
    Then the case summary should list the correct primary correspondent

  @CSRegression
  Scenario Outline: User changes the primary correspondent of the case via the people tab
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