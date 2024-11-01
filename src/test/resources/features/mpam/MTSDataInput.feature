@MTSDataInput @MPAM
Feature: MTS Data Input

  Background:
    Given I am logged into "CS" as user "MPAM_USER"
    And I create a single "MTS" case and return to the dashboard
    And I load and claim the current case

  @MPAMRegression1 @SmokeTests
  Scenario: The user is able to complete and close an MTS Case
    And I complete the Data Input stage and close the MTS Case
    Then the case should be closed
    And the read-only Case Details accordion should contain all case information entered during the "Data Input" stage
    And the support note should be visible in the summary

  @Validation
  Scenario Outline: User tests validation at MTS Data Input
    And I trigger the "<errorType>" error message at "Data Input"
    Then the "<errorType>" error message is displayed at "Data Input"
    Examples:
      | errorType                       |
      | PRIMARY CORRESPONDENT REQUIRED  |
      | BUSINESS AREA REQUIRED          |
      | BUSINESS UNIT REQUIRED          |
      | URGENCY REQUIRED                |
      | CHANNEL RECEIVED REQUIRED       |
      | ENQUIRY SUBJECT REQUIRED        |
      | ENQUIRY REASON REQUIRED         |
      | SUPPORT CASE NOTE REQUIRED      |
      | YOUR BUSINESS AREA REQUIRED     |