@Triage
Feature: Triage

  Background:
    Given I log in to DECS
    And I create a "UKVI" case and move it to the "Triage" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the UKVI Triage Page
    Then the "UKVI Triage" page should be displayed

  @Navigation
  Scenario: User should be on the Enquiry Subject Page
    When I click the "Set enquiry subject/reason" link
    Then the "Enquiry subject" page should be displayed

  @Navigation
  Scenario: User should be on the Enquiry Reason Page
    When I click the "Set enquiry subject/reason" link
    And I select an enquiry subject and continue
    Then the "Enquiry reason" page should be displayed

  Scenario: User can see the selected enquiry subject and reason on the UKVI Triage page
    When I click the "Set enquiry subject/reason" link
    And I select an enquiry subject and continue
    And I select an enquiry reason and continue
    Then the set enquiry subject and reason should be displayed on the UKVI Triage page

  Scenario: User views the Business Units for different Business Areas
    And I record the current options for Business Area
    When I change the Business Unit of the case to "Windrush"
    Then the options for Business Area should change

  @Workflow
  Scenario: User completes the Triage stage
    When I complete the "Triage" stage
    Then the case should be moved to the "Draft" stage

  @Workflow
  Scenario: User puts the Triage case On Hold
    When I send the Triage case to "On Hold"
    Then the case should be moved to the "Triage (On Hold)" stage
    And the case should be allocated to me in the summary

  @Workflow
    Scenario: User escalates the Triage case to the workflow manager
    When I send the Triage case to "Workflow Manager"
    Then the case should be moved to the "Triage (Escalated)" stage

  @Workflow
  Scenario: User takes a Triage On Hold case off hold
    And I send the Triage case to "On Hold"
    And I load and claim the current case
    When I take the Triage (On Hold) case off hold
    Then the case should be moved to the "Triage" stage
    And the case should be allocated to me in the summary

  @Validation
  Scenario Outline: User triggers error message to be displayed at Triage
    When the user triggers the "<errorType>" error message at Triage by not entering the correct information
    Then  the "<errorType>" error message should be displayed at Triage
    Examples:
      | errorType                |
      | Actions Required         |
      | Business Unit Required   |
      | Enquiry Subject Required |
      | Enquiry Reason Required  |