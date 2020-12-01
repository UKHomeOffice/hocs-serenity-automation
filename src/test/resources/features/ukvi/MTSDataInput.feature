@DataInput @MTS
Feature: MTS Data Input

  Background:
    Given I log in to DECS
    And I create a single "MTS" case and return to the dashboard
    And I load and claim the current case

  @MPAMRegression
  Scenario: The user is able to complete and close an MTS Case
    And I complete the Data Input stage and close the MTS Case
    Then the case should be closed

  @MPAMRegression
  Scenario: The user is able to put an MTS Case On Hold
    And I put the MTS case on hold
    Then the case should be moved to the "On Hold" stage

  @Validation
  Scenario: User triggers all error messages at the MTS Data Input stage
    And I check the validation at the MTS Data Input stage

  @Validation
  Scenario Outline: User triggers error messages at the MTS Data Input stage
    And I trigger the "<errorMessage>" error at the MTS Data Input stage
    Then the "<errorMessage>" error message should be displayed at MTS Data Input Stage
    Examples:
    | errorMessage          |
    | Primary Correspondent |
    | Business Area         |
    | Business Unit         |
    | Urgency               |
    | Channel Received      |
    | Enquiry Subject       |
    | Enquiry Reason        |
    | Your Business Area    |
    | Date of Surgery       |
    | Actions               |