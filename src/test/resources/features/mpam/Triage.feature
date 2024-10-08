@Triage @MPAM
Feature: Triage

  Background:
    Given I am logged into "CS" as user "MPAM_USER"
    And I create a "MPAM" case and move it to the "Triage" stage
    And I load and claim the current case

  @Navigation
  Scenario: User should be on the MPAM Triage Page
    Then the "MPAM Triage" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User should be on the Enquiry Subject Page
    When I select to set the Enquiry Subject and Reason
    Then the "Enquiry subject" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User should be on the Enquiry Reason Page
    When I select to set the Enquiry Subject and Reason
    And I select the "Other" enquiry subject and continue
    Then the "Enquiry reason" page should be displayed
    And the header tags in the HTML of the page are properly structured
    And the accessibility statement link should be visible

  @Navigation
  Scenario: User should be on the EU National Compliance Measures Page
    When I select to set the Enquiry Subject and Reason
    And I select the "Person Specific" enquiry subject and continue
    And I select the "EU National Compliance Measures" enquiry reason and continue
    Then the "EU National Compliance Measures" page should be displayed

  @Validation
  Scenario: User can see the selected enquiry subject and reason on the MPAM Triage page
    And I select the "Other" enquiry subject and continue
    And I select the "DNA" enquiry reason and continue
    Then the set enquiry subject and reason should be displayed on the MPAM Triage page

  @MPAMRegression2
  Scenario: User can select multiple compliance measures for a case with EU Nationals Compliance Measures as the enquiry reason
    And I select the "Person Specific" enquiry subject and continue
    And I select the "EU National Compliance Measures" enquiry reason and continue
    And I select the "Education" compliance measure
    And I select the "Other" compliance measure
    And I enter details of the compliance measures and continue
    And I select the business unit
    And I select the action "Ready to draft"
    And the case should be moved to the "Draft" stage
    Then the summary tab should display "Education" as a compliance measure
    And the summary tab should display "Other" as a compliance measure
    And the summary tab should display the details entered for EU National Compliance Measures
    
  Scenario: User views the Business Units for different Business Areas
    And I select to change the Business Area
    And I record the current options for Business Unit
    When I select "Windrush" as the new Business Area of the case
    Then the options for Business Unit should change

  @MPAMRegression2
  Scenario: User can change the Business Area of the case
    When I change the Business Area of the case to "Windrush"
    Then the new Business Area should be selected in the accordion
    And the case should be in the correct MPAM "Triage" team workstack

  @MPAMRegression2
  Scenario: User can change the reference type of the case
    When I change the reference type of the case to "Correct an error"
    Then the reference type that is displayed should be "Official"
    And the case should be in the correct MPAM "Triage" team workstack
    And a Conversion note should be visible in the timeline showing the submitted notes on the conversion of the case

  @MPAMWorkflow @MPAMRegression2
  Scenario: User completes the Triage stage
    When I complete the "Triage" stage
    Then the case should be moved to the "Draft" stage

  @MPAMWorkflow @MPAMRegression2
  Scenario: User puts the Triage case On Hold
    When I send the Triage case to "On Hold"
    Then the case should be moved to the "Triage (On Hold)" stage
    And the case "should" be allocated to me in the summary

  @MPAMWorkflow @MPAMRegression2
  Scenario: User escalates the Triage case to the workflow manager
    When I send the Triage case to "Workflow Manager"
    Then the case should be moved to the "Triage (Escalated)" stage

  @MPAMWorkflow @MPAMRegression2
  Scenario: User takes a Triage On Hold case off hold
    And I send the Triage case to "On Hold"
    And I load the current case
    When I take the Triage (On Hold) case off hold
    Then the case should be moved to the "Triage" stage
    And the case "should" be allocated to me in the summary

  @MPAMWorkflow @MPAMRegression2
  Scenario: User de-escalates a Triage (Escalated) case
    When I send the Triage case to "Workflow Manager"
    And I load the current case
    When I select the "De-Escalate" action at the Triage-Escalated stage
    Then the case should be moved to the "Triage" stage
    And the case "should" be allocated to me in the summary

  @MPAMWorkflow @MPAMRegression2
  Scenario: User closes a Triage (Escalated) case
    And I send the Triage case to "Workflow Manager"
    When I load the current case
    And I select the "Close Case" action at the Triage-Escalated stage
    And I click the "Close case" button
    Then the case should be closed

  @MPAMWorkflow @MPAMRegression2
  Scenario: User moves a case into a Campaign from the Triage stage
    When I move the case into a Campaign from the "Triage" stage
    And I load the current case
    Then the case is added to the correct Campaign

  @MPAMWorkflow @MPAMRegression2
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

  @Validation
  Scenario Outline: User tests validation at the Triage stage
    And I trigger the "<errorType>" error message at the "Triage" stage
    Then the "<errorType>" error message is displayed at the "Triage" stage
    Examples:
      | errorType                        |
      | ENQUIRY SUBJECT REQUIRED         |
      | ENQUIRY REASON REQUIRED          |
      | BUSINESS UNIT REQUIRED           |
      | ACTIONS REQUIRED                 |
      | ESCALATION REASON REQUIRED       |
      | CAMPAIGN REQUIRED                |