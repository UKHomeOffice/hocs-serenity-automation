@Triage @UKVI
Feature: Triage

  Background:
    Given I log in to "DECS" as user "UKVI_USER"
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the MPAM Triage Page
    Then the "MPAM Triage" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User should be on the Enquiry Subject Page
    When I click the "Set enquiry subject/reason" link
    Then the "Enquiry subject" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User should be on the Enquiry Reason Page
    When I click the "Set enquiry subject/reason" link
    And I select an enquiry subject and continue
    Then the "Enquiry reason" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @UKVIRegression @test4
  Scenario: User can see the selected enquiry subject and reason on the MPAM Triage page
    When I click the "Set enquiry subject/reason" link
    And I select an enquiry subject and continue
    And I select an enquiry reason and continue
    Then the set enquiry subject and reason should be displayed on the MPAM Triage page

  @OtherTests
  Scenario: User views the Business Units for different Business Areas
    And I select to change the Business Area
    And I record the current options for Business Unit
    When I select "Windrush" as the new Business Area of the case
    Then the options for Business Unit should change

  @UKVIRegression
  Scenario: User can change the Business Area of the case
    When I change the Business Area of the case to "Windrush"
    Then the new Business Area should be selected in the accordion
    And the case should be in the correct MPAM "Triage" team workstack

  @UKVIRegression
  Scenario: User can change the reference type of the case
    When I change the reference type of the case to "Correct an error"
    Then the reference type that is displayed should be "Official"
    And the case should be in the correct MPAM "Triage" team workstack
    And a conversion note should be visible showing the entered notes on conversion

  @UKVIWorkflow @UKVIRegression
  Scenario: User completes the Triage stage
    When I complete the "Triage" stage
    Then the case should be moved to the "Draft" stage

  @UKVIWorkflow @UKVIRegression
  Scenario: User puts the Triage case On Hold
    When I send the Triage case to "On Hold"
    Then the case should be moved to the "Triage (On Hold)" stage
    And the case "should" be allocated to me in the summary

  @UKVIWorkflow @UKVIRegression
  Scenario: User escalates the Triage case to the workflow manager
    When I send the Triage case to "Workflow Manager"
    Then the case should be moved to the "Triage (Escalated)" stage

  @UKVIWorkflow @UKVIRegression
  Scenario: User takes a Triage On Hold case off hold
    And I send the Triage case to "On Hold"
    And I load and claim the current case
    When I take the Triage (On Hold) case off hold
    Then the case should be moved to the "Triage" stage
    And the case "should" be allocated to me in the summary

  @UKVIWorkflow @UKVIRegression
  Scenario: User de-escalates a Triage (Escalated) case
    When I send the Triage case to "Workflow Manager"
    And I load and claim the current case
    When I de-escalate the Triage (Escalated) case
    Then the case should be moved to the "Triage" stage
    And the case "should" be allocated to me in the summary

  @UKVIWorkflow @UKVIRegression
  Scenario: User closes a Triage (Escalated) case
    And I send the Triage case to "Workflow Manager"
    When I load and claim the current case
    And I select to close the Triage (Escalated) case
    And I click the "Close case" button
    Then the case should be closed

  @Validation
  Scenario Outline: User triggers error message to be displayed at Triage
    When the user triggers the "<errorType>" error message at Triage by not entering the correct information
    Then  the "<errorType>" error message should be displayed at Triage
    Examples:
      | errorType                                 |
      | Actions Required                          |
      | Business Unit Required                    |
      | Enquiry Subject Required                  |

  @UKVIWorkflow @UKVIRegression
  Scenario: User moves a case into a Campaign from the Triage stage
    When I move the case into a Campaign from the "Triage" stage
    And I load the current case
    Then the case is added to the correct Campaign

  @UKVIWorkflow @UKVIRegression
  Scenario Outline: User moves cases into Campaigns from the Triage sub-stages
    When I send the Triage case to "<moveTo>"
    And I load the current case
    And I move the case into a Campaign from the "<triageStage>" stage
    And I load the current case
    Then the case is added to the correct Campaign
    Examples:
      | moveTo                 | triageStage                   |
      | On Hold                | Triage-On Hold                |
      | Workflow Manager       | Triage-Escalated              |